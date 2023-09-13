package org.knowm.xchange.binance.service;

import static org.knowm.xchange.binance.BinanceResilience.REQUEST_WEIGHT_RATE_LIMITER;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.knowm.xchange.binance.BinanceAdapters;
import org.knowm.xchange.binance.BinanceFuturesCoin;
import org.knowm.xchange.binance.BinanceFuturesCommon;
import org.knowm.xchange.binance.BinanceFuturesExchange;
import org.knowm.xchange.binance.BinanceFuturesOld;
import org.knowm.xchange.binance.BinanceFuturesUSDT;
import org.knowm.xchange.binance.dto.marketdata.BinanceAggTrades;
import org.knowm.xchange.binance.dto.marketdata.BinanceFuturesFundingRate;
import org.knowm.xchange.binance.dto.marketdata.BinanceFuturesOpenInterest;
import org.knowm.xchange.binance.dto.marketdata.BinanceFuturesPremiumIndex;
import org.knowm.xchange.binance.dto.marketdata.BinanceGlobalLongShortAccountRatio;
import org.knowm.xchange.binance.dto.marketdata.BinanceKline;
import org.knowm.xchange.binance.dto.marketdata.BinanceOrderbook;
import org.knowm.xchange.binance.dto.marketdata.BinanceTakerLongShortRatio;
import org.knowm.xchange.binance.dto.marketdata.BinanceTopLongShortAccountRatio;
import org.knowm.xchange.binance.dto.marketdata.BinanceTopLongShortPositionRatio;
import org.knowm.xchange.binance.dto.marketdata.KlineInterval;
import org.knowm.xchange.binance.dto.trade.BinanceOrder;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.instrument.Instrument;
import org.knowm.xchange.utils.StreamUtils;

public class BinanceFuturesMarketDataServiceRaw extends BinanceFuturesBaseService {

  protected BinanceFuturesMarketDataServiceRaw(
      BinanceFuturesExchange exchange,
      BinanceFuturesOld binance,
      BinanceFuturesCommon binanceCommon,
      ResilienceRegistries resilienceRegistries) {
    super(exchange, binance, binanceCommon, resilienceRegistries);
  }

  public List<BinanceFuturesPremiumIndex> premiumIndexUSDT() throws IOException {
    return decorateApiCall(
        () -> ((BinanceFuturesUSDT) binance).premiumIndex())
        .withRetry(retry("premiumIndex"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), 1)
        .call();
  }

  public BinanceFuturesPremiumIndex premiumIndexUSDT(String pair) throws IOException {
    return decorateApiCall(
        () -> ((BinanceFuturesUSDT) binance).premiumIndex(pair))
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

  /**
   * Deprecated. The endpoint has been out of maintenance
   *
   * @param symbol
   * @param startTime
   * @param endTime
   * @param limit
   * @return
   * @throws IOException
   */
  @Deprecated
  public List<BinanceOrder> allForceOrders(
      String symbol, Long startTime, Long endTime, Integer limit) throws IOException {
    return decorateApiCall(() -> binance.allForceOrders(symbol, startTime, endTime, limit))
        .withRetry(retry("allForceOrders"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), 5)
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

  public BinanceOrderbook getBinanceOrderbook(Instrument pair, Integer limit) throws IOException {
    return decorateApiCall(() -> binance.depth(BinanceAdapters.toSymbol(pair), limit))
        .withRetry(retry("depth"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), depthPermits(limit))
        .call();
  }

  public List<BinanceAggTrades> aggTrades(
      CurrencyPair pair, Long fromId, Long startTime, Long endTime, Integer limit)
      throws IOException {
    return decorateApiCall(
        () ->
            binance.aggTrades(
                BinanceAdapters.toSymbol(pair), fromId, startTime, endTime, limit))
        .withRetry(retry("aggTrades"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), aggTradesPermits(limit))
        .call();
  }

  public BinanceKline lastKline(CurrencyPair pair, KlineInterval interval) throws IOException {
    return klines(pair, interval, 1, null, null).stream().collect(StreamUtils.singletonCollector());
  }

  public List<BinanceKline> klines(CurrencyPair pair, KlineInterval interval) throws IOException {
    return klines(pair, interval, null, null, null);
  }

  public List<BinanceKline> klines(
      CurrencyPair pair, KlineInterval interval, Integer limit, Long startTime, Long endTime)
      throws IOException {
    return klines(BinanceAdapters.toSymbol(pair), interval, limit, startTime, endTime);
  }

  public List<BinanceKline> klines(
      String pair, KlineInterval interval, Integer limit, Long startTime, Long endTime)
      throws IOException {
    List<Object[]> raw =
        decorateApiCall(() -> binance.klines(pair, interval.code(), limit, startTime, endTime))
            .withRetry(retry("klines"))
            .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), klinePermits(limit))
            .call();
    return raw.stream()
        .map(obj -> new BinanceKline(BinanceAdapters.adaptSymbol(pair, true), interval, obj))
        .collect(Collectors.toList());
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

  protected int aggTradesPermits(Integer limit) {
    if (limit != null && limit > 500) {
      return 2;
    }
    return 1;
  }

  protected int klinePermits(Integer limit) {
    if (limit == null || limit <= 50) {
      return 1;
    } else if (limit <= 100) {
      return 1;
    } else if (limit < 500) {
      return 2;
    } else if (limit <= 1000) {
      return 5;
    } else {
      return 10;
    }
  }

}
