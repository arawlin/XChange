package org.knowm.xchange.ftx.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class FtxFutureInfo {

  @JsonProperty("ask")
  private final BigDecimal ask;

  @JsonProperty("bid")
  private final BigDecimal bid;

  @JsonProperty("change1h")
  private final BigDecimal change1h;

  @JsonProperty("change24h")
  private final BigDecimal change24h;

  @JsonProperty("changeBod")
  private final BigDecimal changeBod;

  @JsonProperty("volumeUsd24h")
  private final BigDecimal volumeUsd24h;

  @JsonProperty("volume")
  private final BigDecimal volume;

  @JsonProperty("description")
  private final String description;

  @JsonProperty("enabled")
  private final boolean enabled;

  @JsonProperty("expired")
  private final boolean expired;

  @JsonProperty("expiry")
  private final String expiry;

  @JsonProperty("index")
  private final BigDecimal index;

  @JsonProperty("imfFactor")
  private final BigDecimal imfFactor;

  @JsonProperty("last")
  private final BigDecimal last;

  @JsonProperty("lowerBound")
  private final BigDecimal lowerBound;

  @JsonProperty("mark")
  private final BigDecimal mark;

  @JsonProperty("name")
  private final String name;

  @JsonProperty("openInterest")
  private final BigDecimal openInterest;

  @JsonProperty("openInterestUsd")
  private final BigDecimal openInterestUsd;

  @JsonProperty("perpetual")
  private final boolean perpetual;

  @JsonProperty("positionLimitWeight")
  private final BigDecimal positionLimitWeight;

  @JsonProperty("postOnly")
  private final boolean postOnly;

  @JsonProperty("priceIncrement")
  private final BigDecimal priceIncrement;

  @JsonProperty("sizeIncrement")
  private final BigDecimal sizeIncrement;

  @JsonProperty("underlying")
  private final String underlying;

  @JsonProperty("upperBound")
  private final BigDecimal upperBound;

  @JsonProperty("type")
  private final String type;

  @JsonCreator
  public FtxFutureInfo(
          @JsonProperty("ask") BigDecimal ask,
          @JsonProperty("bid") BigDecimal bid,
          @JsonProperty("change1h") BigDecimal change1h,
          @JsonProperty("change24h") BigDecimal change24h,
          @JsonProperty("changeBod") BigDecimal changeBod,
          @JsonProperty("volumeUsd24h") BigDecimal volumeUsd24h,
          @JsonProperty("volume") BigDecimal volume,
          @JsonProperty("description") String description,
          @JsonProperty("enabled") boolean enabled,
          @JsonProperty("expired") boolean expired,
          @JsonProperty("expiry") String expiry,
          @JsonProperty("index") BigDecimal index,
          @JsonProperty("imfFactor") BigDecimal imfFactor,
          @JsonProperty("last") BigDecimal last,
          @JsonProperty("lowerBound") BigDecimal lowerBound,
          @JsonProperty("mark") BigDecimal mark,
          @JsonProperty("name") String name,
          @JsonProperty("openInterest") BigDecimal openInterest,
          @JsonProperty("openInterestUsd") BigDecimal openInterestUsd,
          @JsonProperty("perpetual") boolean perpetual,
          @JsonProperty("positionLimitWeight") BigDecimal positionLimitWeight,
          @JsonProperty("postOnly") boolean postOnly,
          @JsonProperty("priceIncrement") BigDecimal priceIncrement,
          @JsonProperty("sizeIncrement") BigDecimal sizeIncrement,
          @JsonProperty("underlying") String underlying,
          @JsonProperty("upperBound") BigDecimal upperBound,
          @JsonProperty("type") String type
  ) {
    this.ask = ask;
    this.bid = bid;
    this.change1h = change1h;
    this.change24h = change24h;
    this.changeBod = changeBod;
    this.volumeUsd24h = volumeUsd24h;
    this.volume = volume;
    this.description = description;
    this.enabled = enabled;
    this.expired = expired;
    this.expiry = expiry;
    this.index = index;
    this.imfFactor = imfFactor;
    this.last = last;
    this.lowerBound = lowerBound;
    this.mark = mark;
    this.name = name;
    this.openInterest = openInterest;
    this.openInterestUsd = openInterestUsd;
    this.perpetual = perpetual;
    this.positionLimitWeight = positionLimitWeight;
    this.postOnly = postOnly;
    this.priceIncrement = priceIncrement;
    this.sizeIncrement = sizeIncrement;
    this.underlying = underlying;
    this.upperBound = upperBound;
    this.type = type;
  }

  public BigDecimal getAsk() {
    return ask;
  }

  public BigDecimal getBid() {
    return bid;
  }

  public BigDecimal getChange1h() {
    return change1h;
  }

  public BigDecimal getChange24h() {
    return change24h;
  }

  public BigDecimal getChangeBod() {
    return changeBod;
  }

  public BigDecimal getVolumeUsd24h() {
    return volumeUsd24h;
  }

  public BigDecimal getVolume() {
    return volume;
  }

  public String getDescription() {
    return description;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public boolean isExpired() {
    return expired;
  }

  public String getExpiry() {
    return expiry;
  }

  public BigDecimal getIndex() {
    return index;
  }

  public BigDecimal getImfFactor() {
    return imfFactor;
  }

  public BigDecimal getLast() {
    return last;
  }

  public BigDecimal getLowerBound() {
    return lowerBound;
  }

  public BigDecimal getMark() {
    return mark;
  }

  public String getName() {
    return name;
  }

  public BigDecimal getOpenInterest() {
    return openInterest;
  }

  public BigDecimal getOpenInterestUsd() {
    return openInterestUsd;
  }

  public boolean isPerpetual() {
    return perpetual;
  }

  public BigDecimal getPositionLimitWeight() {
    return positionLimitWeight;
  }

  public boolean isPostOnly() {
    return postOnly;
  }

  public BigDecimal getPriceIncrement() {
    return priceIncrement;
  }

  public BigDecimal getSizeIncrement() {
    return sizeIncrement;
  }

  public String getUnderlying() {
    return underlying;
  }

  public BigDecimal getUpperBound() {
    return upperBound;
  }

  public String getType() {
    return type;
  }
}
