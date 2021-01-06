package org.knowm.xchange.binance;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.binance.dto.FuturesSettleType;
import org.knowm.xchange.binance.service.BinanceFuturesAccountService;
import org.knowm.xchange.binance.service.BinanceFuturesMarketDataService;
import org.knowm.xchange.binance.service.BinanceFuturesTradeService;
import org.knowm.xchange.client.ExchangeRestProxyBuilder;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.utils.AuthUtils;
import si.mazi.rescu.SynchronizedValueFactory;

public class BinanceFuturesExchange extends BaseExchange {

  private static ResilienceRegistries RESILIENCE_REGISTRIES;

  private SynchronizedValueFactory<Long> timestampFactory;

  @Override
  protected void initServices() {
    BinanceFutures binance;
    BinanceExchangeSpecification spec = (BinanceExchangeSpecification) getExchangeSpecification();
    if (spec.getFuturesSettleType() == FuturesSettleType.USDT) {
      binance = ExchangeRestProxyBuilder.forInterface(BinanceFuturesUSDT.class, getExchangeSpecification()).build();
    } else if (spec.getFuturesSettleType() == FuturesSettleType.COIN) {
      binance = ExchangeRestProxyBuilder.forInterface(BinanceFuturesCoin.class, getExchangeSpecification()).build();
    } else {
      throw new ExchangeException("Must setFuturesSettleType in BinanceExchangeSpecification.");
    }

    BinanceFuturesCommon binanceCommon = ExchangeRestProxyBuilder.forInterface(BinanceFuturesCommon.class, getExchangeSpecification()).build();

    this.marketDataService = new BinanceFuturesMarketDataService(this, binance, binanceCommon, getResilienceRegistries());
    this.tradeService = new BinanceFuturesTradeService(this, binance, binanceCommon, getResilienceRegistries());
    this.accountService = new BinanceFuturesAccountService(this, binance, binanceCommon, getResilienceRegistries());
    this.timestampFactory = new BinanceFuturesTimestampFactory(binance, getExchangeSpecification().getResilience(), getResilienceRegistries());

  }

  public SynchronizedValueFactory<Long> getTimestampFactory() {
    return timestampFactory;
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    throw new UnsupportedOperationException(
        "Binance uses timestamp/recvwindow rather than a nonce");
  }

  public static void resetResilienceRegistries() {
    RESILIENCE_REGISTRIES = null;
  }

  @Override
  public ResilienceRegistries getResilienceRegistries() {
    if (RESILIENCE_REGISTRIES == null) {
      RESILIENCE_REGISTRIES = BinanceResilience.createRegistries();
    }
    return RESILIENCE_REGISTRIES;
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    BinanceExchangeSpecification spec = new BinanceExchangeSpecification(this.getClass());

    // NOTICE: must set they in code
    spec.setSslUri("");
    spec.setHost("");
    spec.setFuturesSettleType(null);

    spec.setPort(80);
    spec.setExchangeName("BinanceFutures");
    spec.setExchangeDescription("Binance Futures Exchange.");
    spec.setShouldLoadRemoteMetaData(false);

    AuthUtils.setApiAndSecretKey(spec, "binance-futures");

    return spec;
  }

  @Override
  public String getMetaDataFileName(ExchangeSpecification exchangeSpecification) {
    return "binance";
  }
}
