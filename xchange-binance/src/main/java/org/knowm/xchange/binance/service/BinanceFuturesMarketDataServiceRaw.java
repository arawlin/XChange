package org.knowm.xchange.binance.service;

import static org.knowm.xchange.binance.BinanceResilience.REQUEST_WEIGHT_RATE_LIMITER;

import java.io.IOException;
import java.util.List;
import org.knowm.xchange.binance.*;
import org.knowm.xchange.binance.dto.marketdata.*;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.currency.CurrencyPair;

public class BinanceFuturesMarketDataServiceRaw
    extends BinanceFuturesBaseService<BinanceFuturesExchange> {

  protected final BinanceFutures binance;
  protected final BinanceFuturesCommon binanceCommon;
  protected final BinanceExchangeSpecification specification;

  protected BinanceFuturesMarketDataServiceRaw(
      BinanceFuturesExchange exchange,
      BinanceFutures binance,
      BinanceFuturesCommon binanceCommon,
      ResilienceRegistries resilienceRegistries) {
    super(exchange, resilienceRegistries);

    this.binance = binance;
    this.binanceCommon = binanceCommon;
    this.specification = (BinanceExchangeSpecification) exchange.getExchangeSpecification();
  }

  public BinanceFuturesPremiumIndex premiumIndexUSDT(CurrencyPair pair) throws IOException {
    return decorateApiCall(
            () -> ((BinanceFuturesUSDT) binance).premiumIndex(BinanceAdapters.toSymbol(pair)))
        .withRetry(retry("premiumIndex"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), 1)
        .call();
  }

  public List<BinanceFuturesPremiumIndex> premiumIndexCoin(String symbol, String pair)
      throws IOException {
    return decorateApiCall(() -> ((BinanceFuturesCoin) binance).premiumIndex(symbol, pair))
        .withRetry(retry("premiumIndex"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), 1)
        .call();
  }

  public List<BinanceFuturesFundingRate> fundingRate(
      String symbol, Long startTime, Long endTime, Integer limit) throws IOException {
    return decorateApiCall(() -> binance.fundingRate(symbol, startTime, endTime, limit))
        .withRetry(retry("fundingRate"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), 1)
        .call();
  }

  public List<BinanceFuturesOpenInterest> openInterestHist(
      String symbol, KlineInterval period, Integer limit, Long startTime, Long endTime)
      throws IOException {
    return decorateApiCall(
            () -> binanceCommon.openInterestHist(symbol, period.code(), limit, startTime, endTime))
        .withRetry(retry("openInterestHist"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), 1)
        .call();
  }

  public List<BinanceTopLongShortAccountRatio> topLongShortAccountRatio(
      String symbol, KlineInterval period, Integer limit, Long startTime, Long endTime)
      throws IOException {
    return decorateApiCall(
            () ->
                binanceCommon.topLongShortAccountRatio(
                    symbol, period.code(), limit, startTime, endTime))
        .withRetry(retry("topLongShortAccountRatio"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), 1)
        .call();
  }

  public List<BinanceTopLongShortPositionRatio> topLongShortPositionRatio(
      String symbol, KlineInterval period, Integer limit, Long startTime, Long endTime)
      throws IOException {
    return decorateApiCall(
            () ->
                binanceCommon.topLongShortPositionRatio(
                    symbol, period.code(), limit, startTime, endTime))
        .withRetry(retry("topLongShortAccountRatio"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), 1)
        .call();
  }

  public List<BinanceGlobalLongShortAccountRatio> globalLongShortAccountRatio(
      String symbol, KlineInterval period, Integer limit, Long startTime, Long endTime)
      throws IOException {
    return decorateApiCall(
            () ->
                binanceCommon.globalLongShortAccountRatio(
                    symbol, period.code(), limit, startTime, endTime))
        .withRetry(retry("topLongShortAccountRatio"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), 1)
        .call();
  }

  public List<BinanceTakerLongShortRatio> takerlongshortRatio(
      String symbol, KlineInterval period, Integer limit, Long startTime, Long endTime)
      throws IOException {
    return decorateApiCall(
            () ->
                binanceCommon.takerlongshortRatio(symbol, period.code(), limit, startTime, endTime))
        .withRetry(retry("topLongShortAccountRatio"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), 1)
        .call();
  }

  public BinanceOrderbook getBinanceOrderbook(CurrencyPair pair, Integer limit) throws IOException {
    return decorateApiCall(() -> binance.depth(BinanceAdapters.toSymbol(pair), limit))
        .withRetry(retry("depth"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), depthPermits(limit))
        .call();
  }

  protected int depthPermits(Integer limit) {
    if (limit == null || limit <= 50) {
      return 2;
    } else if (limit <= 100) {
      return 5;
    } else if (limit <= 500) {
      return 10;
    } else if (limit <= 1000) {
      return 20;
    }
    return 50;
  }
}
