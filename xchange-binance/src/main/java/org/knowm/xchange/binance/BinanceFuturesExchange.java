package org.knowm.xchange.binance;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.binance.dto.FuturesSettleType;
import org.knowm.xchange.binance.dto.meta.exchangeinfo.BinanceExchangeInfo;
import org.knowm.xchange.binance.dto.meta.exchangeinfo.Filter;
import org.knowm.xchange.binance.dto.meta.exchangeinfo.Symbol;
import org.knowm.xchange.binance.service.BinanceFuturesAccountService;
import org.knowm.xchange.binance.service.BinanceFuturesMarketDataService;
import org.knowm.xchange.binance.service.BinanceFuturesTradeService;
import org.knowm.xchange.client.ExchangeRestProxyBuilder;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.meta.InstrumentMetaData;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.instrument.Instrument;
import org.knowm.xchange.utils.AuthUtils;
import si.mazi.rescu.SynchronizedValueFactory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

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

    spec.getResilience().setRetryEnabled(true);
    spec.getResilience().setRateLimiterEnabled(true);

    AuthUtils.setApiAndSecretKey(spec, "binance-futures");

    return spec;
  }

  @Override
  public String getMetaDataFileName(ExchangeSpecification exchangeSpecification) {
    return "binance";
  }

  @Override
  public void remoteInit() {
    try {
      Map<Instrument, InstrumentMetaData> instruments = exchangeMetaData.getInstruments();

      BinanceFuturesMarketDataService marketDataService = (BinanceFuturesMarketDataService) this.marketDataService;
      BinanceExchangeInfo exchangeInfo = marketDataService.getExchangeInfo();
      Symbol[] symbols = exchangeInfo.getSymbols();

      for (Symbol symbol : symbols) {
        if (symbol.getStatus().equals("TRADING")) {
          int basePrecision = Integer.parseInt(symbol.getBaseAssetPrecision());
          int counterPrecision = Integer.parseInt(symbol.getQuotePrecision());
          int pairPrecision = 8;
          int amountPrecision = 8;

          BigDecimal minQty = null;
          BigDecimal maxQty = null;
          BigDecimal stepSize = null;

          BigDecimal counterMinQty = null;
          BigDecimal counterMaxQty = null;

          Filter[] filters = symbol.getFilters();

          CurrencyPair currentCurrencyPair = new CurrencyPair(symbol.getBaseAsset(), symbol.getQuoteAsset());

          for (Filter filter : filters) {
            if (filter.getFilterType().equals("PRICE_FILTER")) {
              pairPrecision = Math.min(pairPrecision, numberOfDecimals(filter.getTickSize()));
              counterMaxQty = new BigDecimal(filter.getMaxPrice()).stripTrailingZeros();
            } else if (filter.getFilterType().equals("LOT_SIZE")) {
              amountPrecision = Math.min(amountPrecision, numberOfDecimals(filter.getStepSize()));
              minQty = new BigDecimal(filter.getMinQty()).stripTrailingZeros();
              maxQty = new BigDecimal(filter.getMaxQty()).stripTrailingZeros();
              stepSize = new BigDecimal(filter.getStepSize()).stripTrailingZeros();
            } else if (filter.getFilterType().equals("MIN_NOTIONAL")) {
              counterMinQty = new BigDecimal(filter.getNotional()).stripTrailingZeros();
            }
          }

          instruments.put(
              currentCurrencyPair,
              new InstrumentMetaData.Builder()
                  .tradingFee(BigDecimal.valueOf(0.1))
                  .minimumAmount(minQty)
                  .maximumAmount(maxQty)
                  .counterMinimumAmount(counterMinQty)
                  .counterMaximumAmount(counterMaxQty)
                  .volumeScale(amountPrecision)
                  .priceScale(pairPrecision)
                  .amountStepSize(stepSize)
                  .marketOrderEnabled(Arrays.asList(symbol.getOrderTypes()).contains("MARKET"))
                  .build());
        } else {
          logger.info("symbol status is not trading. {}", symbol);
        }
      }
    } catch (Exception e) {
      throw new ExchangeException("Failed to initialize: " + e.getMessage(), e);
    }
  }

  private int numberOfDecimals(String value) {
    return new BigDecimal(value).stripTrailingZeros().scale();
  }

}
