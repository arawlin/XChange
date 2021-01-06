package org.knowm.xchange.binance.service;

import org.knowm.xchange.binance.*;
import org.knowm.xchange.binance.dto.FuturesSettleType;
import org.knowm.xchange.binance.dto.account.BinanceFutureAccount;
import org.knowm.xchange.client.ResilienceRegistries;
import si.mazi.rescu.ParamsDigest;

import java.io.IOException;
import java.util.Optional;

import static org.knowm.xchange.binance.BinanceResilience.REQUEST_WEIGHT_RATE_LIMITER;

/**
 * Created by lin on 2021-01-06.
 */
public class BinanceFuturesAccountServiceRaw extends BinanceFuturesBaseService {

  protected BinanceFuturesAccountServiceRaw(BinanceFuturesExchange exchange, BinanceFutures binance, BinanceFuturesCommon binanceCommon, ResilienceRegistries resilienceRegistries) {
    super(exchange, binance, binanceCommon, resilienceRegistries);
  }

  public BinanceFutureAccount getAccountInfo(String apiKeyAnother, ParamsDigest signatureAnother) throws IOException {
    if (futuresSettleType == FuturesSettleType.USDT) {
      return decorateApiCall(
          () -> ((BinanceFuturesUSDT) binance).accountInfo(
              getRecvWindow(),
              getTimestampFactory(),
              Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
              Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
          .withRetry(retry("getAccountInfo"))
          .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), 5)
          .call();
    } else if (futuresSettleType == FuturesSettleType.COIN) {
      return decorateApiCall(
          () ->
              ((BinanceFuturesCoin) binance).accountInfo(
                  getRecvWindow(),
                  getTimestampFactory(),
                  Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
                  Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
          .withRetry(retry("getAccountInfo"))
          .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), 5)
          .call();
    }
    return null;
  }

}
