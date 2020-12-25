package org.knowm.xchange.binance.service;

import org.knowm.xchange.binance.*;
import org.knowm.xchange.binance.dto.BinanceException;
import org.knowm.xchange.binance.dto.trade.*;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.currency.CurrencyPair;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

import java.io.IOException;
import java.math.BigDecimal;

import static org.knowm.xchange.binance.BinanceResilience.*;
import static org.knowm.xchange.client.ResilienceRegistries.NON_IDEMPOTENTE_CALLS_RETRY_CONFIG_NAME;

/**
 * Created by lin on 2020-12-22.
 */
public class BinanceFuturesTradeServiceRaw extends BinanceFuturesBaseService {

  protected final BinanceFutures binance;
  protected final BinanceFuturesCommon binanceCommon;
  protected final BinanceExchangeSpecification specification;

  protected BinanceFuturesTradeServiceRaw(
      BinanceFuturesExchange exchange,
      BinanceFutures binance,
      BinanceFuturesCommon binanceCommon,
      ResilienceRegistries resilienceRegistries) {
    super(exchange, resilienceRegistries);

    this.binance = binance;
    this.binanceCommon = binanceCommon;
    this.specification = (BinanceExchangeSpecification) exchange.getExchangeSpecification();
  }

  public BinancePositionSide getPositionSide() throws IOException, BinanceException {
    return getPositionSide(this.apiKey, this.signatureCreator);
  }

  public BinancePositionSide getPositionSide(String apiKeyAnother, ParamsDigest signatureAnother) throws IOException, BinanceException {
    return decorateApiCall(
        () -> binance.getPositionSide(getRecvWindow(), getTimestampFactory(), apiKeyAnother, signatureAnother))
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
      NewOrderRespType newOrderRespType)
      throws IOException, BinanceException {
    return newOrder(pair,
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
        this.apiKey,
        this.signatureCreator);
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
      Long recvWindow,
      SynchronizedValueFactory<Long> timestamp,
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
                recvWindow,
                timestamp,
                apiKeyAnother,
                signatureAnother))
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
      NewOrderRespType newOrderRespType)
      throws IOException, BinanceException {
    return testNewOrder(pair,
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
        this.apiKey,
        this.signatureCreator);
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
      Long recvWindow,
      SynchronizedValueFactory<Long> timestamp,
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
                recvWindow,
                timestamp,
                apiKeyAnother,
                signatureAnother))
        .withRetry(retry("testNewOrder"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
  }

}
