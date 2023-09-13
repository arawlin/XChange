package org.knowm.xchange.binance.service;

import org.knowm.xchange.binance.*;
import org.knowm.xchange.binance.dto.BinanceException;
import org.knowm.xchange.binance.dto.FuturesSettleType;
import org.knowm.xchange.binance.dto.account.BinanceFutureAccount;
import org.knowm.xchange.binance.dto.account.IfNewUser;
import org.knowm.xchange.binance.dto.account.RebateFutureInfo;
import org.knowm.xchange.client.ResilienceRegistries;
import si.mazi.rescu.ParamsDigest;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.knowm.xchange.binance.BinanceResilience.REQUEST_WEIGHT_RATE_LIMITER;

/**
 * Created by lin on 2021-01-06.
 */
public class BinanceFuturesAccountServiceRaw extends BinanceFuturesBaseService {

  protected BinanceFuturesAccountServiceRaw(BinanceFuturesExchange exchange, BinanceFuturesOld binance, BinanceFuturesCommon binanceCommon, ResilienceRegistries resilienceRegistries) {
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

  public IfNewUser ifNewUser(String apiAgentCode, Integer type, String apiKeyAnother, ParamsDigest signatureAnother) throws IOException, BinanceException {
    return decorateApiCall(
        () -> binanceCommon.ifNewUser(
            apiAgentCode,
            type,
            getRecvWindow(),
            getTimestampFactory(),
            Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
            Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
        .withRetry(retry("ifNewUser"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
  }

  public String userCustomizationSet(String apiAgentCode, String customerId, String apiKeyAnother, ParamsDigest signatureAnother) throws IOException, BinanceException {
    Map<String, String> res = decorateApiCall(
        () -> binanceCommon.userCustomizationSet(
            apiAgentCode,
            customerId,
            getRecvWindow(),
            getTimestampFactory(),
            Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
            Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
        .withRetry(retry("userCustomizationSet"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();

    if (res == null) {
      return null;
    }
    return res.get("customerId");
  }

  public String userCustomizationGet(String apiAgentCode, String apiKeyAnother, ParamsDigest signatureAnother) throws IOException {
    try {
      Map<String, String> res = decorateApiCall(
          () -> binanceCommon.userCustomizationGet(
              apiAgentCode,
              getRecvWindow(),
              getTimestampFactory(),
              Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
              Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
          .withRetry(retry("userCustomizationSet"))
          .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
          .call();

      if (res == null) {
        return null;
      }
      return res.get("customerId");
    } catch (BinanceException e) {
      return null;
    }
  }

  public List<RebateFutureInfo> rebateRecentRecord(
      String customerId,
      Integer type,
      Long startTime,
      Long endTime,
      Integer limit,
      String apiKeyAnother,
      ParamsDigest signatureAnother
  ) throws IOException {
    try {
      return decorateApiCall(
          () -> binanceCommon.rebateRecentRecord(
              customerId,
              type,
              startTime,
              endTime,
              limit,
              getRecvWindow(),
              getTimestampFactory(),
              Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
              Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
          .withRetry(retry("rebateRecentRecord"))
          .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
          .call();
    } catch (BinanceException e) {
      return null;
    }
  }

}
