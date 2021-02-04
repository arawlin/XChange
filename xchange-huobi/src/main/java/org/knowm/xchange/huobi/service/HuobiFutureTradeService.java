package org.knowm.xchange.huobi.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.service.trade.TradeService;

/**
 * Created by lin on 2021-01-29.
 */
public class HuobiFutureTradeService extends HuobiFutureTradeServiceRaw implements TradeService {

  public HuobiFutureTradeService(Exchange exchange) {
    super(exchange);
  }
}
