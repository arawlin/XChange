package org.knowm.xchange.gateiov4;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.gateiov4.service.GateioFuturesMarketDataService;
import org.knowm.xchange.gateiov4.service.GateioResilience;
import org.knowm.xchange.utils.AuthUtils;
import si.mazi.rescu.SynchronizedValueFactory;

/** Created by lin on 2020-10-15. */
public class GateioFuturesExchange extends BaseExchange {

  private static final String HOST = "api.gateio.ws";
  //  private static final String HOST = "fx-api-testnet.gateio.ws";
  private static final String URL_API = "https://" + HOST + "/api/v4";

  private static ResilienceRegistries RESILIENCE_REGISTRIES;

  @Override
  protected void initServices() {
    this.marketDataService = new GateioFuturesMarketDataService(this, getResilienceRegistries());
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    return null;
  }

  public static void resetResilienceRegistries() {
    RESILIENCE_REGISTRIES = null;
  }

  @Override
  public ResilienceRegistries getResilienceRegistries() {
    if (RESILIENCE_REGISTRIES == null) {
      RESILIENCE_REGISTRIES = GateioResilience.createRegistries();
    }
    return RESILIENCE_REGISTRIES;
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {
    ExchangeSpecification spec = new ExchangeSpecification(this.getClass());
    spec.setSslUri(URL_API);
    spec.setHost(HOST);
    spec.setPort(80);
    spec.setExchangeName("Gateio");
    spec.setExchangeDescription("Gateio Exchange.");
    AuthUtils.setApiAndSecretKey(spec, "gateiov4");
    return spec;
  }
}
