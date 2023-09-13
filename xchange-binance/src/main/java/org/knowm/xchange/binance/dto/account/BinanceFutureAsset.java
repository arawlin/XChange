package org.knowm.xchange.binance.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by lin on 2021-01-06.
 */
public final class BinanceFutureAsset {

  public final String asset;
  public final BigDecimal walletBalance;
  public final BigDecimal unrealizedProfit;
  public final BigDecimal marginBalance;
  public final BigDecimal maintMargin;
  public final BigDecimal initialMargin;
  public final BigDecimal positionInitialMargin;
  public final BigDecimal openOrderInitialMargin;
  public final BigDecimal maxWithdrawAmount;
  public final BigDecimal crossWalletBalance;
  public final BigDecimal crossUnPnl;

  public BinanceFutureAsset(
      @JsonProperty("asset") String asset,
      @JsonProperty("walletBalance") BigDecimal walletBalance,
      @JsonProperty("unrealizedProfit") BigDecimal unrealizedProfit,
      @JsonProperty("marginBalance") BigDecimal marginBalance,
      @JsonProperty("maintMargin") BigDecimal maintMargin,
      @JsonProperty("initialMargin") BigDecimal initialMargin,
      @JsonProperty("positionInitialMargin") BigDecimal positionInitialMargin,
      @JsonProperty("openOrderInitialMargin") BigDecimal openOrderInitialMargin,
      @JsonProperty("maxWithdrawAmount") BigDecimal maxWithdrawAmount,
      @JsonProperty("crossWalletBalance") BigDecimal crossWalletBalance,
      @JsonProperty("crossUnPnl") BigDecimal crossUnPnl,
      @JsonProperty("availableBalance") BigDecimal availableBalance
  ) {
    this.asset = asset;
    this.walletBalance = walletBalance;
    this.unrealizedProfit = unrealizedProfit;
    this.marginBalance = marginBalance;
    this.maintMargin = maintMargin;
    this.initialMargin = initialMargin;
    this.positionInitialMargin = positionInitialMargin;
    this.openOrderInitialMargin = openOrderInitialMargin;
    this.maxWithdrawAmount = maxWithdrawAmount;
    this.crossWalletBalance = crossWalletBalance;
    this.crossUnPnl = crossUnPnl;
    this.availableBalance = availableBalance;
  }

  public final BigDecimal availableBalance;
}
