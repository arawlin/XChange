package org.knowm.xchange.binance.service;

import static org.knowm.xchange.binance.BinanceResilience.*;
import static org.knowm.xchange.client.ResilienceRegistries.NON_IDEMPOTENTE_CALLS_RETRY_CONFIG_NAME;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.knowm.xchange.binance.BinanceAdapters;
import org.knowm.xchange.binance.BinanceAuthenticated;
import org.knowm.xchange.binance.BinanceExchange;
import org.knowm.xchange.binance.dto.BinanceException;
import org.knowm.xchange.binance.dto.trade.BinanceCancelledOrder;
import org.knowm.xchange.binance.dto.trade.BinanceListenKey;
import org.knowm.xchange.binance.dto.trade.BinanceNewOrder;
import org.knowm.xchange.binance.dto.trade.BinanceOrder;
import org.knowm.xchange.binance.dto.trade.BinanceTrade;
import org.knowm.xchange.binance.dto.trade.OrderSide;
import org.knowm.xchange.binance.dto.trade.OrderType;
import org.knowm.xchange.binance.dto.trade.TimeInForce;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.currency.CurrencyPair;
import si.mazi.rescu.ParamsDigest;

public class BinanceTradeServiceRaw extends BinanceBaseService {

  protected BinanceTradeServiceRaw(
      BinanceExchange exchange,
      BinanceAuthenticated binance,
      ResilienceRegistries resilienceRegistries) {
    super(exchange, binance, resilienceRegistries);
  }

  public List<BinanceOrder> openOrders() throws BinanceException, IOException {
    return openOrders(null);
  }

  public List<BinanceOrder> openOrders(CurrencyPair pair) throws BinanceException, IOException {
    return openOrders(pair, this.apiKey, this.signatureCreator);
  }

  public List<BinanceOrder> openOrders(CurrencyPair pair, String apiKeyAnother, ParamsDigest signatureAnother) throws BinanceException, IOException {
    return decorateApiCall(
            () ->
                binance.openOrders(
                    Optional.ofNullable(pair).map(BinanceAdapters::toSymbol).orElse(null),
                    getRecvWindow(),
                    getTimestampFactory(),
                    apiKeyAnother,
                    signatureAnother))
        .withRetry(retry("openOrders"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), openOrdersPermits(pair))
        .call();
  }

  public BinanceNewOrder newOrder(
      CurrencyPair pair,
      OrderSide side,
      OrderType type,
      TimeInForce timeInForce,
      BigDecimal quantity,
      BigDecimal price,
      String newClientOrderId,
      BigDecimal stopPrice,
      BigDecimal icebergQty)
      throws IOException, BinanceException {
    return newOrder(pair, side, type, timeInForce, quantity, price, newClientOrderId, stopPrice, icebergQty, this.apiKey, this.signatureCreator);
  }

  public BinanceNewOrder newOrder(
      CurrencyPair pair,
      OrderSide side,
      OrderType type,
      TimeInForce timeInForce,
      BigDecimal quantity,
      BigDecimal price,
      String newClientOrderId,
      BigDecimal stopPrice,
      BigDecimal icebergQty,
      String apiKeyAnother,
      ParamsDigest signatureAnother)
      throws IOException, BinanceException {
    return decorateApiCall(
            () ->
                binance.newOrder(
                    BinanceAdapters.toSymbol(pair),
                    side,
                    type,
                    timeInForce,
                    quantity,
                    price,
                    newClientOrderId,
                    stopPrice,
                    icebergQty,
                    getRecvWindow(),
                    getTimestampFactory(),
                    apiKeyAnother,
                    signatureAnother))
        .withRetry(retry("newOrder", NON_IDEMPOTENTE_CALLS_RETRY_CONFIG_NAME))
        .withRateLimiter(rateLimiter(ORDERS_PER_SECOND_RATE_LIMITER))
        .withRateLimiter(rateLimiter(ORDERS_PER_DAY_RATE_LIMITER))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
  }

  public void testNewOrder(
      CurrencyPair pair,
      OrderSide side,
      OrderType type,
      TimeInForce timeInForce,
      BigDecimal quantity,
      BigDecimal price,
      String newClientOrderId,
      BigDecimal stopPrice,
      BigDecimal icebergQty)
      throws IOException, BinanceException {
    testNewOrder(pair, side, type, timeInForce, quantity, price, newClientOrderId, stopPrice, icebergQty, this.apiKey, this.signatureCreator);
  }

