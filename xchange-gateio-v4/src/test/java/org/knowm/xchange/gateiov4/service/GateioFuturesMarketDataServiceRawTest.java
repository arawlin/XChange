package org.knowm.xchange.gateiov4.service;

import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.gateiov4.GateioFuturesExchange;
import org.knowm.xchange.gateiov4.dto.FuturesSettleType;
import org.knowm.xchange.gateiov4.dto.marketdata.GateioContractInfo;
import org.knowm.xchange.gateiov4.dto.marketdata.GateioFundingRate;
import org.knowm.xchange.utils.Assert;
import org.knowm.xchange.utils.AuthUtils;

/** Created by lin on 2020-10-16. */
public class GateioFuturesMarketDataServiceRawTest {

  private GateioFuturesExchange exchange;
  private GateioFuturesMarketDataService service;

  @Before
  public void setUp() throws Exception {
    String HOST = "api.gateio.ws";
    String URL_API = "https://" + HOST + "/api/v4";

    ExchangeSpecification spec = new ExchangeSpecification(GateioFuturesExchange.class);
    spec.setSslUri(URL_API);
    spec.setHost(HOST);
    spec.setPort(80);
    spec.setExchangeName("GateioV4");
    spec.setExchangeDescription("GateioV4 Exchange.");
    AuthUtils.setApiAndSecretKey(spec, "gateiov4");

    spec.setProxyHost("192.168.1.100");
    spec.setProxyPort(1083);

    exchange = (GateioFuturesExchange) ExchangeFactory.INSTANCE.createExchange(spec);
    service = (GateioFuturesMarketDataService) exchange.getMarketDataService();
  }

  @Test
  public void getContractsInfos() throws IOException {
    List<GateioContractInfo> ls = service.getContractsInfos(FuturesSettleType.btc);
    Assert.notNull(ls, "List<GateioContractInfo> is null");
  }

  @Test
  public void getContractsInfoOne() throws IOException {
    GateioContractInfo i = service.getContractsInfoOne(FuturesSettleType.btc, CurrencyPair.BTC_USD);
    Assert.notNull(i, "GateioContractInfo is null");
  }

  @Test
  public void getContractFundingRate() throws IOException {
    List<GateioFundingRate> ls =
        service.getContractFundingRate(FuturesSettleType.btc, CurrencyPair.BTC_USD, 10);
    Assert.notNull(ls, "List<GateioFundingRate> is null");
  }
}
