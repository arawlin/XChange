package org.knowm.xchange.binance.service;

import org.knowm.xchange.binance.*;
import org.knowm.xchange.binance.dto.BinanceException;
import org.knowm.xchange.binance.dto.FuturesSettleType;
import org.knowm.xchange.binance.dto.trade.*;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.currency.CurrencyPair;
import si.mazi.rescu.ParamsDigest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.knowm.xchange.binance.BinanceResilience.*;
import static org.knowm.xchange.client.ResilienceRegistries.NON_IDEMPOTENTE_CALLS_RETRY_CONFIG_NAME;

/**
 * Created by lin on 2020-12-22.
 */
public class BinanceFuturesTradeServiceRaw extends BinanceFuturesBaseService {

  protected BinanceFuturesTradeServiceRaw(
      BinanceFuturesExchange exchange,
      BinanceFutures binance,
      BinanceFuturesCommon binanceCommon,
      ResilienceRegistries resilienceRegistries) {
    super(exchange, binance, binanceCommon, resilienceRegistries);
  }

  public BinancePositionSide getPositionSide(String apiKeyAnother, ParamsDigest signatureAnother) throws IOException, BinanceException {
    return decorateApiCall(
        () -> binance.getPositionSide(
            getRecvWindow(),
            getTimestampFactory(),
            Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
            Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
        .withRetry(retry("testNewOrder"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
  }

  public BinanceFutureNewOrder newOrder(
      CurrencyPair pair,
      OrderSide side,
      PositionSide positionSide,
      FutureOrderType type,
      Boolean reduceOnly,
      BigDecimal quantity,
      BigDecimal price,
      String newClientOrderId,
      BigDecimal stopPrice,
      Boolean closePosition,
      Boolean activationPrice,
      Boolean callbackRate,
      TimeInForce timeInForce,
      WorkingType workingType,
      Boolean priceProtect,
      NewOrderRespType newOrderRespType,
      String apiKeyAnother,
      ParamsDigest signatureAnother)
      throws IOException, BinanceException {
    return decorateApiCall(
        () ->
            binance.newOrder(
                BinanceAdapters.toSymbol(pair),
                side,
                positionSide,
                type,
                reduceOnly,
                quantity,
                price,
                newClientOrderId,
                stopPrice,
                closePosition,
                activationPrice,
                callbackRate,
                timeInForce,
                workingType,
                priceProtect,
                newOrderRespType,
                getRecvWindow(),
                getTimestampFactory(),
                Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
                Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
        .withRetry(retry("newOrder", NON_IDEMPOTENTE_CALLS_RETRY_CONFIG_NAME))
        .withRateLimiter(rateLimiter(ORDERS_PER_SECOND_RATE_LIMITER))
        .withRateLimiter(rateLimiter(ORDERS_PER_DAY_RATE_LIMITER))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
  }

  public Object testNewOrder(
      CurrencyPair pair,
      OrderSide side,
      PositionSide positionSide,
      FutureOrderType type,
      Boolean reduceOnly,
      BigDecimal quantity,
      BigDecimal price,
      String newClientOrderId,
      BigDecimal stopPrice,
      Boolean closePosition,
      Boolean activationPrice,
      Boolean callbackRate,
      TimeInForce timeInForce,
      WorkingType workingType,
      Boolean priceProtect,
      NewOrderRespType newOrderRespType,
      String apiKeyAnother,
      ParamsDigest signatureAnother)
      throws IOException, BinanceException {
    return decorateApiCall(
        () ->
            binance.testNewOrder(
                BinanceAdapters.toSymbol(pair),
                side,
                positionSide,
                type,
                reduceOnly,
                quantity,
                price,
                newClientOrderId,
                stopPrice,
                closePosition,
                activationPrice,
                callbackRate,
                timeInForce,
                workingType,
                priceProtect,
                newOrderRespType,
                getRecvWindow(),
                getTimestampFactory(),
                Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
                Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
        .withRetry(retry("testNewOrder"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
  }

  public List<BinanceFutureOrder> openOrders(
      String symbol,
      String pair,
      String apiKeyAnother,
      ParamsDigest signatureAnother
  ) throws BinanceException, IOException {
    return decorateApiCall(
        () ->
            binance.openOrders(
                symbol,
                pair,
                getRecvWindow(),
                getTimestampFactory(),
                Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
                Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
        .withRetry(retry("openOrders"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), openOrdersPermits(symbol))
        .call();
  }

  public BinanceFutureOrder cancelOrder(
      String symbol,
      Long orderId,
      String origClientOrderId,
      String apiKeyAnother,
      ParamsDigest signatureAnother
  ) throws IOException, BinanceException {
    return decorateApiCall(
        () ->
            binance.cancelOrder(
                symbol,
                orderId,
                origClientOrderId,
                getRecvWindow(),
                getTimestampFactory(),
                Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
                Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
        .withRetry(retry("cancelOrder"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
  }

  public boolean cancelAllOpenOrders(String symbol, String apiKeyAnother, ParamsDigest signatureAnother)
      throws IOException, BinanceException {
    Map<String, Object> res = decorateApiCall(
        () ->
            binance.cancelAllOpenOrders(
                symbol,
                getRecvWindow(),
                getTimestampFactory(),
                Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
                Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
        .withRetry(retry("cancelAllOpenOrders"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();

    return (Integer) res.get("code") == 200;
  }

  public List<BinanceFutureOrder> allOrders(
      String symbol,
      String pair,
      Long orderId,
      Long startTime,
      Long endTime,
      Integer limit,
      String apiKeyAnother,
      ParamsDigest signatureAnother)
      throws IOException, BinanceException {
    return decorateApiCall(
        () ->
            binance.allOrders(
                symbol,
                pair,
                orderId,
                startTime,
                endTime,
                limit,
                getRecvWindow(),
                getTimestampFactory(),
                Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
                Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
        .withRetry(retry("allOrders"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), allOrdersPermits(pair))
        .call();
  }

  public List<BinanceFuturePositionRisk> positionRisk(
      String symbol,
      String pair,
      String apiKeyAnother,
      ParamsDigest signatureAnother)
      throws IOException, BinanceException {
    if (futuresSettleType == FuturesSettleType.USDT) {
      return decorateApiCall(
          () ->
              ((BinanceFuturesUSDT) binance).positionRisk(
                  symbol,
                  getRecvWindow(),
                  getTimestampFactory(),
                  Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
                  Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
          .withRetry(retry("positionRisk"))
          .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
          .call();
    } else if (futuresSettleType == FuturesSettleType.COIN) {
      return decorateApiCall(
          () ->
              ((BinanceFuturesCoin) binance).positionRisk(
                  symbol,
                  pair,
                  getRecvWindow(),
                  getTimestampFactory(),
                  Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
                  Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
          .withRetry(retry("positionRisk"))
          .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
          .call();
    }
    return null;
  }

  protected int openOrdersPermits(String symbol) {
    return symbol != null ? 1 : 5;
  }

  protected int allOrdersPermits(String pair) {
    if (specification.getFuturesSettleType() == FuturesSettleType.USDT) {
      return 5;
    } else if (specification.getFuturesSettleType() == FuturesSettleType.COIN) {
      return pair == null ? 20 : 40;
    }
    return 5;
  }
}
