package org.knowm.xchange.binance.service.trade;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.binance.BinanceExchangeSpecification;
import org.knowm.xchange.binance.BinanceFuturesExchange;
import org.knowm.xchange.binance.BinanceFuturesTimestampFactory;
import org.knowm.xchange.binance.BinanceFuturesUSDT;
import org.knowm.xchange.binance.dto.FuturesSettleType;
import org.knowm.xchange.binance.dto.trade.*;
import org.knowm.xchange.binance.service.BinanceFuturesTradeService;
import org.knowm.xchange.binance.service.BinanceHmacDigest;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.StopOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by lin on 2020-10-20.
 */
public class BinanceFuturesTradeServiceTest {

  private static final Logger log = LoggerFactory.getLogger(BinanceFuturesTradeServiceTest.class);

  private BinanceFuturesExchange exchange;
  private BinanceFuturesTradeService service;

  private BinanceFuturesTimestampFactory timestampFactory;

  static String apiKey;
  static String secretKey;

  @Before
  public void setUp() {
    apiKey = System.getProperty("apiKey");
    secretKey = System.getProperty("secretKey");

    BinanceExchangeSpecification spec = new BinanceExchangeSpecification(BinanceFuturesExchange.class);

    // NOTICE: must set they in code
    spec.setSslUri(BinanceFuturesUSDT.URL_TEST);
    spec.setHost(BinanceFuturesUSDT.HOST_TEST);
    spec.setFuturesSettleType(FuturesSettleType.USDT);

    spec.setPort(80);
    spec.setExchangeName("BinanceFutures");
    spec.setExchangeDescription("Binance Futures Exchange.");
    spec.setShouldLoadRemoteMetaData(false);

    spec.setApiKey(apiKey);
    spec.setSecretKey(secretKey);

    spec.setProxyHost("192.168.1.100");
    spec.setProxyPort(1081);

    spec.setExchangeSpecificParametersItem("recvWindow", 10000L);

    exchange = (BinanceFuturesExchange) ExchangeFactory.INSTANCE.createExchange(spec);
    service = (BinanceFuturesTradeService) exchange.getTradeService();

    timestampFactory = (BinanceFuturesTimestampFactory) exchange.getTimestampFactory();
  }

  @Test
  public void testOpenOrders() throws IOException {
    List<BinanceFutureOrder> ls = service.openOrders("BTCUSDT", "", null, null);
    Assume.assumeNotNull(ls);
  }

  @Test
  public void testNewOrder() throws IOException {
    // check server time
    long delta = timestampFactory.deltaServerTime();
    log.info("delt time is {}", delta);

    Object o = service.testNewOrder(
        CurrencyPair.BTC_USDT,
        OrderSide.BUY,
        PositionSide.LONG,
        FutureOrderType.LIMIT,
        null,
        new BigDecimal("0.01"),
        new BigDecimal("22524.76"),
        "10000",
        null,
        null,
        null,
        null,
        TimeInForce.GTC,
        null,
        null,
        null,
        null,
        null
    );

    Assume.assumeNotNull(o);
  }

  @Test
  public void newOrder() throws IOException {
    BinanceFutureNewOrder o = service.newOrder(
        CurrencyPair.BTC_USDT,
        OrderSide.SELL,
        PositionSide.SHORT,
        FutureOrderType.LIMIT,
        null,
        new BigDecimal("0.005"),
        new BigDecimal("53024.76"),
        System.currentTimeMillis() + "",
        null,
        null,
        null,
        null,
        TimeInForce.GTC,
        null,
        null,
        null,
        null,
        null
    );

    Assume.assumeNotNull(o);
  }

  @Test
  public void getPositionSide() throws IOException {
    BinancePositionSide p = service.getPositionSide(null, null);
    Assume.assumeNotNull(p);
  }

  @Test
  public void placeTestOrderLimit() throws IOException {
    LimitOrder o = sampleLimitOrder();

    Object obj = service.placeTestOrder(
        FutureOrderType.LIMIT,
        o,
        o.getLimitPrice(),
        null,
        null,
        null
    );
    Assume.assumeNotNull(obj);
  }

  @Test
  public void placeOrderLimit() throws IOException {
    LimitOrder o = sampleLimitOrder();

    String i = service.placeLimitOrder(o);
    Assume.assumeNotNull(i);
  }

  @Test
  public void placeOrderMarket() throws IOException {
    MarketOrder o = sampleMarketOrder();

    String i = service.placeMarketOrder(o);
    Assume.assumeNotNull(i);
  }

