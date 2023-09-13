package org.knowm.xchange.binance;

import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.binance.dto.meta.BinanceTime;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.client.ResilienceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import si.mazi.rescu.SynchronizedValueFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;

import static org.knowm.xchange.binance.BinanceResilience.REQUEST_WEIGHT_RATE_LIMITER;

public class BinanceFuturesTimestampFactory implements SynchronizedValueFactory<Long> {

  private static final Logger LOG = LoggerFactory.getLogger(BinanceFuturesTimestampFactory.class);

  private final BinanceFutures binance;
  private final ExchangeSpecification.ResilienceSpecification resilienceSpecification;
  private final ResilienceRegistries resilienceRegistries;

  private Long deltaServerTimeExpire;
  private Long deltaServerTime;

  public BinanceFuturesTimestampFactory(
      BinanceFutures binance,
      ExchangeSpecification.ResilienceSpecification resilienceSpecification,
      ResilienceRegistries resilienceRegistries) {
    this.binance = binance;
    this.resilienceSpecification = resilienceSpecification;
    this.resilienceRegistries = resilienceRegistries;
  }

  @Override
  public Long createValue() {

    return System.currentTimeMillis();
  }

  public void clearDeltaServerTime() {
    deltaServerTime = null;
  }

  public long deltaServerTime() throws IOException {
    long serverTime = binanceTime().getServerTimeRaw();
    long systemTime = System.currentTimeMillis();

    deltaServerTime = serverTime - systemTime;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    LOG.info(
        "deltaServerTime: {} - {} => {}",
        df.format(serverTime),
        df.format(systemTime),
        deltaServerTime);

    return deltaServerTime;
  }

  private BinanceTime binanceTime() throws IOException {
    return ResilienceUtils.decorateApiCall(resilienceSpecification, () -> binance.time())
        .withRetry(resilienceRegistries.retries().retry("time"))
        .withRateLimiter(
            resilienceRegistries.rateLimiters().rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
  }
}
