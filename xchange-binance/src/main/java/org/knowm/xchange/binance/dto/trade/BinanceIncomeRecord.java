package org.knowm.xchange.binance.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public final class BinanceIncomeRecord {

  public final String symbol;
  public final FutureIncomeType incomeType;
  public final BigDecimal income;
  public final String asset;
  public final String info;
  public final long time;
  public final String tranId;
  public final String tradeId;

  public BinanceIncomeRecord(
      @JsonProperty("symbol") String symbol,
      @JsonProperty("incomeType") FutureIncomeType incomeType,
      @JsonProperty("income") BigDecimal income,
      @JsonProperty("asset") String asset,
      @JsonProperty("info") String info,
      @JsonProperty("time") long time,
      @JsonProperty("tranId") String tranId,
      @JsonProperty("tradeId") String tradeId
  ) {
    this.symbol = symbol;
    this.incomeType = incomeType;
    this.income = income;
    this.asset = asset;
    this.info = info;
    this.time = time;
    this.tranId = tranId;
    this.tradeId = tradeId;
  }

}
