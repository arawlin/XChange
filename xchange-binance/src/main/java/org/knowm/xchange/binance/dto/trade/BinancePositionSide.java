package org.knowm.xchange.binance.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BinancePositionSide {

  private Boolean dualSidePosition;

  public BinancePositionSide(@JsonProperty("dualSidePosition") Boolean dualSidePosition) {
    this.dualSidePosition = dualSidePosition;
  }

  public Boolean getDualSidePosition() {
    return dualSidePosition;
  }
}
