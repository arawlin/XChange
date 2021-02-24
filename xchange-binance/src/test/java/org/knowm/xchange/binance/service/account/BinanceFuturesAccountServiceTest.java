package org.knowm.xchange.binance.service.account;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.binance.BinanceExchangeSpecification;
import org.knowm.xchange.binance.BinanceFuturesCoin;
import org.knowm.xchange.binance.BinanceFuturesExchange;
import org.knowm.xchange.binance.BinanceFuturesUSDT;
import org.knowm.xchange.binance.dto.FuturesSettleType;
import org.knowm.xchange.binance.dto.account.BinanceFutureAccount;
import org.knowm.xchange.binance.service.BinanceFuturesAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by lin on 2020-10-20.
 */
public class BinanceFuturesAccountServiceTest {

  private static final Logger log = LoggerFactory.getLogger(BinanceFuturesAccountServiceTest.class);

  private BinanceFuturesExchange exchange;
  private BinanceFuturesAccountService service;

  private String apiKey;
  private String secretKey;

  @Before
  public void setUp() {
    apiKey = System.getProperty("apiKey");
    secretKey = System.getProperty("secretKey");

    BinanceExchangeSpecification spec = new BinanceExchangeSpecification(BinanceFuturesExchange.class);

    // NOTICE: must set they in code
    spec.setSslUri(BinanceFuturesCoin.URL_TEST);
    spec.setHost(BinanceFuturesCoin.HOST_TEST);
    spec.setFuturesSettleType(FuturesSettleType.COIN);

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
    service = (BinanceFuturesAccountService) exchange.getAccountService();
  }

  @Test
  public void getAccountInfo() throws IOException {
    BinanceFutureAccount a = service.getAccountInfo(null, null);
    Assume.assumeNotNull(a);
  }
}
