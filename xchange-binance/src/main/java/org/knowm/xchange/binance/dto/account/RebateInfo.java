package org.knowm.xchange.binance.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class RebateInfo {

  private final String customerId;
  private final String email;
  private final BigDecimal income;
  private final String asset;
  private final String symbol;
  private final Long time;

  public RebateInfo(
      @JsonProperty("customerId") String customerId,
      @JsonProperty("email") String email,
      @JsonProperty("income") BigDecimal income,
      @JsonProperty("asset") String asset,
      @JsonProperty("symbol") String symbol,
      @JsonProperty("time") Long time
  ) {
    this.customerId = customerId;
    this.email = email;
    this.income = income;
    this.asset = asset;
    this.symbol = symbol;
    this.time = time;
  }

  public String getCustomerId() {
    return customerId;
  }

  public String getEmail() {
    return email;
  }

  public BigDecimal getIncome() {
    return income;
  }

  public String getAsset() {
    return asset;
  }

  public String getSymbol() {
    return symbol;
  }

  public Long getTime() {
    return time;
  }
}
