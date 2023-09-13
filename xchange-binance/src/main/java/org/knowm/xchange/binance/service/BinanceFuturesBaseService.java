package org.knowm.xchange.binance.service;

import org.knowm.xchange.binance.BinanceExchangeSpecification;
import org.knowm.xchange.binance.BinanceFutures;
import org.knowm.xchange.binance.BinanceFuturesCommon;
import org.knowm.xchange.binance.BinanceFuturesExchange;
import org.knowm.xchange.binance.dto.FuturesSettleType;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.service.BaseResilientExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

public class BinanceFuturesBaseService extends BaseResilientExchangeService<BinanceFuturesExchange> {

  protected final Logger logger = LoggerFactory.getLogger(getClass());
  protected final BinanceFutures binance;
  protected final BinanceFuturesCommon binanceCommon;

  protected final BinanceExchangeSpecification specification;
  protected final String apiKey;
  protected final ParamsDigest signatureCreator;
  protected final FuturesSettleType futuresSettleType;

  protected BinanceFuturesBaseService(BinanceFuturesExchange exchange,
                                      BinanceFutures binance,
                                      BinanceFuturesCommon binanceCommon,
                                      ResilienceRegistries resilienceRegistries) {
    super(exchange, resilienceRegistries);

    this.binance = binance;
    this.binanceCommon = binanceCommon;

    this.specification = (BinanceExchangeSpecification) exchange.getExchangeSpecification();
    this.apiKey = specification.getApiKey();
    this.signatureCreator = BinanceHmacDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
    this.futuresSettleType = specification.getFuturesSettleType();
  }

  public Long getRecvWindow() {
    return (Long)
        exchange.getExchangeSpecification().getExchangeSpecificParametersItem("recvWindow");
  }

  public SynchronizedValueFactory<Long> getTimestampFactory() {
    return exchange.getTimestampFactory();
  }

}
