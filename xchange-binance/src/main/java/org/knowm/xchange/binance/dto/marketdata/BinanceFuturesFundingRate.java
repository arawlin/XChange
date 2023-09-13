package org.knowm.xchange.binance.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/** Created by lin on 2020-10-20. */
public class BinanceFuturesFundingRate {

  private final String symbol;
  private final BigDecimal fundingRate;
  private final long fundingTime;

  public BinanceFuturesFundingRate(
      @JsonProperty("symbol") String symbol,
      @JsonProperty("fundingRate") BigDecimal fundingRate,
      @JsonProperty("fundingTime") long fundingTime) {
    this.symbol = symbol;
    this.fundingRate = fundingRate;
    this.fundingTime = fundingTime;
  }

  public String getSymbol() {
    return symbol;
  }

  public BigDecimal getFundingRate() {
    return fundingRate;
  }

  public long getFundingTime() {
    return fundingTime;
  }
}
