package org.knowm.xchange.binance.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by lin on 2021-01-06.
 */
public final class BinanceFuturePosition {

  public final String symbol;
  public final BigDecimal initialMargin;
  public final BigDecimal maintMargin;
  public final BigDecimal unrealizedProfit;
  public final BigDecimal positionInitialMargin;
  public final BigDecimal openOrderInitialMargin;
  public final BigDecimal entryPrice;
  public final BigDecimal positionAmt;
  public final Integer leverage;
  public final Boolean isolated;
  public final PositionSide positionSide;

  public final BigDecimal maxNotional;
  public final BigDecimal maxQty;

  public BinanceFuturePosition(
      @JsonProperty("symbol") String symbol,
      @JsonProperty("initialMargin") BigDecimal initialMargin,
      @JsonProperty("maintMargin") BigDecimal maintMargin,
      @JsonProperty("unrealizedProfit") BigDecimal unrealizedProfit,
      @JsonProperty("positionInitialMargin") BigDecimal positionInitialMargin,
      @JsonProperty("openOrderInitialMargin") BigDecimal openOrderInitialMargin,
      @JsonProperty("entryPrice") BigDecimal entryPrice,
      @JsonProperty("positionAmt") BigDecimal positionAmt,
      @JsonProperty("leverage") Integer leverage,
      @JsonProperty("isolated") Boolean isolated,
      @JsonProperty("positionSide") PositionSide positionSide,
      @JsonProperty("maxNotional") BigDecimal maxNotional,
      @JsonProperty("maxQty") BigDecimal maxQty
  ) {
    this.symbol = symbol;
    this.initialMargin = initialMargin;
    this.maintMargin = maintMargin;
    this.unrealizedProfit = unrealizedProfit;
    this.positionInitialMargin = positionInitialMargin;
    this.openOrderInitialMargin = openOrderInitialMargin;
    this.entryPrice = entryPrice;
    this.positionAmt = positionAmt;
    this.leverage = leverage;
    this.isolated = isolated;
    this.positionSide = positionSide;
    this.maxNotional = maxNotional;
    this.maxQty = maxQty;
  }
}
