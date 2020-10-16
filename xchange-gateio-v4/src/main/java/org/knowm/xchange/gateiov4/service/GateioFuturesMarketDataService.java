package org.knowm.xchange.gateiov4.service;

import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.gateiov4.GateioFuturesExchange;
import org.knowm.xchange.service.marketdata.MarketDataService;

/** Created by lin on 2020-10-15. */
public class GateioFuturesMarketDataService extends GateioFuturesMarketDataServiceRaw
    implements MarketDataService {

  public GateioFuturesMarketDataService(
      GateioFuturesExchange exchange, ResilienceRegistries resilienceRegistries) {
    super(exchange, resilienceRegistries);
  }
}
