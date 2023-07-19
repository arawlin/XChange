package org.knowm.xchange.okcoin.v3.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Arrays;

@Data
public class OkexFutureOrderBook {

  @JsonProperty("timestamp")
  private String timestamp;

  @JsonProperty("time")
  private String time;

  @JsonProperty("bids")
  private OkexFutureOrderBookEntry[] bids;

  @JsonProperty("asks")
  private OkexFutureOrderBookEntry[] asks;

  public String getTimestamp() {

    return timestamp;
  }

  public String getTime() {
    return time;
  }

  public OkexFutureOrderBookEntry[] getBids() {

    return bids;
  }

  public OkexFutureOrderBookEntry[] getAsks() {

    return asks;
  }

  @Override
  public String toString() {
    return "OkexFutureOrderBookEntry [timestamp="
        + timestamp
        + ", bids="
        + Arrays.toString(bids)
        + ", asks="
        + Arrays.toString(asks)
        + "]";
  }
}
