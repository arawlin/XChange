package org.knowm.xchange.binance;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.binance.dto.FuturesSettleType;

/** Created by lin on 2020-10-20. */
public class BinanceExchangeSpecification extends ExchangeSpecification {

  private FuturesSettleType futuresSettleType;

  public BinanceExchangeSpecification(Class<? extends Exchange> exchangeClass) {
    super(exchangeClass);
  }

  public FuturesSettleType getFuturesSettleType() {
    return futuresSettleType;
  }

  public void setFuturesSettleType(FuturesSettleType futuresSettleType) {
    this.futuresSettleType = futuresSettleType;
  }
}
