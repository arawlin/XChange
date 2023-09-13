package org.knowm.xchange.binance.dto.trade;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum FutureOrderType {
  LIMIT,
  MARKET,
  STOP,
  STOP_MARKET,
  TAKE_PROFIT,
  TAKE_PROFIT_MARKET,
  TRAILING_STOP_MARKET;

  @JsonCreator
  public static FutureOrderType getFutureOrderType(String s) {
    try {
      return FutureOrderType.valueOf(s);
    } catch (Exception e) {
      throw new RuntimeException("Unknown future order type " + s + ".");
    }
  }
}
