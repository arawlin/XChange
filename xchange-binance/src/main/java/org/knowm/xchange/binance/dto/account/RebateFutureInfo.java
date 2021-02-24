package org.knowm.xchange.binance.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class RebateFutureInfo {

  private final String customerId;
  private final String unit;
  private final BigDecimal tradeVol;
  private final BigDecimal rebateVol;
  private final Long time;

  public RebateFutureInfo(
      @JsonProperty("customerId") String customerId,
      @JsonProperty("unit") String unit,
      @JsonProperty("tradeVol") BigDecimal tradeVol,
      @JsonProperty("rebateVol") BigDecimal rebateVol,
      @JsonProperty("time") Long time
  ) {
    this.customerId = customerId;
    this.unit = unit;
    this.tradeVol = tradeVol;
    this.rebateVol = rebateVol;
    this.time = time;
  }

  public String getCustomerId() {
    return customerId;
  }

  public String getUnit() {
    return unit;
  }

  public BigDecimal getTradeVol() {
    return tradeVol;
  }

  public BigDecimal getRebateVol() {
    return rebateVol;
  }

  public Long getTime() {
    return time;
  }
}
