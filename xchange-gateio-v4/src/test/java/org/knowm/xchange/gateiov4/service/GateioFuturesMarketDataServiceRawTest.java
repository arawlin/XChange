package org.knowm.xchange.gateiov4.service;

import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.gateiov4.GateioFuturesExchange;
import org.knowm.xchange.gateiov4.dto.FuturesSettleType;
import org.knowm.xchange.gateiov4.dto.marketdata.GateioContractInfo;
import org.knowm.xchange.gateiov4.dto.marketdata.GateioFundingRate;
import org.knowm.xchange.utils.Assert;

/** Created by lin on 2020-10-16. */
public class GateioFuturesMarketDataServiceRawTest {

  private GateioFuturesExchange exchange;
  private GateioFuturesMarketDataService service;

  @Before
  public void setUp() throws Exception {
    exchange = ExchangeFactory.INSTANCE.createExchange(GateioFuturesExchange.class);
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
