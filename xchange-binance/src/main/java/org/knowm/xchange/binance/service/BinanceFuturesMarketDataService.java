package org.knowm.xchange.binance.service;

import org.knowm.xchange.binance.BinanceFutures;
import org.knowm.xchange.binance.BinanceFuturesCommon;
import org.knowm.xchange.binance.BinanceFuturesExchange;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.service.marketdata.MarketDataService;

public class BinanceFuturesMarketDataService extends BinanceFuturesMarketDataServiceRaw
    implements MarketDataService {

  public BinanceFuturesMarketDataService(
      BinanceFuturesExchange exchange,
      BinanceFutures binance,
      BinanceFuturesCommon binanceCommon,
      ResilienceRegistries resilienceRegistries) {
    super(exchange, binance, binanceCommon, resilienceRegistries);
  }
}
