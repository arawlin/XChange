package org.knowm.xchange.binance.service;

import org.knowm.xchange.binance.*;
import org.knowm.xchange.binance.dto.BinanceException;
import org.knowm.xchange.binance.dto.trade.*;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.*;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.trade.params.CancelOrderParams;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.orders.OpenOrdersParams;
import org.knowm.xchange.service.trade.params.orders.OrderQueryParams;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by lin on 2020-12-22.
 */
public class BinanceFuturesTradeService extends BinanceFuturesTradeServiceRaw implements TradeService {

  public BinanceFuturesTradeService(
      BinanceFuturesExchange exchange,
      BinanceFutures binance,
      BinanceFuturesCommon binanceCommon,
      ResilienceRegistries resilienceRegistries) {
    super(exchange, binance, binanceCommon, resilienceRegistries);
  }

  @Override
  public OpenOrders getOpenOrders() throws IOException {
    throw new NotYetImplementedForExchangeException("Use raw service!");
  }

  @Override
  public OpenOrders getOpenOrders(OpenOrdersParams params) throws IOException {
    throw new NotYetImplementedForExchangeException("Use raw service!");
  }

  @Override
  public String placeMarketOrder(MarketOrder marketOrder) throws IOException {
    return placeOrder(FutureOrderType.MARKET, marketOrder, null, null, null, null);
  }

  @Override
  public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
    return placeOrder(FutureOrderType.LIMIT, limitOrder, limitOrder.getLimitPrice(), null, null, null);
  }

  @Override
  public String placeStopOrder(StopOrder stopOrder) throws IOException {
    return placeOrder(BinanceAdapters.adaptFutureOrderType(stopOrder), stopOrder, stopOrder.getLimitPrice(), stopOrder.getStopPrice(), null, null);
  }

  @Override
  public String changeOrder(LimitOrder limitOrder) throws IOException {
    return null;
  }

  @Override
  public boolean cancelOrder(String orderId) throws IOException {
    return false;
  }

  @Override
  public boolean cancelOrder(CancelOrderParams orderParams) throws IOException {
    return false;
  }

  @Override
  public UserTrades getTradeHistory(TradeHistoryParams params) throws IOException {
    return null;
  }

  @Override
  public TradeHistoryParams createTradeHistoryParams() {
    return null;
  }

  @Override
  public OpenOrdersParams createOpenOrdersParams() {
    return null;
  }

  @Override
  public Collection<Order> getOrder(String... orderIds) throws IOException {
    return null;
  }

  @Override
  public Collection<Order> getOrder(OrderQueryParams... orderQueryParams) throws IOException {
    return null;
  }

  public String placeOrder(FutureOrderType type, Order order, BigDecimal limitPrice, BigDecimal stopPrice, String apiKey, String secretKey) throws IOException {
    try {
      BinancePositionSide binancePositionSide = getPositionSide(apiKey, BinanceHmacDigest.createInstance(secretKey));
      OrderSide orderSide;
      PositionSide positionSide;
      Boolean reduceOnly = null;
      switch (order.getType()) {
        case ASK:
          orderSide = OrderSide.SELL;
          positionSide = binancePositionSide.getDualSidePosition() ? PositionSide.SHORT : PositionSide.BOTH;
          break;
        case EXIT_ASK:
          orderSide = OrderSide.BUY;
          positionSide = binancePositionSide.getDualSidePosition() ? PositionSide.SHORT : PositionSide.BOTH;
          reduceOnly = binancePositionSide.getDualSidePosition() ? null : true;
          break;

        case BID:
          orderSide = OrderSide.BUY;
          positionSide = binancePositionSide.getDualSidePosition() ? PositionSide.LONG : PositionSide.BOTH;
          break;
        case EXIT_BID:
          orderSide = OrderSide.SELL;
          positionSide = binancePositionSide.getDualSidePosition() ? PositionSide.LONG : PositionSide.BOTH;
          reduceOnly = binancePositionSide.getDualSidePosition() ? null : true;
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + order.getType());
      }

      BinanceFutureNewOrder o = newOrder(
          order.getCurrencyPair(),
          orderSide,
          positionSide,
          type,
          reduceOnly,
          order.getOriginalAmount(),
          limitPrice,
          order.getUserReference(),
          stopPrice,
          order.hasFlag(BinanceOrderFlags.CLOSE_POSITION),
          null,
          null,
          BinanceAdapters.timeInForceFromOrder(order).orElse(null),
          (WorkingType) order.getOrderFlagMap(BinanceOrderFlags.WORKING_TYPE),
          order.hasFlag(BinanceOrderFlags.PRICE_PROTECT),
          null,
          null,
          null,
          apiKey,
          BinanceHmacDigest.createInstance(secretKey)
      );
      return o.orderId + "";
    } catch (BinanceException e) {
      throw BinanceErrorAdapter.adapt(e);
    }
  }

  public Object placeTestOrder(FutureOrderType type, Order order, BigDecimal limitPrice, BigDecimal stopPrice, String apiKey, String secretKey) throws IOException {
    try {
      BinancePositionSide binancePositionSide = getPositionSide(apiKey, BinanceHmacDigest.createInstance(secretKey));
      OrderSide orderSide;
      PositionSide positionSide;
      Boolean reduceOnly = null;
      switch (order.getType()) {
        case ASK:
          orderSide = OrderSide.SELL;
          positionSide = binancePositionSide.getDualSidePosition() ? PositionSide.SHORT : PositionSide.BOTH;
          break;
        case EXIT_ASK:
          orderSide = OrderSide.BUY;
          positionSide = binancePositionSide.getDualSidePosition() ? PositionSide.SHORT : PositionSide.BOTH;
          reduceOnly = binancePositionSide.getDualSidePosition() ? null : true;
          break;

        case BID:
          orderSide = OrderSide.BUY;
          positionSide = binancePositionSide.getDualSidePosition() ? PositionSide.LONG : PositionSide.BOTH;
          break;
        case EXIT_BID:
          orderSide = OrderSide.SELL;
          positionSide = binancePositionSide.getDualSidePosition() ? PositionSide.LONG : PositionSide.BOTH;
          reduceOnly = binancePositionSide.getDualSidePosition() ? null : true;
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + order.getType());
      }

      return testNewOrder(
          order.getCurrencyPair(),
          orderSide,
          positionSide,
          type,
          reduceOnly,
          order.getOriginalAmount(),
          limitPrice,
          order.getUserReference(),
          stopPrice,
          order.hasFlag(BinanceOrderFlags.CLOSE_POSITION),
          null,
          null,
          BinanceAdapters.timeInForceFromOrder(order).orElse(null),
          (WorkingType) order.getOrderFlagMap(BinanceOrderFlags.WORKING_TYPE),
          order.hasFlag(BinanceOrderFlags.PRICE_PROTECT),
          null,
          null,
          null,
          apiKey,
          BinanceHmacDigest.createInstance(secretKey)
      );
    } catch (BinanceException e) {
      throw BinanceErrorAdapter.adapt(e);
    }
  }

}
