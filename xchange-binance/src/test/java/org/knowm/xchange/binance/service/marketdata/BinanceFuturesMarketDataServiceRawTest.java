package org.knowm.xchange.binance.service.marketdata;

import org.junit.Before;
import org.junit.Test;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.binance.BinanceExchangeSpecification;
import org.knowm.xchange.binance.BinanceFuturesExchange;
import org.knowm.xchange.binance.BinanceFuturesUSDT;
import org.knowm.xchange.binance.dto.FuturesSettleType;
import org.knowm.xchange.binance.dto.marketdata.BinanceFuturesFundingRate;
import org.knowm.xchange.binance.dto.marketdata.BinanceFuturesOpenInterest;
import org.knowm.xchange.binance.dto.marketdata.BinanceFuturesPremiumIndex;
import org.knowm.xchange.binance.dto.marketdata.KlineInterval;
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
  }

  @Test
  public void premiumIndex() throws IOException {
    List<BinanceFuturesPremiumIndex> ls = service.premiumIndexCoin("BTCUSD_PERP", "");
    Assert.notNull(ls, "");
  }

  @Test
  public void fundingRate() throws ParseException, IOException {
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    long startTime = f.parse("2020-10-18 00:00").getTime();
    long endTime = f.parse("2020-10-19 00:00").getTime();
    List<BinanceFuturesFundingRate> ls = service.fundingRate("BTCUSD_PERP", startTime, endTime, 10);
    Assert.notNull(ls, "");
  }

  @Test
  public void openInterestHist() throws IOException {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    Long now = c.getTimeInMillis();

    c.add(Calendar.DATE, -1);
    Long then = c.getTimeInMillis();

    List<BinanceFuturesOpenInterest> ls = service.openInterestHist("ZECUSDT", KlineInterval.m15, 30, then, now);
    Assert.notNull(ls, "");
  }

}
