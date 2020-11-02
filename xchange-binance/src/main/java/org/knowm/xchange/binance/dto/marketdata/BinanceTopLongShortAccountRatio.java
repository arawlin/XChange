package org.knowm.xchange.binance.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/** Created by lin on 2020-10-22. */
public class BinanceTopLongShortAccountRatio {

  private final String symbol;
  private final BigDecimal longShortRatio;
  private final BigDecimal longAccount;
  private final BigDecimal shortAccount;
  private final long timestamp;

  public BinanceTopLongShortAccountRatio(
      @JsonProperty("symbol") String symbol,
      @JsonProperty("longShortRatio") BigDecimal longShortRatio,
      @JsonProperty("longAccount") BigDecimal longAccount,
      @JsonProperty("shortAccount") BigDecimal shortAccount,
      @JsonProperty("timestamp") long timestamp) {
    this.symbol = symbol;
    this.longShortRatio = longShortRatio;
    this.longAccount = longAccount;
    this.shortAccount = shortAccount;
    this.timestamp = timestamp;
  }

  public String getSymbol() {
    return symbol;
  }

  public BigDecimal getLongShortRatio() {
    return longShortRatio;
  }

  public BigDecimal getLongAccount() {
    return longAccount;
  }

  public BigDecimal getShortAccount() {
    return shortAccount;
  }

  public long getTimestamp() {
    return timestamp;
  }
}
