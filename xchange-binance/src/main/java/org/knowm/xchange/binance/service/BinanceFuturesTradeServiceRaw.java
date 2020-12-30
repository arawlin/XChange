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
import java.util.List;
import java.util.Optional;

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
                Optional.ofNullable(recvWindow).orElse(getRecvWindow()),
                Optional.ofNullable(timestamp).orElse(getTimestampFactory()),
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
                Optional.ofNullable(recvWindow).orElse(getRecvWindow()),
                Optional.ofNullable(timestamp).orElse(getTimestampFactory()),
                Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
                Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
        .withRetry(retry("testNewOrder"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
  }

  public List<BinanceFutureOrder> openOrders(
      String symbol,
      String pair,
      Long recvWindow,
      SynchronizedValueFactory<Long> timestamp,
      String apiKeyAnother,
      ParamsDigest signatureAnother
  ) throws BinanceException, IOException {
    return decorateApiCall(
        () ->
            binance.openOrders(
                symbol,
                pair,
                Optional.ofNullable(recvWindow).orElse(getRecvWindow()),
                Optional.ofNullable(timestamp).orElse(getTimestampFactory()),
                Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
                Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
        .withRetry(retry("openOrders"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), openOrdersPermits(symbol))
        .call();
  }

  protected int openOrdersPermits(String symbol) {
    return symbol != null ? 1 : 5;
  }

}