  @Test
  public void placeOrderStop() throws IOException {
    StopOrder o = sampleStopOrder();

    String i = service.placeStopOrder(o);
    Assume.assumeNotNull(i);
  }

  private LimitOrder sampleLimitOrder() {
    LimitOrder o = new LimitOrder(
        Order.OrderType.BID,
//        Order.OrderType.EXIT_BID,
//        Order.OrderType.ASK,
//        Order.OrderType.EXIT_ASK,
        new BigDecimal("0.01"),
        CurrencyPair.BTC_USDT,
        "",
        new Date(),
        new BigDecimal("30000")
    );
    o.setUserReference("1234" + System.currentTimeMillis());
    o.addOrderFlag(TimeInForce.GTC);
    return o;
  }

  private MarketOrder sampleMarketOrder() {
    MarketOrder o = new MarketOrder(
        Order.OrderType.EXIT_BID,
        new BigDecimal("0.002"),
        CurrencyPair.BTC_USDT
    );
    o.setUserReference("1234" + System.currentTimeMillis());
    return o;
  }

  private StopOrder sampleStopOrder() {
    StopOrder o = new StopOrder(
        Order.OrderType.EXIT_BID,
//        Order.OrderType.EXIT_ASK,
        new BigDecimal("0.002"),
        CurrencyPair.BTC_USDT,
        "",
        new Date(),
        new BigDecimal("20000")
    );
//    o.setLimitPrice(new BigDecimal("20001"));
    o.setIntention(StopOrder.Intention.STOP_LOSS);
//    o.setIntention(StopOrder.Intention.TAKE_PROFIT);
    o.setUserReference("1234" + System.currentTimeMillis());

    o.addOrderFlagMap(BinanceOrderFlags.WORKING_TYPE, WorkingType.MARK_PRICE);
    o.addOrderFlag(BinanceOrderFlags.PRICE_PROTECT);

    return o;
  }

  @Test
  public void cancelOrder() throws IOException {
    BinanceFutureOrder o = service.cancelOrder("BTCUSDT", 2601551060L, null, null, null);
    Assume.assumeNotNull(o);
  }

  @Test
  public void orderStatus() throws IOException {
    BinanceFutureOrder o = service.orderStatus(CurrencyPair.BTC_USDT, 2686019171L, null, null, null);
    Assume.assumeNotNull(o);
  }

  @Test
  public void cancelAllOpenOrders() throws IOException {
    boolean isSuccess = service.cancelAllOpenOrders("BTCUSDT", null, null);
    Assume.assumeTrue(isSuccess);
  }

  @Test
  public void allOrders() throws IOException {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    Long endTime = c.getTimeInMillis();

    c.add(Calendar.DATE, -1);
    Long startTime = c.getTimeInMillis();

    List<BinanceFutureOrder> ls = service.allOrders("BTCUSDT", null, null, startTime, endTime, null, null, null);
    Assume.assumeNotNull(ls);
  }

  @Test
  public void userTrades() throws IOException {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    Long endTime = c.getTimeInMillis();

    c.add(Calendar.DATE, -1);
    Long startTime = c.getTimeInMillis();

    List<BinanceFutureTrade> ls = service.userTrades("BTCUSDT", null, startTime, endTime, null, 20, null, null);
    Assume.assumeNotNull(ls);
  }

  @Test
  public void income() throws IOException {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    Long endTime = c.getTimeInMillis();

    c.add(Calendar.MONTH, -1);
    Long startTime = c.getTimeInMillis();

    List<BinanceIncomeRecord> ls = service.income("BTCUSDT", FutureIncomeType.FUNDING_FEE, startTime, endTime, 20, null, null);
    Assume.assumeNotNull(ls);
  }

  @Test
  public void positionRisk() throws IOException {
    List<BinanceFuturePositionRisk> ls = service.positionRisk("BTCUSDT", null, null, null);
    Assume.assumeNotNull(ls);
  }

  @Test
  public void setPositionSide() throws IOException {
    boolean suc = service.setPositionSide(true, apiKey, BinanceHmacDigest.createInstance(secretKey));
    Assume.assumeTrue(suc);
  }

  @Test
  public void setLeverage() throws IOException {
    BinanceLeverageStatus res = service.setLeverage("BTCUSDT", 6, apiKey, BinanceHmacDigest.createInstance(secretKey));
    Assume.assumeNotNull(res);
  }

  @Test
  public void setPositionMargin() throws IOException {
    BinanceResponsePositionMargin res = service.setPositionMargin("BTCUSDT", PositionSide.LONG, new BigDecimal(100), 1, apiKey, BinanceHmacDigest.createInstance(secretKey));
    Assume.assumeNotNull(res);
  }

}
