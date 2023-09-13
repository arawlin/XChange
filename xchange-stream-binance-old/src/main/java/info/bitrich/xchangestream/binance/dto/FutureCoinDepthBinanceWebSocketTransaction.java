package info.bitrich.xchangestream.binance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class FutureCoinDepthBinanceWebSocketTransaction
    extends FutureUSDTDepthBinanceWebSocketTransaction {

  protected final String pair;

  public FutureCoinDepthBinanceWebSocketTransaction(
      @JsonProperty("e") String eventType,
      @JsonProperty("E") String eventTime,
      @JsonProperty("T") String transactionTime,
      @JsonProperty("s") String symbol,
      @JsonProperty("ps") String pair,
      @JsonProperty("U") long firstUpdateId,
      @JsonProperty("u") long lastUpdateId,
      @JsonProperty("pu") long lastLastUpdateId,
      @JsonProperty("b") List<Object[]> _bids,
      @JsonProperty("a") List<Object[]> _asks) {
    super(
        eventType,
        eventTime,
        transactionTime,
        symbol,
        firstUpdateId,
        lastUpdateId,
        lastLastUpdateId,
        _bids,
        _asks);

    this.pair = pair;
  }

  public String getTransactionTime() {
    return transactionTime;
  }

  public long getLastLastUpdateId() {
    return lastLastUpdateId;
  }
}
