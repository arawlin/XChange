package org.knowm.xchange.okcoin.v3.service;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.okcoin.OkexExchangeV3;
import org.knowm.xchange.okcoin.v3.dto.marketdata.OkexFutureOrderBook;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by lin on 2021-01-29.
 */
public class OkexMarketDataServiceRawTest {

  private OkexExchangeV3 exchange;
  private OkexMarketDataService marketDataService;

  @Before
  public void setup() {
    ExchangeSpecification spec = new ExchangeSpecification(OkexExchangeV3.class);
    spec.setSslUri("https://www.okex.com");
    spec.setHost("www.okex.com");
    spec.setExchangeName("OKEx");
    spec.setExchangeDescription("OKEx is a globally oriented crypto-currency trading platform.");

    spec.setProxyHost("192.168.1.100");
    spec.setProxyPort(1081);

    exchange = (OkexExchangeV3) ExchangeFactory.INSTANCE.createExchange(spec);

    marketDataService = (OkexMarketDataService) exchange.getMarketDataService();

  }

  @Test
  public void getFuturesOrderBook() throws IOException {
    OkexFutureOrderBook e = marketDataService.getFuturesOrderBook("LINK-USD-210205", 50, "0.001");
    Assume.assumeNotNull(e);
  }

  @Test
  public void getSwapOrderBook() throws IOException {
    OkexFutureOrderBook e = marketDataService.getSwapOrderBook("BTC-USDT-SWAP", 50, null);
    Assume.assumeNotNull(e);
  }
}