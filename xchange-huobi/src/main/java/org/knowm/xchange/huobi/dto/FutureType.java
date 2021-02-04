package org.knowm.xchange.huobi.dto;

/**
 * Created by lin on 2021-01-30.
 */
public enum FutureType {
  CONTRACT,
  SWAP,
  SWAP_LINEAR;

  public boolean is(String t) {
    return name().equals(t);
  }

}
