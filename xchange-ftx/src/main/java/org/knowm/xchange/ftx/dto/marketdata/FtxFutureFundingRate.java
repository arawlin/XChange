package org.knowm.xchange.ftx.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class FtxFutureFundingRate {

  @JsonProperty("future")
  private final String future;

  @JsonProperty("rate")
  private final BigDecimal rate;

  @JsonProperty("time")
  private final Date time;

  @JsonCreator
  public FtxFutureFundingRate(
          @JsonProperty("future") String future,
          @JsonProperty("rate") BigDecimal rate,
          @JsonProperty("time") Date time
  ) {
    this.future = future;
    this.rate = rate;
    this.time = time;
  }

  public String getFuture() {
    return future;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public Date getTime() {
    return time;
  }
}
