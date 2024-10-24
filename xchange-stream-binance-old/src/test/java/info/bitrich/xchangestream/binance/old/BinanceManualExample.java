package info.bitrich.xchangestream.binance.old;

import info.bitrich.xchangestream.core.ProductSubscription;
import info.bitrich.xchangestream.core.StreamingExchange;
import info.bitrich.xchangestream.core.StreamingExchangeFactory;
import io.reactivex.disposables.Disposable;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.DiffOrderBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Lukas Zaoralek on 15.11.17.
 */
public class BinanceManualExample {

  private static final Logger LOG = LoggerFactory.getLogger(BinanceManualExample.class);

  public static void main(String[] args) throws InterruptedException {
    // Far safer than temporarily adding these to code that might get committed to VCS
    String apiKey = System.getProperty("binance-api-key");
    String apiSecret = System.getProperty("binance-api-secret");

    ExchangeSpecification spec = new ExchangeSpecification(BinanceStreamingExchange.class);
    spec.setSslUri("https://api.binance.com");
    spec.setHost("www.binance.com");
    spec.setPort(80);
    spec.setExchangeName("Binance");
    spec.setExchangeDescription("Binance Exchange.");

    spec.setApiKey(apiKey);
    spec.setSecretKey(apiSecret);

    spec.setProxyHost("127.0.0.1");
    spec.setProxyPort(1081);

    spec.setExchangeSpecificParametersItem(StreamingExchange.SOCKS_PROXY_HOST, "127.0.0.1");
    spec.setExchangeSpecificParametersItem(StreamingExchange.SOCKS_PROXY_PORT, 1080);

    spec.setShouldLoadRemoteMetaData(false);

    BinanceStreamingExchange exchange =
        (BinanceStreamingExchange) StreamingExchangeFactory.INSTANCE.createExchange(spec);

    ProductSubscription subscription =
        ProductSubscription.create()
//            .addTicker(CurrencyPair.ETH_BTC)
//            .addTicker(CurrencyPair.LTC_BTC)
//            .addOrderbook(CurrencyPair.LTC_BTC)
//            .addTrades(CurrencyPair.BTC_USDT)
            .setAllTicker(true)
            .build();

    exchange.connect(subscription).blockingAwait();

    LOG.info("Subscribing public channels");

//    Disposable tickers =
//        exchange
//            .getStreamingMarketDataService()
//            .getTicker(CurrencyPair.ETH_BTC)
//            .subscribe(
//                ticker -> {
//                  LOG.info("Ticker: {}", ticker);
//                },
//                throwable -> LOG.error("ERROR in getting ticker: ", throwable));
//
//    Disposable trades =
//        exchange
//            .getStreamingMarketDataService()
//            .getTrades(CurrencyPair.BTC_USDT)
//            .subscribe(
//                trade -> {
//                  LOG.info("Trade: {}", trade);
//                });
//
//    Disposable orderChanges = null;
//    Disposable userTrades = null;
//    Disposable balances = null;
//    Disposable accountInfo = null;
//    Disposable executionReports = null;
//
//    if (apiKey != null) {
//
//      LOG.info("Subscribing authenticated channels");
//
//      // Level 1 (generic) APIs
//      orderChanges =
//          exchange
//              .getStreamingTradeService()
//              .getOrderChanges()
//              .subscribe(oc -> LOG.info("Order change: {}", oc));
//      userTrades =
//          exchange
//              .getStreamingTradeService()
//              .getUserTrades()
//              .subscribe(trade -> LOG.info("User trade: {}", trade));
//      balances =
//          exchange
//              .getStreamingAccountService()
//              .getBalanceChanges()
//              .subscribe(
//                  trade -> LOG.info("Balance: {}", trade),
//                  e -> LOG.error("Error in balance stream", e));
//
//      // Level 2 (exchange-specific) APIs
//      executionReports =
//          exchange
//              .getStreamingTradeService()
//              .getRawExecutionReports()
//              .subscribe(report -> LOG.info("Subscriber got execution report: {}", report));
//      accountInfo =
//          exchange
//              .getStreamingAccountService()
//              .getRawAccountInfo()
//              .subscribe(
//                  accInfo ->
//                      LOG.info(
//                          "Subscriber got account Info (not printing, often causes console issues in IDEs)"));
//    }
//
//    Disposable orderbooks = orderbooks(exchange, "one");
//    Thread.sleep(5000);
//    Disposable orderbooks2 = orderbooks(exchange, "two");

    Disposable allticker = exchange.getStreamingMarketDataService().getAllTicker().subscribe(tickers -> LOG.info("tickers count - {}", tickers.size()));

    Thread.sleep(1000000);

//    tickers.dispose();
//    trades.dispose();
//    orderbooks.dispose();
//    orderbooks2.dispose();
    allticker.dispose();

//    if (apiKey != null) {
//      orderChanges.dispose();
//      userTrades.dispose();
//      balances.dispose();
//      accountInfo.dispose();
//      executionReports.dispose();
//    }

    exchange.disconnect().blockingAwait();
  }

  private static Disposable orderbooks(StreamingExchange exchange, String identifier) {
    return exchange
        .getStreamingMarketDataService()
        .getOrderBook(CurrencyPair.LTC_BTC)
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
}
