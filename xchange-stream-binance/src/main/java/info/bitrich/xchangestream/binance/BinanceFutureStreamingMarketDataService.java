package info.bitrich.xchangestream.binance;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.util.concurrent.RateLimiter;
import info.bitrich.xchangestream.binance.dto.BinanceWebsocketTransaction;
import info.bitrich.xchangestream.binance.dto.FutureCoinDepthBinanceWebSocketTransaction;
import info.bitrich.xchangestream.binance.dto.FutureUSDTDepthBinanceWebSocketTransaction;
import io.reactivex.Observable;
import org.knowm.xchange.binance.BinanceAdapters;
import org.knowm.xchange.binance.BinanceErrorAdapter;
import org.knowm.xchange.binance.BinanceExchangeSpecification;
import org.knowm.xchange.binance.dto.BinanceException;
import org.knowm.xchange.binance.dto.FuturesSettleType;
import org.knowm.xchange.binance.dto.marketdata.BinanceOrderbook;
import org.knowm.xchange.binance.service.BinanceFuturesMarketDataService;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.OrderBookUpdate;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.RateLimitExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static info.bitrich.xchangestream.service.netty.StreamingObjectMapperHelper.getObjectMapper;

public class BinanceFutureStreamingMarketDataService extends BinanceStreamingMarketDataService {
  private static final Logger LOG =
      LoggerFactory.getLogger(BinanceFutureStreamingMarketDataService.class);

  private static JavaType DEPTH_TYPE;

  protected final BinanceFuturesMarketDataService marketDataService;
  protected final BinanceExchangeSpecification specification;

  private final Map<CurrencyPair, OrderbookSubscription> orderbooks = new HashMap<>();

  public BinanceFutureStreamingMarketDataService(
      BinanceStreamingService service,
      BinanceFuturesMarketDataService marketDataService,
      Runnable onApiCall,
      final String orderBookUpdateFrequencyParameter,
      BinanceExchangeSpecification specification) {

    super(service, null, onApiCall, orderBookUpdateFrequencyParameter);

    this.marketDataService = marketDataService;
    this.specification = specification;

    if (specification.getFuturesSettleType() == FuturesSettleType.USDT) {
      DEPTH_TYPE =
          getObjectMapper()
              .getTypeFactory()
              .constructType(
                  new TypeReference<
                      BinanceWebsocketTransaction<FutureUSDTDepthBinanceWebSocketTransaction>>() {
                  });
    } else if (specification.getFuturesSettleType() == FuturesSettleType.COIN) {
      DEPTH_TYPE =
          getObjectMapper()
              .getTypeFactory()
              .constructType(
                  new TypeReference<
                      BinanceWebsocketTransaction<FutureCoinDepthBinanceWebSocketTransaction>>() {
                  });
    }
  }

  private final class OrderbookSubscription {
    long snapshotlastUpdateId;
    AtomicLong lastUpdateId = new AtomicLong(0L);
    OrderBook orderBook;
    Observable<BinanceWebsocketTransaction<FutureUSDTDepthBinanceWebSocketTransaction>> stream;

    void invalidateSnapshot() {
      snapshotlastUpdateId = 0L;
    }

    void initSnapshotIfInvalid(CurrencyPair currencyPair) {
      if (snapshotlastUpdateId != 0L) return;
      try {
        LOG.info("Fetching initial orderbook snapshot for {} ", currencyPair);
        onApiCall.run();
        fallbackOnApiCall.get().run();
        BinanceOrderbook book = fetchBinanceOrderBook(currencyPair);
        snapshotlastUpdateId = book.lastUpdateId;
//        lastUpdateId.set(book.lastUpdateId);  // #see subscription.stream dealing
        lastUpdateId.set(0L);
        orderBook = BinanceAdapters.convertOrderBook(book, currencyPair);
      } catch (Exception e) {
        LOG.error("Failed to fetch initial order book for " + currencyPair, e);
        snapshotlastUpdateId = 0L;
        lastUpdateId.set(0L);
        orderBook = null;
      }
    }

    private BinanceOrderbook fetchBinanceOrderBook(CurrencyPair currencyPair)
        throws IOException, InterruptedException {
      try {
        return marketDataService.getBinanceOrderbook(currencyPair, 1000);
      } catch (BinanceException e) {
        if (BinanceErrorAdapter.adapt(e) instanceof RateLimitExceededException) {
          if (fallenBack.compareAndSet(false, true)) {
            LOG.error(
                "API Rate limit was hit when fetching Binance order book snapshot. Provide a \n"
                    + "rate limiter. Apache Commons and Google Guava provide the TimedSemaphore\n"
                    + "and RateLimiter classes which are effective for this purpose. Example:\n"
                    + "\n"
                    + "  exchangeSpecification.setExchangeSpecificParametersItem(\n"
                    + "      info.bitrich.xchangestream.util.Events.BEFORE_API_CALL_HANDLER,\n"
                    + "      () -> rateLimiter.acquire())\n"
                    + "\n"
                    + "Pausing for 15sec and falling back to one call per three seconds, but you\n"
                    + "will get more optimal performance by handling your own rate limiting.");
            RateLimiter rateLimiter = RateLimiter.create(0.333);
            fallbackOnApiCall.set(rateLimiter::acquire);
            Thread.sleep(15000);
          }
        }
        throw e;
      }
    }
  }

