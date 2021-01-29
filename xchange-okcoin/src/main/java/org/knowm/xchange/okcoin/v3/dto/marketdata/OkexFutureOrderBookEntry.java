package org.knowm.xchange.okcoin.v3.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class OkexFutureOrderBookEntry {

  private BigDecimal price;
  private BigDecimal volume;
  private Integer numOrdersOnLevelForce;
  private Integer numOrdersOnLevel;

  @JsonCreator
  public OkexFutureOrderBookEntry(
      @JsonProperty("price") BigDecimal price,
      @JsonProperty("volume") BigDecimal volume,
      @JsonProperty("numOrdersOnLevelForce") Integer numOrdersOnLevelForce,
      @JsonProperty("numOrdersOnLevel") Integer numOrdersOnLevel) {

    this.price = price;
    this.volume = volume;
    this.numOrdersOnLevelForce = numOrdersOnLevelForce;
    this.numOrdersOnLevel = numOrdersOnLevel;
  }

  public BigDecimal getPrice() {

    return price;
  }

  public BigDecimal getVolume() {

    return volume;
  }

  public Integer getNumOrdersOnLevel() {
    return numOrdersOnLevel;
  }

  public Integer getNumOrdersOnLevelForce() {
    return numOrdersOnLevelForce;
  }

  @Override
  public String toString() {
    return "OkexOrderBookEntry [price="
        + price
        + ","
        + " volume="
        + volume
        + ","
        + " numOrdersOnLevel="
        + numOrdersOnLevel
        + "]";
  }
}
