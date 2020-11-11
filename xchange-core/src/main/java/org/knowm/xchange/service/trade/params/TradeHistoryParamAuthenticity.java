package org.knowm.xchange.service.trade.params;

public interface TradeHistoryParamAuthenticity extends TradeHistoryParams {

  String getApiKey();
  void setApiKey(String apiKey);

  String getSecretKey();
  void setSecretKey(String secretKey);

}
