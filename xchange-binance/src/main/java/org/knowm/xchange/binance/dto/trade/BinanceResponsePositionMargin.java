package org.knowm.xchange.binance.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.binance.dto.BinanceResponse;

import java.math.BigDecimal;

/**
 * Created by lin on 2021-03-29.
 */
public class BinanceResponsePositionMargin extends BinanceResponse {

  public BigDecimal amount;
  public Integer type;

  public BinanceResponsePositionMargin(
      @JsonProperty("amount") BigDecimal amount,
      @JsonProperty("type") Integer type,
      @JsonProperty("code") Integer code,
      @JsonProperty("msg") String msg
  ) {
    super(code, msg);

    this.amount = amount;
    this.type = type;
  }
}
