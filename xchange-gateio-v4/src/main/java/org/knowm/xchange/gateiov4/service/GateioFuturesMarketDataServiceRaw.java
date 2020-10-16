package org.knowm.xchange.gateiov4.service;

import static org.knowm.xchange.gateiov4.service.GateioResilience.RATE_LIMITER_REQUEST_PER_SECOND_IN_FUTURES;

import java.io.IOException;
import java.util.List;
import org.knowm.xchange.client.ExchangeRestProxyBuilder;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.gateiov4.GateioFutures;
import org.knowm.xchange.gateiov4.GateioFuturesExchange;
import org.knowm.xchange.gateiov4.dto.marketdata.FuturesSettleType;
import org.knowm.xchange.gateiov4.dto.marketdata.GateioContractInfo;
import org.knowm.xchange.gateiov4.dto.marketdata.GateioFundingRate;

/** Created by lin on 2020-10-15. */
public class GateioFuturesMarketDataServiceRaw extends GateioBaseService<GateioFuturesExchange> {

  protected GateioFutures gateio;

  protected GateioFuturesMarketDataServiceRaw(
      GateioFuturesExchange exchange, ResilienceRegistries resilienceRegistries) {
    super(exchange, resilienceRegistries);

    gateio =
        ExchangeRestProxyBuilder.forInterface(
                GateioFutures.class, exchange.getExchangeSpecification())
            .build();
  }

  public GateioContractInfo getContractsInfoOne(FuturesSettleType settle, CurrencyPair pair)
      throws IOException {
    return decorateApiCall(
            () -> gateio.getContractsInfoOne(settle.name(), GateioAdapters.toSymbol(pair)))
        .withRetry(retry("getContractsInfoOne"))
        .withRateLimiter(rateLimiter(RATE_LIMITER_REQUEST_PER_SECOND_IN_FUTURES), 1)
        .call();
  }

  List<GateioFundingRate> getContractFundingRate(
      FuturesSettleType settle, CurrencyPair pair, Integer limit) throws IOException {
    return decorateApiCall(
            () ->
                gateio.getContractFundingRate(settle.name(), GateioAdapters.toSymbol(pair), limit))
        .withRetry(retry("getContractFundingRate"))
        .withRateLimiter(rateLimiter(RATE_LIMITER_REQUEST_PER_SECOND_IN_FUTURES), 1)
        .call();
  }
}
