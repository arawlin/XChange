package org.knowm.xchange.binance.service.marketdata;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.binance.BinanceExchangeSpecification;
import org.knowm.xchange.binance.BinanceFuturesExchange;
import org.knowm.xchange.binance.BinanceFuturesUSDT;
import org.knowm.xchange.binance.dto.FuturesSettleType;
import org.knowm.xchange.binance.dto.marketdata.BinanceFuturesFundingRate;
import org.knowm.xchange.binance.dto.marketdata.BinanceFuturesPremiumIndex;
import org.knowm.xchange.binance.service.BinanceFuturesMarketDataServiceRaw;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.utils.Assert;

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
    BinanceFuturesPremiumIndex i = service.premiumIndex(CurrencyPair.BTC_USDT);
    Assert.notNull(i, "");
  }

  @Test
  public void fundingRate() throws ParseException, IOException {
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    long startTime = f.parse("2020-10-18 00:00").getTime();
    long endTime = f.parse("2020-10-19 00:00").getTime();
    List<BinanceFuturesFundingRate> ls =
        service.fundingRate(CurrencyPair.BTC_USDT, startTime, endTime, 10);
    Assert.notNull(ls, "");
  }
}
