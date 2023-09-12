package org.knowm.xchange.okcoin.v3.service;

import org.knowm.xchange.okcoin.OkexExchangeV3;
import org.knowm.xchange.okcoin.v3.dto.marketdata.*;

import java.io.IOException;
import java.util.List;

public class OkexMarketDataServiceRaw extends OkexBaseService {

  public OkexMarketDataServiceRaw(OkexExchangeV3 exchange) {
    super(exchange);
  }

  public List<OkexSpotInstrument> getAllSpotInstruments() throws IOException {
    return okex.getAllSpotInstruments();
  }

  public List<OkexSpotTicker> getAllSpotTickers() throws IOException {
    return okex.getAllSpotTickers();
  }

  public OkexSpotTicker getSpotTicker(String instrumentID) throws IOException {
    OkexSpotTicker tokenPairInformation = okex.getSpotTicker(instrumentID);
    return tokenPairInformation;
  }

  public List<OkexFutureInstrument> getAllFutureInstruments() throws IOException {
    return okex.getAllFutureInstruments();
  }

  public List<OkexFutureTicker> getAllFutureTickers() throws IOException {
    return okex.getAllFutureTickers();
  }

  public OkexFutureOrderBook getFuturesOrderBook(String instrumentId, Integer size, String depth) throws IOException {
    return okex.getFuturesOrderBook(instrumentId, size, depth);
  }

  public List<OkexSwapInstrument> getAllSwapInstruments() throws IOException {
    return okex.getAllSwapInstruments();
  }

  public List<OkexSwapTicker> getAllSwapTickers() throws IOException {
    return okex.getAllSwapTickers();
  }

  public OkexFutureOrderBook getSwapOrderBook(String instrumentId, Integer size, String depth) throws IOException {
    return okex.getSwapOrderBook(instrumentId, size, depth);
  }

}
