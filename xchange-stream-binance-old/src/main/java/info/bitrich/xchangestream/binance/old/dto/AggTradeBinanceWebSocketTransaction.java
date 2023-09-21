package info.bitrich.xchangestream.binance.old.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.binance.dto.marketdata.BinanceAggTrades;

import java.math.BigDecimal;

public class AggTradeBinanceWebSocketTransaction extends ProductBinanceWebSocketTransaction {

  protected final BinanceAggTrades aggTrades;

  public AggTradeBinanceWebSocketTransaction(
      @JsonProperty("e") String eventType,
      @JsonProperty("E") String eventTime,
      @JsonProperty("s") String symbol,
      @JsonProperty("a") long aggregateTradeId,
      @JsonProperty("p") BigDecimal price,
      @JsonProperty("q") BigDecimal quantity,
      @JsonProperty("f") long firstTradeId,
      @JsonProperty("l") long lastTradeId,
      @JsonProperty("T") long timestamp,
      @JsonProperty("m") boolean buyerMaker
  ) {
    super(eventType, eventTime, symbol);

    aggTrades = new BinanceAggTrades(
        aggregateTradeId,
        price,
        quantity,
        firstTradeId,
        lastTradeId,
        timestamp,
        buyerMaker,
        false
    );
  }

  public BinanceAggTrades getAggTrades() {
    return aggTrades;
  }
}
