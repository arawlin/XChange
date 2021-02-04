package org.knowm.xchange.huobi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lin on 2021-01-29.
 */
public class HuobiStringResult extends HuobiResult<String> {

  public HuobiStringResult(
      @JsonProperty("status") String status,
      @JsonProperty("data") String data,
      @JsonProperty("err-code") String errCode,
      @JsonProperty("err-msg") String errMsg
  ) {
    super(status, errCode, errMsg, data);
  }
}
