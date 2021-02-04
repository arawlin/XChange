package org.knowm.xchange.huobi.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.client.ExchangeRestProxyBuilder;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.huobi.*;
import org.knowm.xchange.huobi.dto.FutureType;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import si.mazi.rescu.ParamsDigest;

/**
 * Created by lin on 2021-01-29.
 */
public class HuobiFutureBaseService extends BaseExchangeService implements BaseService {

  protected HuobiFutureBase api;
  protected String apiKeyCreator;
  protected ParamsDigest signatureCreator;
  protected FutureType futureType;

  protected HuobiFutureBaseService(Exchange exchange) {
    super(exchange);

    HuobiExchangeSpecification exchangeSpecification = (HuobiExchangeSpecification) exchange.getExchangeSpecification();
    this.apiKeyCreator = exchangeSpecification.getApiKey();
    this.signatureCreator = HuobiDigest.createInstance(exchangeSpecification.getSecretKey());

    this.futureType = exchangeSpecification.getFutureType();
    switch (this.futureType) {
      case CONTRACT:
        api = ExchangeRestProxyBuilder.forInterface(HuobiContract.class, exchangeSpecification).build();
        break;
      case SWAP:
        api = ExchangeRestProxyBuilder.forInterface(HuobiSwap.class, exchangeSpecification).build();
        break;
      case SWAP_LINEAR:
        api = ExchangeRestProxyBuilder.forInterface(HuobiSwapLinear.class, exchangeSpecification).build();
        break;

      default:
        throw new ExchangeException("future type is error");
    }
  }

}
