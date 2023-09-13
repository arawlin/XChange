package org.knowm.xchange.binance.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public final class BinanceFutureNewOrder {

  public final String symbol;
  public final long orderId;
  public final String clientOrderId;
  public final long updateTime;
  public final BigDecimal price;
  public final BigDecimal origQty;
  public final BigDecimal executedQty;
  public final OrderStatus status;
  public final TimeInForce timeInForce;
  public final FutureOrderType type;
  public final FutureOrderType origType;
  public final OrderSide side;

  public final BigDecimal cumQty;
  public final BigDecimal cumQuote;
  public final BigDecimal avgPrice;
  public final BigDecimal stopPrice;
  public final BigDecimal activatePrice;
  public final BigDecimal priceRate;
  public final Boolean reduceOnly;
  public final Boolean closePosition;
  public final Boolean priceProtect;
  public final PositionSide positionSide;
  public final WorkingType workingType;

  public BinanceFutureNewOrder(
      @JsonProperty("symbol") String symbol,
      @JsonProperty("orderId") long orderId,
      @JsonProperty("clientOrderId") String clientOrderId,
      @JsonProperty("updateTime") long updateTime,
      @JsonProperty("price") BigDecimal price,
      @JsonProperty("origQty") BigDecimal origQty,
      @JsonProperty("executedQty") BigDecimal executedQty,
      @JsonProperty("status") OrderStatus status,
      @JsonProperty("timeInForce") TimeInForce timeInForce,
      @JsonProperty("type") FutureOrderType type,
      @JsonProperty("origType") FutureOrderType origType,
      @JsonProperty("side") OrderSide side,
      @JsonProperty("cumQty") BigDecimal cumQty,
      @JsonProperty("cumQuote") BigDecimal cumQuote,
      @JsonProperty("avgPrice") BigDecimal avgPrice,
      @JsonProperty("stopPrice") BigDecimal stopPrice,
      @JsonProperty("activatePrice") BigDecimal activatePrice,
      @JsonProperty("priceRate") BigDecimal priceRate,
      @JsonProperty("reduceOnly") Boolean reduceOnly,
      @JsonProperty("closePosition") Boolean closePosition,
      @JsonProperty("priceProtect") Boolean priceProtect,
      @JsonProperty("positionSide") PositionSide positionSide,
      @JsonProperty("workingType") WorkingType workingType
  ) {
    this.symbol = symbol;
    this.orderId = orderId;
    this.clientOrderId = clientOrderId;
    this.updateTime = updateTime;
    this.price = price;
    this.origQty = origQty;
    this.executedQty = executedQty;
    this.status = status;
    this.timeInForce = timeInForce;
    this.type = type;
    this.origType = origType;
    this.side = side;
    this.cumQty = cumQty;
    this.cumQuote = cumQuote;
    this.avgPrice = avgPrice;
    this.stopPrice = stopPrice;
    this.activatePrice = activatePrice;
    this.priceRate = priceRate;
    this.reduceOnly = reduceOnly;
    this.closePosition = closePosition;
    this.priceProtect = priceProtect;
    this.positionSide = positionSide;
    this.workingType = workingType;
  }
}
