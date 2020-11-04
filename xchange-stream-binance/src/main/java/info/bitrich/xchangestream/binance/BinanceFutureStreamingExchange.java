package info.bitrich.xchangestream.binance;

import com.google.common.base.MoreObjects;
import info.bitrich.xchangestream.core.ProductSubscription;
import info.bitrich.xchangestream.core.StreamingExchange;
import info.bitrich.xchangestream.core.StreamingMarketDataService;
import info.bitrich.xchangestream.service.netty.ConnectionStateModel;
import info.bitrich.xchangestream.util.Events;
import io.reactivex.Completable;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import org.knowm.xchange.binance.BinanceExchangeSpecification;
import org.knowm.xchange.binance.BinanceFuturesExchange;
import org.knowm.xchange.binance.dto.FuturesSettleType;
import org.knowm.xchange.binance.service.BinanceFuturesMarketDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Created by lin on 2020-11-03. */
public class BinanceFutureStreamingExchange extends BinanceFuturesExchange
    implements StreamingExchange {

  private static final Logger LOG = LoggerFactory.getLogger(BinanceFutureStreamingExchange.class);
  private static String API_BASE_URI;
  protected static final String USE_HIGHER_UPDATE_FREQUENCY =
      "Binance_Orderbook_Use_Higher_Frequency";

  protected BinanceStreamingService streamingService;

  protected BinanceFutureStreamingMarketDataService streamingMarketDataService;

  private Runnable onApiCall;
  private String orderBookUpdateFrequencyParameter = "";

  @Override
  protected void initServices() {
    super.initServices();
    this.onApiCall = Events.onApiCall(exchangeSpecification);
    Boolean userHigherFrequency =
        MoreObjects.firstNonNull(
            (Boolean)
                exchangeSpecification.getExchangeSpecificParametersItem(
                    USE_HIGHER_UPDATE_FREQUENCY),
            Boolean.FALSE);

    if (userHigherFrequency) {
      orderBookUpdateFrequencyParameter = "@100ms";
    }

    BinanceExchangeSpecification spec = (BinanceExchangeSpecification) getExchangeSpecification();
    if (spec.getFuturesSettleType() == FuturesSettleType.USDT) {
      API_BASE_URI = "wss://fstream.binance.com/";
    } else if (spec.getFuturesSettleType() == FuturesSettleType.COIN) {
      API_BASE_URI = "wss://dstream.binance.com/";
    }
  }

  @Override
  public Completable connect(ProductSubscription... args) {
    if (args == null || args.length == 0) {
      throw new IllegalArgumentException("Subscriptions must be made at connection time");
    }
    if (streamingService != null) {
      throw new UnsupportedOperationException(
          "Exchange only handles a single connection - disconnect the current connection.");
    }

    ProductSubscription subscriptions = args[0];
    streamingService = createStreamingService(subscriptions);
    applyStreamingSpecification(getExchangeSpecification(), streamingService);

    List<Completable> completables = new ArrayList<>();

    if (subscriptions.hasUnauthenticated()) {
      completables.add(streamingService.connect());
    }

    streamingMarketDataService =
        new BinanceFutureStreamingMarketDataService(
            streamingService,
            (BinanceFuturesMarketDataService) marketDataService,
            onApiCall,
            orderBookUpdateFrequencyParameter,
            (BinanceExchangeSpecification) getExchangeSpecification());

    return Completable.concat(completables)
        .doOnComplete(() -> streamingMarketDataService.openSubscriptions(subscriptions));
  }

  @Override
  public Completable disconnect() {
    List<Completable> completables = new ArrayList<>();
    completables.add(streamingService.disconnect());
    streamingService = null;
    streamingMarketDataService = null;
    return Completable.concat(completables);
  }

  @Override
  public boolean isAlive() {
    return streamingService != null && streamingService.isSocketOpen();
  }

  @Override
  public Observable<Throwable> reconnectFailure() {
    return streamingService.subscribeReconnectFailure();
  }

  @Override
  public Observable<Object> connectionSuccess() {
    return streamingService.subscribeConnectionSuccess();
  }

  @Override
  public Observable<ConnectionStateModel.State> connectionStateObservable() {
    return streamingService.subscribeConnectionState();
  }

  @Override
  public StreamingMarketDataService getStreamingMarketDataService() {
    return streamingMarketDataService;
  }

  @Override
  public void useCompressedMessages(boolean compressedMessages) {
    streamingService.useCompressedMessages(compressedMessages);
  }

  protected BinanceStreamingService createStreamingService(ProductSubscription subscription) {
    String path =
        API_BASE_URI
            + "stream?streams="
            + BinanceStreamingUtil.buildSubscriptionStreams(
                subscription, "", orderBookUpdateFrequencyParameter, "");
    return new BinanceStreamingService(path, subscription);
  }
}
