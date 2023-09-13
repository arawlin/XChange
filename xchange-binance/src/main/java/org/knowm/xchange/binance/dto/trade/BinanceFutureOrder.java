package org.knowm.xchange.binance.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public final class BinanceFutureOrder {

  public final String symbol;
  public final String pair;
  public final long orderId;
  public final String clientOrderId;
  public final BigDecimal price;
  public final BigDecimal stopPrice;
  public final BigDecimal avgPrice;
  public final BigDecimal activatePrice;
  public final BigDecimal priceRate;
  public final BigDecimal origQty;
  public final BigDecimal executedQty;
  public final BigDecimal cumQuote;
  public final BigDecimal cumBase;
  public final boolean reduceOnly;
  public final boolean closePosition;
  public final boolean priceProtect;
  public final OrderStatus status;
  public final WorkingType workingType;
  public final PositionSide positionSide;
  public final TimeInForce timeInForce;
  public final FutureOrderType type;
  public final FutureOrderType origType;
  public final OrderSide side;
  public final BigDecimal icebergQty;
  public final long time;
  public final long updateTime;

  public BinanceFutureOrder(
      @JsonProperty("symbol") String symbol,
      @JsonProperty("pair") String pair,
      @JsonProperty("orderId") long orderId,
      @JsonProperty("clientOrderId") String clientOrderId,
      @JsonProperty("price") BigDecimal price,
      @JsonProperty("stopPrice") BigDecimal stopPrice,
      @JsonProperty("avgPrice") BigDecimal avgPrice,
      @JsonProperty("activatePrice") BigDecimal activatePrice,
      @JsonProperty("priceRate") BigDecimal priceRate,
      @JsonProperty("origQty") BigDecimal origQty,
      @JsonProperty("executedQty") BigDecimal executedQty,
      @JsonProperty("cumQuote") BigDecimal cumQuote,
      @JsonProperty("cumBase") BigDecimal cumBase,
      @JsonProperty("reduceOnly") boolean reduceOnly,
      @JsonProperty("closePosition") boolean closePosition,
      @JsonProperty("priceProtect") boolean priceProtect,
      @JsonProperty("status") OrderStatus status,
      @JsonProperty("workingType") WorkingType workingType,
      @JsonProperty("positionSide") PositionSide positionSide,
      @JsonProperty("timeInForce") TimeInForce timeInForce,
      @JsonProperty("type") FutureOrderType type,
      @JsonProperty("origType") FutureOrderType origType,
      @JsonProperty("side") OrderSide side,
      @JsonProperty("icebergQty") BigDecimal icebergQty,
      @JsonProperty("time") long time,
      @JsonProperty("updateTime") long updateTime
  ) {
    this.symbol = symbol;
    this.pair = pair;
    this.orderId = orderId;
    this.clientOrderId = clientOrderId;
    this.price = price;
    this.stopPrice = stopPrice;
    this.avgPrice = avgPrice;
    this.activatePrice = activatePrice;
    this.priceRate = priceRate;
    this.origQty = origQty;
    this.executedQty = executedQty;
    this.cumQuote = cumQuote;
    this.cumBase = cumBase;
    this.reduceOnly = reduceOnly;
    this.closePosition = closePosition;
    this.priceProtect = priceProtect;
    this.status = status;
    this.workingType = workingType;
    this.positionSide = positionSide;
    this.timeInForce = timeInForce;
    this.type = type;
    this.origType = origType;
    this.side = side;
    this.icebergQty = icebergQty;
    this.time = time;
    this.updateTime = updateTime;
  }

  public Date getTime() {
    return new Date(time);
  }
}
