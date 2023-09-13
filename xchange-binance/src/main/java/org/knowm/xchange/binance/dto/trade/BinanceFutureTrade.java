package org.knowm.xchange.binance.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public final class BinanceFutureTrade {

  public final long id;
  public final long orderId;
  public final String symbol;
  public final String pair;
  public final String marginAsset;
  public final OrderSide side;
  public final BigDecimal price;
  public final BigDecimal qty;
  public final BigDecimal realizedPnl;
  public final BigDecimal baseQty;
  public final BigDecimal quoteQty;
  public final BigDecimal commission;
  public final String commissionAsset;
  public final PositionSide positionSide;
  public final long time;
  public final boolean buyer;
  public final boolean maker;

  public BinanceFutureTrade(
      @JsonProperty("id") long id,
      @JsonProperty("orderId") long orderId,
      @JsonProperty("symbol") String symbol,
      @JsonProperty("pair") String pair,
      @JsonProperty("marginAsset") String marginAsset,
      @JsonProperty("side") OrderSide side,
      @JsonProperty("price") BigDecimal price,
      @JsonProperty("qty") BigDecimal qty,
      @JsonProperty("realizedPnl") BigDecimal realizedPnl,
      @JsonProperty("baseQty") BigDecimal baseQty,
      @JsonProperty("quoteQty") BigDecimal quoteQty,
      @JsonProperty("commission") BigDecimal commission,
      @JsonProperty("commissionAsset") String commissionAsset,
      @JsonProperty("positionSide") PositionSide positionSide,
      @JsonProperty("time") long time,
      @JsonProperty("buyer") boolean buyer,
      @JsonProperty("maker") boolean maker
  ) {
    this.id = id;
    this.orderId = orderId;
    this.symbol = symbol;
    this.pair = pair;
    this.marginAsset = marginAsset;
    this.side = side;
    this.price = price;
    this.qty = qty;
    this.realizedPnl = realizedPnl;
    this.baseQty = baseQty;
    this.quoteQty = quoteQty;
    this.commission = commission;
    this.commissionAsset = commissionAsset;
    this.positionSide = positionSide;
    this.time = time;
    this.buyer = buyer;
    this.maker = maker;
  }

  public Date getTime() {
    return new Date(time);
  }
}
