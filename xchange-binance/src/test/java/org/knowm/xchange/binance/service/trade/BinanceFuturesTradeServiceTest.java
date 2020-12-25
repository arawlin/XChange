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
import org.knowm.xchange.binance.service.BinanceFuturesTradeServiceRaw;
import org.knowm.xchange.currency.CurrencyPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;

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
  public void setUp() throws Exception {
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
        null
    );

    Assume.assumeNotNull(o);
  }

  @Test
  public void newOrder() throws IOException {
    BinanceFutureNewOrder o = service.newOrder(
        CurrencyPair.BTC_USDT,
        OrderSide.SELL,
        PositionSide.LONG,
        FutureOrderType.LIMIT,
        null,
        new BigDecimal("0.005"),
        new BigDecimal("23024.76"),
        System.currentTimeMillis() + "",
        null,
        null,
        null,
        null,
        TimeInForce.GTC,
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
}
