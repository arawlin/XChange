package org.knowm.xchange.gateiov4.service;

import org.knowm.xchange.currency.CurrencyPair;

/** Created by lin on 2020-10-15. */
public class GateioAdapters {

  private GateioAdapters() {}

  public static String toSymbol(CurrencyPair pair) {
    return pair.base.getCurrencyCode() + "_" + pair.counter.getCurrencyCode();
  }
}
