package org.knowm.xchange.gateiov4.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/** Created by lin on 2020-10-15. */
public class GateioFundingRate {

  private long time;
  private BigDecimal rate;

  public GateioFundingRate(@JsonProperty("t") long time, @JsonProperty("r") BigDecimal rate) {
    this.time = time;
    this.rate = rate;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public void setRate(BigDecimal rate) {
    this.rate = rate;
  }
}
