package org.knowm.xchange.ftx.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class FtxFutureStats {

  @JsonProperty("volume")
  private final BigDecimal volume;

  @JsonProperty("nextFundingRate")
  private final BigDecimal nextFundingRate;

  @JsonProperty("nextFundingTime")
  private final Date nextFundingTime;

  @JsonProperty("expirationPrice")
  private final BigDecimal expirationPrice;

  @JsonProperty("predictedExpirationPrice")
  private final BigDecimal predictedExpirationPrice;

  @JsonProperty("openInterest")
  private final BigDecimal openInterest;

  @JsonProperty("strikePrice")
  private final BigDecimal strikePrice;

  public FtxFutureStats(
          @JsonProperty("volume") BigDecimal volume,
          @JsonProperty("nextFundingRate") BigDecimal nextFundingRate,
          @JsonProperty("nextFundingTime") Date nextFundingTime,
          @JsonProperty("expirationPrice") BigDecimal expirationPrice,
          @JsonProperty("predictedExpirationPrice") BigDecimal predictedExpirationPrice,
          @JsonProperty("openInterest") BigDecimal openInterest,
          @JsonProperty("strikePrice") BigDecimal strikePrice
  ) {
    this.volume = volume;
    this.nextFundingRate = nextFundingRate;
    this.nextFundingTime = nextFundingTime;
    this.expirationPrice = expirationPrice;
    this.predictedExpirationPrice = predictedExpirationPrice;
    this.openInterest = openInterest;
    this.strikePrice = strikePrice;
  }

  public BigDecimal getVolume() {
    return volume;
  }

  public BigDecimal getNextFundingRate() {
    return nextFundingRate;
  }

  public Date getNextFundingTime() {
    return nextFundingTime;
  }

  public BigDecimal getExpirationPrice() {
    return expirationPrice;
  }

  public BigDecimal getPredictedExpirationPrice() {
    return predictedExpirationPrice;
  }

  public BigDecimal getOpenInterest() {
    return openInterest;
  }

  public BigDecimal getStrikePrice() {
    return strikePrice;
  }
}
