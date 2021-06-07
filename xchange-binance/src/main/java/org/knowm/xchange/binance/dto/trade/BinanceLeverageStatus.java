package org.knowm.xchange.binance.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by lin on 2021-03-26.
 */
public class BinanceLeverageStatus {

  public Integer leverage;
  public String maxNotionalValue;
  public String symbol;

  public BinanceLeverageStatus(
      @JsonProperty("leverage") Integer leverage,
      @JsonProperty("maxNotionalValue") String maxNotionalValue,
      @JsonProperty("msg") String symbol
  ) {
    this.leverage = leverage;
    this.maxNotionalValue = maxNotionalValue;
    this.symbol = symbol;
  }
}
