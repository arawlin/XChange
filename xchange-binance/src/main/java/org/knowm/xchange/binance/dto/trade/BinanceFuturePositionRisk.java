package org.knowm.xchange.binance.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by lin on 2021-01-06.
 */
public final class BinanceFuturePositionRisk {

  public final String symbol;
  public final BigDecimal positionAmt;
  public final BigDecimal entryPrice;
  public final BigDecimal markPrice;
  public final BigDecimal unRealizedProfit;
  public final BigDecimal liquidationPrice;
  public final Integer leverage;
  public final MarginType marginType;
  public final BigDecimal isolatedMargin;
  public final Boolean isAutoAddMargin;
  public final PositionSide positionSide;

  public final BigDecimal maxQty;
  public final BigDecimal maxNotionalValue;

  public BinanceFuturePositionRisk(
      @JsonProperty("symbol") String symbol,
      @JsonProperty("positionAmt") BigDecimal positionAmt,
      @JsonProperty("entryPrice") BigDecimal entryPrice,
      @JsonProperty("markPrice") BigDecimal markPrice,
      @JsonProperty("unRealizedProfit") BigDecimal unRealizedProfit,
      @JsonProperty("liquidationPrice") BigDecimal liquidationPrice,
      @JsonProperty("leverage") Integer leverage,
      @JsonProperty("marginType") MarginType marginType,
      @JsonProperty("isolatedMargin") BigDecimal isolatedMargin,
      @JsonProperty("isAutoAddMargin") Boolean isAutoAddMargin,
      @JsonProperty("positionSide") PositionSide positionSide,
      @JsonProperty("maxQty") BigDecimal maxQty,
      @JsonProperty("maxNotionalValue") BigDecimal maxNotionalValue
  ) {
    this.symbol = symbol;
    this.positionAmt = positionAmt;
    this.entryPrice = entryPrice;
    this.markPrice = markPrice;
    this.unRealizedProfit = unRealizedProfit;
    this.liquidationPrice = liquidationPrice;
    this.leverage = leverage;
    this.marginType = marginType;
    this.isolatedMargin = isolatedMargin;
    this.isAutoAddMargin = isAutoAddMargin;
    this.positionSide = positionSide;
    this.maxQty = maxQty;
    this.maxNotionalValue = maxNotionalValue;
  }
}
