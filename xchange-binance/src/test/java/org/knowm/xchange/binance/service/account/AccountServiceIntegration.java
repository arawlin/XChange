package org.knowm.xchange.binance.service.account;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.binance.BinanceExchange;
import org.knowm.xchange.binance.dto.account.*;
import org.knowm.xchange.binance.service.BinanceAccountService;
import org.knowm.xchange.binance.service.BinanceAccountServiceRaw;
import org.knowm.xchange.binance.service.BinanceHmacDigest;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.account.FundingRecord;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.dto.meta.CurrencyMetaData;
import org.knowm.xchange.dto.meta.CurrencyPairMetaData;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.utils.StreamUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AccountServiceIntegration {

  private BinanceExchange exchange;
  private BinanceAccountService service;

  private String apiKey;
  private String apiSecret;
  private String apiAgentCode;

  @Before
  public void before() {
    apiKey = System.getProperty("apiKey");
    apiSecret = System.getProperty("apiSecret");
    apiAgentCode = System.getProperty("apiAgentCode");

    ExchangeSpecification spec = new ExchangeSpecification(BinanceExchange.class);
    spec.setSslUri("https://api.binance.com");
    spec.setHost("www.binance.com");
    spec.setPort(80);
    spec.setExchangeName("Binance");
    spec.setExchangeDescription("Binance Exchange.");

    spec.setShouldLoadRemoteMetaData(false);

    spec.setApiKey(apiKey);
    spec.setSecretKey(apiSecret);

    spec.setProxyHost("192.168.1.100");
    spec.setProxyPort(1081);

    spec.setExchangeSpecificParametersItem("recvWindow", 10000L);

    exchange = (BinanceExchange) ExchangeFactory.INSTANCE.createExchange(spec);
    service = (BinanceAccountService) exchange.getAccountService();
  }

  @Test
  public void testMetaData() throws Exception {

    Map<CurrencyPair, CurrencyPairMetaData> currencyPairs =
        exchange.getExchangeMetaData().getCurrencyPairs();
    Map<Currency, CurrencyMetaData> currencies = exchange.getExchangeMetaData().getCurrencies();
    CurrencyPair currPair;
    Currency curr;

    currPair =
        currencyPairs.keySet().stream()
            .filter(cp -> "ETH/BTC".equals(cp.toString()))
            .collect(StreamUtils.singletonCollector());
    Assert.assertNotNull(currPair);

    currPair =
        currencyPairs.keySet().stream()
            .filter(cp -> "IOTX/ETH".equals(cp.toString()))
            .collect(StreamUtils.singletonCollector());
    Assert.assertNotNull(currPair);

    curr =
        currencies.keySet().stream()
            .filter(c -> Currency.BTC.equals(c))
            .collect(StreamUtils.singletonCollector());
    Assert.assertNotNull(curr);

    curr =
        currencies.keySet().stream()
            .filter(c -> c.equals(new Currency("IOTX")))
            .collect(StreamUtils.singletonCollector());
    Assert.assertNotNull(curr);
  }

  @Test
  public void testBalances() throws Exception {

    Wallet wallet = service.getAccountInfo().getWallet();
    Assert.assertNotNull(wallet);

    Map<Currency, Balance> balances = wallet.getBalances();
    for (Entry<Currency, Balance> entry : balances.entrySet()) {
      Currency curr = entry.getKey();
      Balance bal = entry.getValue();
      if (0 < bal.getAvailable().doubleValue()) {
        Assert.assertSame(curr, bal.getCurrency());
        Assert.assertSame(Currency.getInstance(curr.getCurrencyCode()), bal.getCurrency());
      }
    }
  }

  @Test
  public void testWithdrawalHistory() throws Exception {

    TradeHistoryParams params = service.createFundingHistoryParams();
    List<FundingRecord> fundingHistory = service.getFundingHistory(params);
    Assert.assertNotNull(fundingHistory);

    for (FundingRecord record : fundingHistory) {
      Assert.assertTrue(record.getAmount().compareTo(BigDecimal.ZERO) > 0);
    }
  }

  @Test
  public void testAccount() throws IOException {
    BinanceAccountInformation a = service.account(apiKey, BinanceHmacDigest.createInstance(apiSecret));
    Assume.assumeNotNull(a);
  }

  @Test
  public void testIfNewUser() throws IOException {
    IfNewUser u = service.ifNewUser(apiAgentCode, apiKey, BinanceHmacDigest.createInstance(apiSecret));
    Assume.assumeNotNull(u);
  }

  @Test
  public void userCustomizationGet() throws IOException {
    String s = service.userCustomizationGet(apiAgentCode, apiKey, BinanceHmacDigest.createInstance(apiSecret));
    Assume.assumeNotNull(s);
  }

  @Test
  public void userCustomizationSet() throws IOException {
    String s = service.userCustomizationSet(apiAgentCode, "test1111111", apiKey, BinanceHmacDigest.createInstance(apiSecret));
    Assume.assumeNotNull(s);
  }

  @Test
  public void rebateRecentRecord() throws IOException {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    Long endTime = c.getTimeInMillis();

    c.add(Calendar.DATE, -7);
    Long startTime = c.getTimeInMillis();

    List<RebateInfo> ls = service.rebateRecentRecord(null, startTime, endTime, 500, apiKey, BinanceHmacDigest.createInstance(apiSecret));
    Assume.assumeNotNull(ls);
  }

  @Test
  public void assetTransfer() throws IOException {
    String res = service.assetTransfer(AssetTransferType.UMFUTURE_MAIN, "BUSD", new BigDecimal(0.02), apiKey, BinanceHmacDigest.createInstance(apiSecret));
    Assume.assumeNotNull(res);
  }

  @Test
  public void apiRestrictions() throws IOException {
    BinanceRestrictions res = service.apiRestrictions(apiKey, BinanceHmacDigest.createInstance(apiSecret));
    Assume.assumeNotNull(res);
  }

}
