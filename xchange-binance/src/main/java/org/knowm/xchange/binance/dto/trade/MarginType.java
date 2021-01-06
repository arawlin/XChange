package org.knowm.xchange.binance.dto.trade;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MarginType {
  ISOLATED,
  CROSSED;

  @JsonCreator
  public static MarginType getOrderSide(String s) {
    if ("isolated".equals(s)) {
      return ISOLATED;
    } else {
      return CROSSED;
    }
  }
}
