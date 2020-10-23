package org.knowm.xchange.binance.service.marketdata;

import org.junit.Before;
import org.junit.Test;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.binance.BinanceExchangeSpecification;
import org.knowm.xchange.binance.BinanceFuturesExchange;
import org.knowm.xchange.binance.BinanceFuturesUSDT;
import org.knowm.xchange.binance.dto.FuturesSettleType;
import org.knowm.xchange.binance.dto.marketdata.*;
import org.knowm.xchange.binance.service.BinanceFuturesMarketDataServiceRaw;
import org.knowm.xchange.utils.Assert;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/** Created by lin on 2020-10-20. */
public class BinanceFuturesMarketDataServiceRawTest {

  private BinanceFuturesExchange exchange;
  private BinanceFuturesMarketDataServiceRaw service;

  private Long startTime;
  private Long endTime;

  @Before
  public void setUp() throws Exception {
    BinanceExchangeSpecification spec =
        new BinanceExchangeSpecification(BinanceFuturesExchange.class);

    // NOTICE: must set they in code
    spec.setSslUri(BinanceFuturesUSDT.URL);
    spec.setHost(BinanceFuturesUSDT.HOST);
    spec.setFuturesSettleType(FuturesSettleType.USDT);

    spec.setPort(80);
    spec.setExchangeName("BinanceFutures");
    spec.setExchangeDescription("Binance Futures Exchange.");
    spec.setShouldLoadRemoteMetaData(false);

    spec.setProxyHost("192.168.1.100");
    spec.setProxyPort(1081);

    exchange = (BinanceFuturesExchange) ExchangeFactory.INSTANCE.createExchange(spec);
    service = (BinanceFuturesMarketDataServiceRaw) exchange.getMarketDataService();

    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    endTime = c.getTimeInMillis();

    c.add(Calendar.DATE, -1);
    startTime = c.getTimeInMillis();

  }

  @Test
  public void premiumIndex() throws IOException {
    List<BinanceFuturesPremiumIndex> ls = service.premiumIndexCoin("BTCUSD_PERP", "");
    Assert.notNull(ls, "");
  }

  @Test
  public void fundingRate() throws ParseException, IOException {
    List<BinanceFuturesFundingRate> ls = service.fundingRate("BTCUSD_PERP", startTime, endTime, 10);
    Assert.notNull(ls, "");
  }

  @Test
  public void openInterestHist() throws IOException {
    List<BinanceFuturesOpenInterest> ls = service.openInterestHist("ZECUSDT", KlineInterval.m15, 30, startTime, endTime);
    Assert.notNull(ls, "");
  }

  @Test
  public void topLongShortAccountRatio() throws IOException {
    List<BinanceTopLongShortAccountRatio> ls = service.topLongShortAccountRatio("ZECUSDT", KlineInterval.m15, 30, startTime, endTime);
    Assert.notNull(ls, "");
  }

}
