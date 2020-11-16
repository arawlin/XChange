package info.bitrich.xchangestream.binance;

import info.bitrich.xchangestream.core.ProductSubscription;
import info.bitrich.xchangestream.core.StreamingExchange;
import info.bitrich.xchangestream.core.StreamingExchangeFactory;
import io.reactivex.disposables.Disposable;
import org.knowm.xchange.binance.BinanceExchangeSpecification;
import org.knowm.xchange.binance.BinanceFuturesUSDT;
import org.knowm.xchange.binance.dto.FuturesSettleType;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.DiffOrderBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinanceFutureManualExample {
  private static final Logger LOG = LoggerFactory.getLogger(BinanceFutureManualExample.class);

  public static void main(String[] args) throws InterruptedException {
    // Far safer than temporarily adding these to code that might get committed to VCS
    String apiKey = System.getProperty("binance-api-key");
    String apiSecret = System.getProperty("binance-api-secret");

    BinanceExchangeSpecification spec =
        new BinanceExchangeSpecification(BinanceFutureStreamingExchange.class);
    spec.setSslUri(BinanceFuturesUSDT.URL);
    spec.setHost(BinanceFuturesUSDT.HOST);
    spec.setPort(80);
    spec.setExchangeName("BinanceFutureStreamingExchange");
    spec.setExchangeDescription("Binance Futures Exchange.");

    spec.setFuturesSettleType(FuturesSettleType.USDT);

    spec.setApiKey(apiKey);
    spec.setSecretKey(apiSecret);

    spec.setProxyHost("192.168.1.100");
    spec.setProxyPort(1081);

    spec.setExchangeSpecificParametersItem(StreamingExchange.SOCKS_PROXY_HOST, "192.168.1.100");
    spec.setExchangeSpecificParametersItem(StreamingExchange.SOCKS_PROXY_PORT, 1080);

    spec.setShouldLoadRemoteMetaData(false);

    BinanceFutureStreamingExchange exchange =
        (BinanceFutureStreamingExchange) StreamingExchangeFactory.INSTANCE.createExchange(spec);

    ProductSubscription subscription =
        ProductSubscription.create()
//            .addOrderbook(CurrencyPair.OMG_USDT)
//            .addForceOrders(CurrencyPair.DASH_USDT)
//            .addForceOrders(CurrencyPair.ZEC_USDT)
//            .addTrades(CurrencyPair.ZEC_USDT)
//            .addAggTrades(CurrencyPair.BTC_USDT)
            .addTicker(CurrencyPair.BTC_USDT)
            .build();

    exchange.connect(subscription).blockingAwait();

    LOG.info("Subscribing public channels");

//    Disposable orderbooks2 = orderbooks(exchange, "two");

//    Disposable forceOrder1 = forceOrders(exchange, CurrencyPair.DASH_USDT);
//    Disposable forceOrder2 = forceOrders(exchange, CurrencyPair.ZEC_USDT);

//    Disposable trades =
//        exchange
//            .getStreamingMarketDataService()
//            .getTrades(CurrencyPair.ZEC_USDT)
//            .subscribe(
//                trade -> {
//                  LOG.info("Trade: {}", trade);
//                });

//    Disposable aggTrades = ((BinanceFutureStreamingMarketDataService) exchange.getStreamingMarketDataService())
//        .getAggTrade(CurrencyPair.BTC_USDT)
//        .subscribe(
//            t -> {
//              LOG.info("{}", t);
//            }
//        );

    Disposable ticker = exchange.getStreamingMarketDataService().getTicker(CurrencyPair.BTC_USDT).subscribe(
        t -> {
          LOG.info("{}", t);
        }
    );


    Thread.sleep(1000000);

//    orderbooks2.dispose();
//    forceOrder1.dispose();
//    forceOrder2.dispose();
//    trades.dispose();
//    aggTrades.dispose();
    ticker.dispose();

    exchange.disconnect().blockingAwait();
  }

  private static Disposable orderbooks(StreamingExchange exchange, String identifier) {
    return exchange
        .getStreamingMarketDataService()
        .getOrderBook(CurrencyPair.OMG_USDT)
        .subscribe(
            orderBook -> {
              DiffOrderBook diffOrderBook = (DiffOrderBook) orderBook;
              LOG.info(
                  "Order Book ({}): askDepth={} ask={} askSize={} bidDepth={}. bid={}, bidSize={}",
                  identifier,
                  orderBook.getAsks().size(),
                  orderBook.getAsks().get(0).getLimitPrice(),
                  orderBook.getAsks().get(0).getRemainingAmount(),
                  orderBook.getBids().size(),
                  orderBook.getBids().get(0).getLimitPrice(),
                  orderBook.getBids().get(0).getRemainingAmount()
              );
              LOG.info(
                  "Order Book ({}): isFullUpdate: {}, updateAskDepth={}, updateBidDepth={}",
                  identifier,
                  diffOrderBook.isFullUpdate(),
                  diffOrderBook.getAsksUpdate().size(),
                  diffOrderBook.getBidsUpdate().size()
              );
            },
            throwable -> LOG.error("ERROR in getting order book: ", throwable));
  }

  private static Disposable forceOrders(StreamingExchange exchange, CurrencyPair pair) {
    return ((BinanceFutureStreamingMarketDataService) exchange.getStreamingMarketDataService())
        .getForceOrder(pair)
        .subscribe(
            o -> {
              LOG.info("{} - {}", pair, o);
            }
        );
  }
}
