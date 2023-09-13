package org.knowm.xchange.binance.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public final class BinanceForceOrder {

  public final String symbol;
  public final BigDecimal price;
  public final BigDecimal origQty;
  public final BigDecimal executedQty;
  public final BigDecimal avragePrice;
  public final OrderStatus status;
  public final TimeInForce timeInForce;
  public final OrderType type;
  public final OrderSide side;
  public final long time;
  public BigDecimal executedQtyTotal;
  public String pair;

  public BinanceForceOrder(
      @JsonProperty("symbol") String symbol,
      @JsonProperty("price") BigDecimal price,
      @JsonProperty("origQty") BigDecimal origQty,
      @JsonProperty("executedQty") BigDecimal executedQty,
      @JsonProperty("avragePrice") BigDecimal avragePrice,
      @JsonProperty("status") OrderStatus status,
      @JsonProperty("timeInForce") TimeInForce timeInForce,
      @JsonProperty("type") OrderType type,
      @JsonProperty("side") OrderSide side,
      @JsonProperty("time") long time) {
    this.symbol = symbol;
    this.price = price;
    this.origQty = origQty;
    this.executedQty = executedQty;
    this.avragePrice = avragePrice;
    this.status = status;
    this.timeInForce = timeInForce;
    this.type = type;
    this.side = side;
    this.time = time;
  }

  public Date getTime() {
    return new Date(time);
  }

  public BigDecimal getExecutedQtyTotal() {
    return executedQtyTotal;
  }

  public void setExecutedQtyTotal(BigDecimal executedQtyTotal) {
    this.executedQtyTotal = executedQtyTotal;
  }

  public String getPair() {
    return pair;
  }

  public void setPair(String pair) {
    this.pair = pair;
  }
}
