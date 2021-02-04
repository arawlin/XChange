package org.knowm.xchange.huobi;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.huobi.dto.FutureType;

/**
 * Created by lin on 2021-01-30.
 */
public class HuobiExchangeSpecification extends ExchangeSpecification {

  private FutureType futureType;

  public HuobiExchangeSpecification(Class<? extends Exchange> exchangeClass) {
    super(exchangeClass);
  }

  public FutureType getFutureType() {
    return futureType;
  }

  public void setFutureType(FutureType futureType) {
    this.futureType = futureType;
  }
}
