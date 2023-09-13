package org.knowm.xchange.binance.dto.trade;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum FutureIncomeType {
  TRANSFER,
  WELCOME_BONUS,
  REALIZED_PNL,
  FUNDING_FEE,
  COMMISSION,
  INSURANCE_CLEAR,
  DELIVERED_SETTELMENT;

  @JsonCreator
  public static FutureIncomeType getFutureIncomeType(String s) {
    try {
      return FutureIncomeType.valueOf(s);
    } catch (Exception e) {
      throw new RuntimeException("Unknown future income type " + s + ".");
    }
  }
}
