package org.knowm.xchange.binance.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class BinanceRestrictions {

  public final Boolean ipRestrict;
  public final Boolean enableWithdrawals;
  public final Boolean enableInternalTransfer;
  public final Boolean permitsUniversalTransfer;
  public final Boolean enableVanillaOptions;
  public final Boolean enableReading;
  public final Boolean enableFutures;
  public final Boolean enableMargin;
  public final Boolean enableSpotAndMarginTrading;

  public final Long createTime;
  public final Long tradingAuthorityExpirationTime;

  public BinanceRestrictions(
          @JsonProperty("ipRestrict") Boolean ipRestrict,
          @JsonProperty("enableWithdrawals") Boolean enableWithdrawals,
          @JsonProperty("enableInternalTransfer") Boolean enableInternalTransfer,
          @JsonProperty("permitsUniversalTransfer") Boolean permitsUniversalTransfer,
          @JsonProperty("enableVanillaOptions") Boolean enableVanillaOptions,
          @JsonProperty("enableReading") Boolean enableReading,
          @JsonProperty("enableFutures") Boolean enableFutures,
          @JsonProperty("enableMargin") Boolean enableMargin,
          @JsonProperty("enableSpotAndMarginTrading") Boolean enableSpotAndMarginTrading,
          @JsonProperty("createTime") Long createTime,
          @JsonProperty("tradingAuthorityExpirationTime") Long tradingAuthorityExpirationTime
  ) {
    this.ipRestrict = ipRestrict;
    this.enableWithdrawals = enableWithdrawals;
    this.enableInternalTransfer = enableInternalTransfer;
    this.permitsUniversalTransfer = permitsUniversalTransfer;
    this.enableVanillaOptions = enableVanillaOptions;
    this.enableReading = enableReading;
    this.enableFutures = enableFutures;
    this.enableMargin = enableMargin;
    this.enableSpotAndMarginTrading = enableSpotAndMarginTrading;
    this.createTime = createTime;
    this.tradingAuthorityExpirationTime = tradingAuthorityExpirationTime;
  }

}
