package org.knowm.xchange.binance.service;

import java.util.Date;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.trade.params.*;

public class BinanceTradeHistoryParams
    implements TradeHistoryParamCurrencyPair,
        TradeHistoryParamLimit,
        TradeHistoryParamsIdSpan,
        TradeHistoryParamsTimeSpan,
        TradeHistoryParamAuthenticity {

  /** mandatory */
  private CurrencyPair currencyPair;
  /** optional */
  private Integer limit;
  /** optional */
  private String startId;
  /** ignored */
  private String endId;
  /** optional */
  private Date startTime;
  /** optional */
  private Date endTime;

  private String apiKey;
  private String secretKey;

  public BinanceTradeHistoryParams(CurrencyPair currencyPair) {
    this.currencyPair = currencyPair;
  }

  public BinanceTradeHistoryParams() {}

  public CurrencyPair getCurrencyPair() {
    return currencyPair;
  }

  public void setCurrencyPair(CurrencyPair currencyPair) {
    this.currencyPair = currencyPair;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public String getStartId() {
    return startId;
  }

  public void setStartId(String startId) {
    this.startId = startId;
  }

  public String getEndId() {
    return endId;
  }

  public void setEndId(String endId) {
    this.endId = endId;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  @Override
  public String getApiKey() {
    return apiKey;
  }

  @Override
  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  @Override
  public String getSecretKey() {
    return secretKey;
  }

  @Override
  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }
}
