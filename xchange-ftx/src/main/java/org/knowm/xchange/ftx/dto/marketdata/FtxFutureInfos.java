package org.knowm.xchange.ftx.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class FtxFutureInfos {

  private final List<FtxFutureInfo> list;

  @JsonCreator
  public FtxFutureInfos(List<FtxFutureInfo> list) {
    this.list = list;
  }

  public List<FtxFutureInfo> getList() {
    return list;
  }

}
