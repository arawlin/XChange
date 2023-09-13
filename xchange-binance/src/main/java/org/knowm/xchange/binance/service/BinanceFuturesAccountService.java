package org.knowm.xchange.binance.service;

import org.knowm.xchange.binance.BinanceFutures;
import org.knowm.xchange.binance.BinanceFuturesCommon;
import org.knowm.xchange.binance.BinanceFuturesExchange;
import org.knowm.xchange.binance.BinanceFuturesOld;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.service.account.AccountService;

/**
 * Created by lin on 2021-01-06.
 */
public class BinanceFuturesAccountService extends BinanceFuturesAccountServiceRaw implements AccountService {

  public BinanceFuturesAccountService(BinanceFuturesExchange exchange, BinanceFuturesOld binance, BinanceFuturesCommon binanceCommon, ResilienceRegistries resilienceRegistries) {
    super(exchange, binance, binanceCommon, resilienceRegistries);
  }

}
