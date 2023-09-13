package org.knowm.xchange.binance.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/** Created by lin on 2020-10-22. */
public class BinanceTakerLongShortRatio {

  private final BigDecimal buySellRatio;
  private final BigDecimal buyVol;
  private final BigDecimal sellVol;
  private final long timestamp;

  public BinanceTakerLongShortRatio(
      @JsonProperty("buySellRatio") BigDecimal buySellRatio,
      @JsonProperty("buyVol") BigDecimal buyVol,
      @JsonProperty("sellVol") BigDecimal sellVol,
      @JsonProperty("timestamp") long timestamp) {
    this.buySellRatio = buySellRatio;
    this.buyVol = buyVol;
    this.sellVol = sellVol;
    this.timestamp = timestamp;
  }

  public BigDecimal getBuySellRatio() {
    return buySellRatio;
  }

  public BigDecimal getBuyVol() {
    return buyVol;
  }

  public BigDecimal getSellVol() {
    return sellVol;
  }

  public long getTimestamp() {
    return timestamp;
  }
}