  public void testNewOrder(
      CurrencyPair pair,
      OrderSide side,
      OrderType type,
      TimeInForce timeInForce,
      BigDecimal quantity,
      BigDecimal price,
      String newClientOrderId,
      BigDecimal stopPrice,
      BigDecimal icebergQty,
      String apiKeyAnother,
      ParamsDigest signatureAnother)
      throws IOException, BinanceException {
    decorateApiCall(
            () ->
                binance.testNewOrder(
                    BinanceAdapters.toSymbol(pair),
                    side,
                    type,
                    timeInForce,
                    quantity,
                    price,
                    newClientOrderId,
                    stopPrice,
                    icebergQty,
                    getRecvWindow(),
                    getTimestampFactory(),
                    apiKeyAnother,
                    signatureAnother))
        .withRetry(retry("testNewOrder"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
  }

  public BinanceOrder orderStatus(CurrencyPair pair, long orderId, String origClientOrderId)
      throws IOException, BinanceException {
    return orderStatus(pair, orderId, origClientOrderId, this.apiKey, this.signatureCreator);
  }

  public BinanceOrder orderStatus(CurrencyPair pair, long orderId, String origClientOrderId, String apiKeyAnother, ParamsDigest signatureAnother)
      throws IOException, BinanceException {
    return decorateApiCall(
            () ->
                binance.orderStatus(
                    BinanceAdapters.toSymbol(pair),
                    orderId,
                    origClientOrderId,
                    getRecvWindow(),
                    getTimestampFactory(),
                    apiKeyAnother,
                    signatureAnother))
        .withRetry(retry("orderStatus"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
  }

  public BinanceCancelledOrder cancelOrder(
      CurrencyPair pair, long orderId, String origClientOrderId, String newClientOrderId)
      throws IOException, BinanceException {
    return cancelOrder(pair, orderId, origClientOrderId, newClientOrderId, this.apiKey, this.signatureCreator);
  }

  public BinanceCancelledOrder cancelOrder(
      CurrencyPair pair, long orderId, String origClientOrderId, String newClientOrderId, String apiKeyAnother, ParamsDigest signatureAnother)
      throws IOException, BinanceException {
    return decorateApiCall(
            () ->
                binance.cancelOrder(
                    BinanceAdapters.toSymbol(pair),
                    orderId,
                    origClientOrderId,
                    newClientOrderId,
                    getRecvWindow(),
                    getTimestampFactory(),
                    apiKeyAnother,
                    signatureAnother))
        .withRetry(retry("cancelOrder"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
  }

  public List<BinanceCancelledOrder> cancelAllOpenOrders(CurrencyPair pair)
      throws IOException, BinanceException {
    return cancelAllOpenOrders(pair, this.apiKey, this.signatureCreator);
  }

  public List<BinanceCancelledOrder> cancelAllOpenOrders(CurrencyPair pair, String apiKeyAnother, ParamsDigest signatureAnother)
      throws IOException, BinanceException {
    return decorateApiCall(
            () ->
                binance.cancelAllOpenOrders(
                    BinanceAdapters.toSymbol(pair),
                    getRecvWindow(),
                    getTimestampFactory(),
                    apiKeyAnother,
                    signatureAnother))
        .withRetry(retry("cancelAllOpenOrders"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
  }

  public List<BinanceOrder> allOrders(CurrencyPair pair, Long orderId, Integer limit)
      throws BinanceException, IOException {
    return allOrders(pair, orderId, limit, this.apiKey, this.signatureCreator);
  }

  public List<BinanceOrder> allOrders(CurrencyPair pair, Long orderId, Integer limit, String apiKeyAnother, ParamsDigest signatureAnother)
      throws BinanceException, IOException {
    return decorateApiCall(
            () ->
                binance.allOrders(
                    BinanceAdapters.toSymbol(pair),
                    orderId,
                    limit,
                    getRecvWindow(),
                    getTimestampFactory(),
                    apiKeyAnother,
                    signatureAnother))
        .withRetry(retry("allOrders"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
  }

  public List<BinanceTrade> myTrades(
      CurrencyPair pair, Integer limit, Long startTime, Long endTime, Long fromId, String apiKeyAnother, ParamsDigest signatureAnother)
      throws BinanceException, IOException {
    return decorateApiCall(
            () ->
                binance.myTrades(
                    BinanceAdapters.toSymbol(pair),
                    limit,
                    startTime,
                    endTime,
                    fromId,
                    getRecvWindow(),
                    getTimestampFactory(),
                    apiKeyAnother,
                    signatureAnother))
        .withRetry(retry("myTrades"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), myTradesPermits(limit))
        .call();
  }

  public List<BinanceTrade> myTrades(
      CurrencyPair pair, Integer limit, Long startTime, Long endTime, Long fromId)
      throws BinanceException, IOException {
    return myTrades(pair, limit, startTime, endTime, fromId, this.apiKey, this.signatureCreator);
  }

  public BinanceListenKey startUserDataStream() throws IOException {
    return decorateApiCall(() -> binance.startUserDataStream(apiKey))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
  }

  public void keepAliveDataStream(String listenKey) throws IOException {
    decorateApiCall(() -> binance.keepAliveUserDataStream(apiKey, listenKey))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
  }

  public void closeDataStream(String listenKey) throws IOException {
    decorateApiCall(() -> binance.closeUserDataStream(apiKey, listenKey))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
  }

  protected int openOrdersPermits(CurrencyPair pair) {
    return pair != null ? 1 : 40;
  }

  protected int myTradesPermits(Integer limit) {
    if (limit != null && limit > 500) {
      return 10;
    }
    return 5;
  }
}
