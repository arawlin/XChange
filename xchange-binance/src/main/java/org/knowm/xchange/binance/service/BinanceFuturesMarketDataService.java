package org.knowm.xchange.binance.service;

import org.knowm.xchange.binance.BinanceFutures;
import org.knowm.xchange.binance.BinanceFuturesExchange;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.service.marketdata.MarketDataService;

public class BinanceFuturesMarketDataService extends BinanceFuturesMarketDataServiceRaw
    implements MarketDataService {

  public BinanceFuturesMarketDataService(
      BinanceFuturesExchange exchange,
      BinanceFutures binance,
      ResilienceRegistries resilienceRegistries) {
    super(exchange, binance, resilienceRegistries);
  }
}
