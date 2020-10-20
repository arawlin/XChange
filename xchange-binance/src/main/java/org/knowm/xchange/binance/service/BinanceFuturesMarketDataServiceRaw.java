package org.knowm.xchange.binance.service;

import static org.knowm.xchange.binance.BinanceResilience.REQUEST_WEIGHT_RATE_LIMITER;

import java.io.IOException;
import java.util.List;
import org.knowm.xchange.binance.BinanceAdapters;
import org.knowm.xchange.binance.BinanceFutures;
import org.knowm.xchange.binance.BinanceFuturesExchange;
import org.knowm.xchange.binance.dto.marketdata.BinanceFuturesFundingRate;
import org.knowm.xchange.binance.dto.marketdata.BinanceFuturesPremiumIndex;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.currency.CurrencyPair;

public class BinanceFuturesMarketDataServiceRaw
    extends BinanceFuturesBaseService<BinanceFuturesExchange> {

  protected final BinanceFutures binance;

  protected BinanceFuturesMarketDataServiceRaw(
      BinanceFuturesExchange exchange,
      BinanceFutures binance,
      ResilienceRegistries resilienceRegistries) {
    super(exchange, resilienceRegistries);

    this.binance = binance;
  }

  public BinanceFuturesPremiumIndex premiumIndex(CurrencyPair pair) throws IOException {
    return decorateApiCall(() -> binance.premiumIndex(BinanceAdapters.toSymbol(pair)))
        .withRetry(retry("premiumIndex"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), 1)
        .call();
  }

  public List<BinanceFuturesFundingRate> fundingRate(
      CurrencyPair pair, Long startTime, Long endTime, Integer limit) throws IOException {
    return decorateApiCall(
            () -> binance.fundingRate(BinanceAdapters.toSymbol(pair), startTime, endTime, limit))
        .withRetry(retry("fundingRate"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), 1)
        .call();
  }
}
