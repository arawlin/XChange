package org.knowm.xchange.binance.dto;

/** Created by lin on 2020-10-20. */
public enum FuturesSettleType {
  USDT,
  COIN;

  public boolean is(String t) {
    return name().equals(t);
  }
}
