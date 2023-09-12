package org.knowm.xchange.binance.service.marketdata;

import org.junit.*;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.binance.BinanceExchange;
import org.knowm.xchange.binance.BinanceExchangeSpecification;
import org.knowm.xchange.binance.BinanceFuturesUSDT;
import org.knowm.xchange.binance.dto.FuturesSettleType;
import org.knowm.xchange.binance.dto.marketdata.BinanceKline;
import org.knowm.xchange.binance.dto.marketdata.BinanceTicker24h;
import org.knowm.xchange.binance.dto.marketdata.KlineInterval;
import org.knowm.xchange.binance.service.BinanceMarketDataService;
import org.knowm.xchange.binance.service.BinanceMarketDataServiceRaw;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.instrument.Instrument;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MarketDataServiceIntegration {

  static BinanceExchange exchange;
  static MarketDataService marketService;

  @BeforeClass
  public static void beforeClass() {
    ExchangeSpecification spec = new ExchangeSpecification(BinanceExchange.class);

    spec.setSslUri("https://api.binance.com");
    spec.setHost("www.binance.com");
    spec.setPort(80);

    spec.setExchangeName("Binance");
    spec.setExchangeDescription("Binance Exchange.");
    spec.setShouldLoadRemoteMetaData(false);

    spec.setProxyHost("192.168.1.100");
    spec.setProxyPort(1081);

    exchange = (BinanceExchange) ExchangeFactory.INSTANCE.createExchange(spec);

    marketService = exchange.getMarketDataService();
  }

  @Before
  public void before() {
//    Assume.assumeNotNull(exchange.getExchangeSpecification().getApiKey());
  }

  @Test
  public void testTimestamp() throws Exception {

    long serverTime = exchange.getTimestampFactory().createValue();
    Assert.assertTrue(0 < serverTime);
  }

  @Test
  public void testBinanceTicker24h() throws Exception {

    List<BinanceTicker24h> tickers = new ArrayList<>();
    for (Instrument cp : exchange.getExchangeMetaData().getInstruments().keySet()) {
      if (cp.getCounter() == Currency.USDT) {
        tickers.add(getBinanceTicker24h(cp));
      }
    }

    Collections.sort(
        tickers,
        new Comparator<BinanceTicker24h>() {
          @Override
          public int compare(BinanceTicker24h t1, BinanceTicker24h t2) {
            return t2.getPriceChangePercent().compareTo(t1.getPriceChangePercent());
          }
        });

    tickers.stream()
        .forEach(
            t -> {
              System.out.println(
                  t.getCurrencyPair()
                      + " => "
                      + String.format("%+.2f%%", t.getPriceChangePercent()));
            });
  }

  private BinanceTicker24h getBinanceTicker24h(Instrument pair) throws IOException {
    BinanceMarketDataService service = (BinanceMarketDataService) marketService;
    return service.ticker24h(pair);
  }

  @Test
  public void testBinanceKline() throws Exception {
    BinanceMarketDataServiceRaw serviceRaw = (BinanceMarketDataServiceRaw) marketService;
    List<BinanceKline> ls = serviceRaw.klines(CurrencyPair.BTC_USDT, KlineInterval.h1);
    System.out.println(ls);
  }
}
