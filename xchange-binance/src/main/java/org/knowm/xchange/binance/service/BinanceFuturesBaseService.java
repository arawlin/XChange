package org.knowm.xchange.binance.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.service.BaseResilientExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import si.mazi.rescu.ParamsDigest;

public class BinanceFuturesBaseService<E extends Exchange> extends BaseResilientExchangeService<E> {

  protected final Logger logger = LoggerFactory.getLogger(getClass());

  protected final ParamsDigest signatureCreator;

  protected BinanceFuturesBaseService(E exchange, ResilienceRegistries resilienceRegistries) {

    super(exchange, resilienceRegistries);
    this.signatureCreator =
        BinanceHmacDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
  }

  public Long getRecvWindow() {
    return (Long)
        exchange.getExchangeSpecification().getExchangeSpecificParametersItem("recvWindow");
  }
}
