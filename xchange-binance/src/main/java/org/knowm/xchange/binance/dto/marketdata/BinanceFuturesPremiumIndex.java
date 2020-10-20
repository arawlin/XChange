package org.knowm.xchange.binance.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/** Created by lin on 2020-10-20. */
public class BinanceFuturesPremiumIndex {

  private final String symbol;
  private final BigDecimal markPrice;
  private final BigDecimal indexPrice;
  private final BigDecimal lastFundingRate;
  private final long nextFundingTime;
  private final long time;

  public BinanceFuturesPremiumIndex(
      @JsonProperty("symbol") String symbol,
      @JsonProperty("markPrice") BigDecimal markPrice,
      @JsonProperty("indexPrice") BigDecimal indexPrice,
      @JsonProperty("lastFundingRate") BigDecimal lastFundingRate,
      @JsonProperty("nextFundingTime") long nextFundingTime,
      @JsonProperty("time") long time) {
    this.symbol = symbol;
    this.markPrice = markPrice;
    this.indexPrice = indexPrice;
    this.lastFundingRate = lastFundingRate;
    this.nextFundingTime = nextFundingTime;
    this.time = time;
  }

  public String getSymbol() {
    return symbol;
  }

  public BigDecimal getMarkPrice() {
    return markPrice;
  }

  public BigDecimal getIndexPrice() {
    return indexPrice;
  }

  public BigDecimal getLastFundingRate() {
    return lastFundingRate;
  }

  public long getNextFundingTime() {
    return nextFundingTime;
  }

  public long getTime() {
    return time;
  }
}
