package org.knowm.xchange.binance.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/** Created by lin on 2020-10-22. */
public class BinanceFuturesOpenInterest {

  private final String symbol;
  private final BigDecimal sumOpenInterest;
  private final BigDecimal sumOpenInterestValue;
  private final long timestamp;

  public BinanceFuturesOpenInterest(
      @JsonProperty("symbol") String symbol,
      @JsonProperty("sumOpenInterest") BigDecimal sumOpenInterest,
      @JsonProperty("sumOpenInterestValue") BigDecimal sumOpenInterestValue,
      @JsonProperty("timestamp") long timestamp) {
    this.symbol = symbol;
    this.sumOpenInterest = sumOpenInterest;
    this.sumOpenInterestValue = sumOpenInterestValue;
    this.timestamp = timestamp;
  }

  public String getSymbol() {
    return symbol;
  }

  public BigDecimal getSumOpenInterest() {
    return sumOpenInterest;
  }

  public BigDecimal getSumOpenInterestValue() {
    return sumOpenInterestValue;
  }

  public long getTimestamp() {
    return timestamp;
  }
}