  private OrderbookSubscription connectOrderBook(CurrencyPair currencyPair) {
    OrderbookSubscription subscription = new OrderbookSubscription();

    // 1. Open a stream to wss://stream.binance.com:9443/ws/bnbbtc@depth
    // 2. Buffer the events you receive from the stream.

    subscription.stream =
        service
            .subscribeChannel(channelFromCurrency(currencyPair, "depth"))
            .map(this::depthTransaction)
            .filter(transaction -> transaction.getData().getCurrencyPair().equals(currencyPair));
    return subscription;
  }

  @Override
  protected Observable<OrderBook> orderBookStream(CurrencyPair currencyPair) {
    OrderbookSubscription subscription = orderbooks.computeIfAbsent(currencyPair, this::connectOrderBook);

    return subscription
        .stream

        // 3. Get a depth snapshot from
        // https://www.binance.com/api/v1/depth?symbol=BNBBTC&limit=1000
        // (we do this if we don't already have one or we've invalidated a previous one)
        .doOnNext(transaction -> subscription.initSnapshotIfInvalid(currencyPair))

        // If we failed, don't return anything. Just keep trying until it works
        .filter(transaction -> subscription.snapshotlastUpdateId > 0L)
        .map(BinanceWebsocketTransaction::getData)

        // 4. Drop any event where u is < lastUpdateId in the snapshot.
        .filter(depth -> depth.getLastUpdateId() >= subscription.snapshotlastUpdateId)

        // 5. The first processed event should have U <= lastUpdateId AND u >= lastUpdateId
        // 6. While listening to the stream, each new event's pu should be equal to the previous event's u, otherwise initialize the process from step 3.
        .filter(
            depth -> {
              long lastUpdateId = subscription.lastUpdateId.get();
              boolean result;
              if (lastUpdateId == 0L) {
                result =
                    depth.getFirstUpdateId() <= subscription.snapshotlastUpdateId
                        && depth.getLastUpdateId() >= subscription.snapshotlastUpdateId;
              } else {
                result = depth.getLastLastUpdateId() == lastUpdateId;
              }
              if (result) {
                subscription.lastUpdateId.set(depth.getLastUpdateId());
              } else {
                // If not, we re-sync.  This will commonly occur a few times when starting up, since
                // given update ids 1,2,3,4,5,6,7,8,9, Binance may sometimes return a snapshot
                // as of 5, but update events covering 1-3, 4-6 and 7-9.  We can't apply the 4-6
                // update event without double-counting 5, and we can't apply the 7-9 update without
                // missing 6.  The only thing we can do is to keep requesting a fresh snapshot until
                // we get to a situation where the snapshot and an update event precisely line up.
                LOG.info(
                    "Orderbook snapshot for {} out of date (last={}, U={}, u={}). This is normal. Re-syncing.",
                    currencyPair,
                    lastUpdateId,
                    depth.getFirstUpdateId(),
                    depth.getLastUpdateId());
                subscription.invalidateSnapshot();
              }
              return result;
            })

        // 7. The data in each event is the absolute quantity for a price level
        // 8. If the quantity is 0, remove the price level
        // 9. Receiving an event that removes a price level that is not in your local order book can
        // happen and is normal.
        .map(
            depth -> {
              BinanceOrderbook ob = depth.getOrderBook();
              ob.bids.forEach(
                  (key, value) ->
                      subscription.orderBook.update(
                          new OrderBookUpdate(
                              Order.OrderType.BID,
                              null,
                              currencyPair,
                              key,
                              depth.getEventTime(),
                              value)));
              ob.asks.forEach(
                  (key, value) ->
                      subscription.orderBook.update(
                          new OrderBookUpdate(
                              Order.OrderType.ASK,
                              null,
                              currencyPair,
                              key,
                              depth.getEventTime(),
                              value)));
              return subscription.orderBook;
            });
  }

  private BinanceWebsocketTransaction<FutureUSDTDepthBinanceWebSocketTransaction> depthTransaction(
      JsonNode node) {
    try {
      return mapper.readValue(mapper.treeAsTokens(node), DEPTH_TYPE);
    } catch (IOException e) {
      throw new ExchangeException("Unable to parse order book transaction", e);
    }
  }

}
