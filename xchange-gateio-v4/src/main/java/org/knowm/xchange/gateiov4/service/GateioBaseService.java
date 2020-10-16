package org.knowm.xchange.gateiov4.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.service.BaseResilientExchangeService;

/** Created by lin on 2020-10-15. */
public class GateioBaseService<E extends Exchange> extends BaseResilientExchangeService<E> {

  protected GateioBaseService(E exchange, ResilienceRegistries resilienceRegistries) {
    super(exchange, resilienceRegistries);
  }
}
