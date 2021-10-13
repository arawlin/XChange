package org.knowm.xchange.huobi;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.huobi.service.HuobiFutureTradeService;

/**
 * Created by lin on 2021-01-29.
 */
public class HuobiFutureExchange extends BaseExchange implements Exchange {

  @Override
  protected void initServices() {
    this.tradeService = new HuobiFutureTradeService(this);
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {
    HuobiExchangeSpecification exchangeSpecification = new HuobiExchangeSpecification(this.getClass());
    exchangeSpecification.setSslUri("https://api.hbdm.com");
    exchangeSpecification.setHost("api.hbdm.com");
    exchangeSpecification.setPort(80);
    exchangeSpecification.setExchangeName("Huobi Future");

//    exchangeSpecification.setFutureType(FutureType.CONTRACT);

    return exchangeSpecification;
  }
}
