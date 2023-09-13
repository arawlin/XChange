package org.knowm.xchange.binance.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.binance.dto.trade.BinanceFuturePosition;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lin on 2021-01-06.
 */
public final class BinanceFutureAccount {

  public final List<BinanceFutureAsset> assets;
  public final List<BinanceFuturePosition> positions;
  public final Boolean canDeposit;
  public final Boolean canTrade;
  public final Boolean canWithdraw;
  public final Integer feeTier;
  public final Long updateTime;

  public final BigDecimal totalInitialMargin;
  public final BigDecimal totalMaintMargin;
  public final BigDecimal totalWalletBalance;
  public final BigDecimal totalUnrealizedProfit;
  public final BigDecimal totalMarginBalance;
  public final BigDecimal totalPositionInitialMargin;
  public final BigDecimal totalOpenOrderInitialMargin;
  public final BigDecimal totalCrossWalletBalance;
  public final BigDecimal totalCrossUnPnl;
  public final BigDecimal availableBalance;
  public final BigDecimal maxWithdrawAmount;

  public BinanceFutureAccount(
      @JsonProperty("assets") List<BinanceFutureAsset> assets,
      @JsonProperty("positions") List<BinanceFuturePosition> positions,
      @JsonProperty("canDeposit") Boolean canDeposit,
      @JsonProperty("canTrade") Boolean canTrade,
      @JsonProperty("canWithdraw") Boolean canWithdraw,
      @JsonProperty("feeTier") Integer feeTier,
      @JsonProperty("updateTime") Long updateTime,
      @JsonProperty("totalInitialMargin") BigDecimal totalInitialMargin,
      @JsonProperty("totalMaintMargin") BigDecimal totalMaintMargin,
      @JsonProperty("totalWalletBalance") BigDecimal totalWalletBalance,
      @JsonProperty("totalUnrealizedProfit") BigDecimal totalUnrealizedProfit,
      @JsonProperty("totalMarginBalance") BigDecimal totalMarginBalance,
      @JsonProperty("totalPositionInitialMargin") BigDecimal totalPositionInitialMargin,
      @JsonProperty("totalOpenOrderInitialMargin") BigDecimal totalOpenOrderInitialMargin,
      @JsonProperty("totalCrossWalletBalance") BigDecimal totalCrossWalletBalance,
      @JsonProperty("totalCrossUnPnl") BigDecimal totalCrossUnPnl,
      @JsonProperty("availableBalance") BigDecimal availableBalance,
      @JsonProperty("maxWithdrawAmount") BigDecimal maxWithdrawAmount
  ) {
    this.assets = assets;
    this.positions = positions;
    this.canDeposit = canDeposit;
    this.canTrade = canTrade;
    this.canWithdraw = canWithdraw;
    this.feeTier = feeTier;
    this.updateTime = updateTime;
    this.totalInitialMargin = totalInitialMargin;
    this.totalMaintMargin = totalMaintMargin;
    this.totalWalletBalance = totalWalletBalance;
    this.totalUnrealizedProfit = totalUnrealizedProfit;
    this.totalMarginBalance = totalMarginBalance;
    this.totalPositionInitialMargin = totalPositionInitialMargin;
    this.totalOpenOrderInitialMargin = totalOpenOrderInitialMargin;
    this.totalCrossWalletBalance = totalCrossWalletBalance;
    this.totalCrossUnPnl = totalCrossUnPnl;
    this.availableBalance = availableBalance;
    this.maxWithdrawAmount = maxWithdrawAmount;
  }
}
