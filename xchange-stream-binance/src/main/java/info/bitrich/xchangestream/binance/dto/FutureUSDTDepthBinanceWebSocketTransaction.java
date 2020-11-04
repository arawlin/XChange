package info.bitrich.xchangestream.binance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class FutureUSDTDepthBinanceWebSocketTransaction extends DepthBinanceWebSocketTransaction {

  protected final String transactionTime;
  protected final long lastLastUpdateId;

  public FutureUSDTDepthBinanceWebSocketTransaction(
      @JsonProperty("e") String eventType,
      @JsonProperty("E") String eventTime,
      @JsonProperty("T") String transactionTime,
      @JsonProperty("s") String symbol,
      @JsonProperty("U") long firstUpdateId,
      @JsonProperty("u") long lastUpdateId,
      @JsonProperty("pu") long lastLastUpdateId,
      @JsonProperty("b") List<Object[]> _bids,
      @JsonProperty("a") List<Object[]> _asks) {
    super(eventType, eventTime, symbol, firstUpdateId, lastUpdateId, _bids, _asks);

    this.transactionTime = transactionTime;
    this.lastLastUpdateId = lastLastUpdateId;
  }

  public String getTransactionTime() {
    return transactionTime;
  }

  public long getLastLastUpdateId() {
    return lastLastUpdateId;
  }
}
