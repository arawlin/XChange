package org.knowm.xchange.binance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lin on 2021-03-26.
 */
public class BinanceResponse {

  public Integer code;
  public String msg;

  public BinanceResponse(
      @JsonProperty("code") Integer code,
      @JsonProperty("msg") String msg
  ) {
    this.code = code;
    this.msg = msg;
  }
}
