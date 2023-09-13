package org.knowm.xchange.binance.service;

import org.knowm.xchange.binance.*;
import org.knowm.xchange.binance.dto.BinanceException;
import org.knowm.xchange.binance.dto.BinanceResponse;
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
import static org.knowm.xchange.client.ResilienceRegistries.NON_IDEMPOTENT_CALLS_RETRY_CONFIG_NAME;

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
        .withRetry(retry("getPositionSide"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
  }

  /**
   * 更改持仓模式(TRADE)
   *
   * @param dualSidePosition "true": 双向持仓模式；"false": 单向持仓模式
   * @param apiKeyAnother
   * @param signatureAnother
   * @return
   * @throws IOException
   * @throws BinanceException
   */
  public boolean setPositionSide(Boolean dualSidePosition, String apiKeyAnother, ParamsDigest signatureAnother) throws IOException, BinanceException {
    BinanceResponse res = decorateApiCall(
        () -> binance.setPositionSide(
            dualSidePosition,
            getRecvWindow(),
            getTimestampFactory(),
            Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
            Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
        .withRetry(retry("setPositionSide"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
    return res.code == 200;
  }

  public BinanceLeverageStatus setLeverage(String symbol, Integer leverage, String apiKeyAnother, ParamsDigest signatureAnother) throws IOException, BinanceException {
    return decorateApiCall(
        () -> binance.setLeverage(
            symbol,
            leverage,
            getRecvWindow(),
            getTimestampFactory(),
            Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
            Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
        .withRetry(retry("setLeverage"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
  }

  /**
   * 变换逐全仓模式 (TRADE)
   *
   * @param symbol
   * @param marginType       保证金模式 ISOLATED(逐仓), CROSSED(全仓)
   * @param apiKeyAnother
   * @param signatureAnother
   * @return
   * @throws IOException
   * @throws BinanceException
   */
  public boolean setMarginType(String symbol, MarginType marginType, String apiKeyAnother, ParamsDigest signatureAnother) throws IOException, BinanceException {
    BinanceResponse res = decorateApiCall(
        () -> binance.setMarginType(
            symbol,
            marginType,
            getRecvWindow(),
            getTimestampFactory(),
            Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
            Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
        .withRetry(retry("setMarginType"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
    return res.code == 200;
  }

  /**
   * @param symbol
   * @param positionSide     持仓方向，单向持仓模式下非必填，默认且仅可填BOTH;在双向持仓模式下必填,且仅可选择 LONG 或 SHORT
   * @param amount           保证金资金
   * @param type             调整方向 1: 增加逐仓保证金，2: 减少逐仓保证金
   * @param apiKeyAnother
   * @param signatureAnother
   * @return
   * @throws IOException
   * @throws BinanceException
   */
  public BinanceResponsePositionMargin setPositionMargin(String symbol, PositionSide positionSide, BigDecimal amount, Integer type, String apiKeyAnother, ParamsDigest signatureAnother) throws IOException, BinanceException {
    return decorateApiCall(
        () -> binance.setPositionMargin(
            symbol,
            positionSide,
            amount,
            type,
            getRecvWindow(),
            getTimestampFactory(),
            Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
            Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
        .withRetry(retry("setPositionMargin"))
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
        .withRetry(retry("newOrder", NON_IDEMPOTENT_CALLS_RETRY_CONFIG_NAME))
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

  public BinanceFutureOrder orderStatus(CurrencyPair pair, long orderId, String origClientOrderId, String apiKeyAnother, ParamsDigest signatureAnother)
      throws IOException, BinanceException {
    return decorateApiCall(
        () ->
            binance.orderStatus(
                BinanceAdapters.toSymbol(pair),
                orderId,
                origClientOrderId,
                getRecvWindow(),
                getTimestampFactory(),
                Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
                Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
        .withRetry(retry("orderStatus"))
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

  public List<BinanceFutureTrade> userTrades(
      String symbol,
      String pair,
      Long startTime,
      Long endTime,
      Long fromId,
      Integer limit,
      String apiKeyAnother,
      ParamsDigest signatureAnother
  ) throws IOException {
    return decorateApiCall(
        () ->
            binance.userTrades(
                symbol,
                pair,
                startTime,
                endTime,
                fromId,
                limit,
                getRecvWindow(),
                getTimestampFactory(),
                Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
                Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
        .withRetry(retry("userTrades"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), allOrdersPermits(pair))
        .call();
  }

  public List<BinanceIncomeRecord> income(
      String symbol,
      FutureIncomeType incomeType,
      Long startTime,
      Long endTime,
      Integer limit,
      String apiKeyAnother,
      ParamsDigest signatureAnother
  ) throws IOException {
    return decorateApiCall(
        () ->
            binance.income(
                symbol,
                incomeType,
                startTime,
                endTime,
                limit,
                getRecvWindow(),
                getTimestampFactory(),
                Optional.ofNullable(apiKeyAnother).orElse(this.apiKey),
                Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)))
        .withRetry(retry("income"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), 30)
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
