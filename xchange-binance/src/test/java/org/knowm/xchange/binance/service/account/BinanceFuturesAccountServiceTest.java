package org.knowm.xchange.binance.service.account;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.binance.BinanceExchangeSpecification;
import org.knowm.xchange.binance.BinanceFuturesExchange;
import org.knowm.xchange.binance.BinanceFuturesUSDT;
import org.knowm.xchange.binance.dto.FuturesSettleType;
import org.knowm.xchange.binance.dto.account.BinanceFutureAccount;
import org.knowm.xchange.binance.dto.account.IfNewUser;
import org.knowm.xchange.binance.dto.account.RebateFutureInfo;
import org.knowm.xchange.binance.service.BinanceFuturesAccountService;
import org.knowm.xchange.binance.service.BinanceHmacDigest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by lin on 2020-10-20.
 */
public class BinanceFuturesAccountServiceTest {

  private static final Logger log = LoggerFactory.getLogger(BinanceFuturesAccountServiceTest.class);

  private BinanceFuturesExchange exchange;
  private BinanceFuturesAccountService service;

  private String apiKey;
  private String apiSecret;
  private String apiAgentCode;

  @Before
  public void setUp() {
    apiKey = System.getProperty("apiKey");
    apiSecret = System.getProperty("apiSecret");
    apiAgentCode = System.getProperty("apiAgentCode");

    BinanceExchangeSpecification spec = new BinanceExchangeSpecification(BinanceFuturesExchange.class);

    // NOTICE: must set they in code
    spec.setSslUri(BinanceFuturesUSDT.URL);
    spec.setHost(BinanceFuturesUSDT.HOST);
    spec.setFuturesSettleType(FuturesSettleType.USDT);

    spec.setPort(80);
    spec.setExchangeName("BinanceFutures");
    spec.setExchangeDescription("Binance Futures Exchange.");
    spec.setShouldLoadRemoteMetaData(false);

    spec.setApiKey(apiKey);
    spec.setSecretKey(apiSecret);

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

  private static final Integer REFERRAL_COIN_TYPE = 1;

  @Test
  public void testIfNewUser() throws IOException {
    IfNewUser u = service.ifNewUser(apiAgentCode, REFERRAL_COIN_TYPE, apiKey, BinanceHmacDigest.createInstance(apiSecret));
    Assume.assumeNotNull(u);
  }

  @Test
  public void userCustomizationGet() throws IOException {
    String s = service.userCustomizationGet(apiAgentCode, apiKey, BinanceHmacDigest.createInstance(apiSecret));
    Assume.assumeNotNull(s);
  }

  @Test
  public void userCustomizationSet() throws IOException {
    String s = service.userCustomizationSet(apiAgentCode, "test_future_2222222", apiKey, BinanceHmacDigest.createInstance(apiSecret));
    Assume.assumeNotNull(s);
  }

  @Test
  public void rebateRecentRecord() throws IOException {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    Long endTime = c.getTimeInMillis();

    c.add(Calendar.DATE, -7);
    Long startTime = c.getTimeInMillis();

    List<RebateFutureInfo> ls = service.rebateRecentRecord(null, REFERRAL_COIN_TYPE, startTime, endTime, 500, apiKey, BinanceHmacDigest.createInstance(apiSecret));
    Assume.assumeNotNull(ls);
  }

}
