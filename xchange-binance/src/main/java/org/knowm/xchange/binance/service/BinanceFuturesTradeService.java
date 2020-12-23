package org.knowm.xchange.binance.service;

import org.knowm.xchange.binance.BinanceFutures;
import org.knowm.xchange.binance.BinanceFuturesCommon;
import org.knowm.xchange.binance.BinanceFuturesExchange;
import org.knowm.xchange.binance.dto.trade.OrderType;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.*;
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
    return null;
  }

  @Override
  public OpenOrders getOpenOrders(OpenOrdersParams params) throws IOException {
    return null;
  }

  @Override
  public String placeMarketOrder(MarketOrder marketOrder) throws IOException {
    return null;
  }

  @Override
  public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
    return null;
  }

  @Override
  public String placeStopOrder(StopOrder stopOrder) throws IOException {
    return null;
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

  public void placeTestOrder(OrderType type, Order order, BigDecimal limitPrice, BigDecimal stopPrice, String apiKey, String secretKey) throws IOException {
//    try {
//      TimeInForce tif = BinanceAdapters.timeInForceFromOrder(order).orElse(null);
//      Long recvWindow =
//          (Long)
//              exchange.getExchangeSpecification().getExchangeSpecificParametersItem("recvWindow");
//
//      testNewOrder(
//          order.getCurrencyPair(),
//          BinanceAdapters.convert(order.getType()),
//
//          );
//    } catch (BinanceException e) {
//      throw BinanceErrorAdapter.adapt(e);
//    }
  }

}
