package org.knowm.xchange.ftx.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class FtxFutureFundingRates {

  private final List<FtxFutureFundingRate> list;

  @JsonCreator
  public FtxFutureFundingRates(List<FtxFutureFundingRate> list) {
    this.list = list;
  }

  public List<FtxFutureFundingRate> getList() {
    return list;
  }

}
