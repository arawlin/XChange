package org.knowm.xchange.okex;

import jakarta.ws.rs.*;
import org.knowm.xchange.okex.dto.OkexException;
import org.knowm.xchange.okex.dto.OkexResponse;
import org.knowm.xchange.okex.dto.marketdata.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/v5")
@Produces(APPLICATION_JSON)
public interface Okex {
  String instrumentsPath = "/public/instruments"; // Stated as 20 req/2 sec

  // To avoid 429s, actual req/second may need to be lowered!
  Map<String, List<Integer>> publicPathRateLimits =
      new HashMap<String, List<Integer>>() {
        {
          put(instrumentsPath, Arrays.asList(8, 1));
        }
      };

  @GET
  @Path(instrumentsPath)
  OkexResponse<List<OkexInstrument>> getInstruments(
      @QueryParam("instType") String instrumentType,
      @QueryParam("uly") String underlying,
      @QueryParam("instId") String instrumentId,
      @HeaderParam("X-SIMULATED-TRADING") String simulatedTrading)
      throws OkexException, IOException;

  @GET
  @Path("/market/trades")
  @Consumes(APPLICATION_JSON)
  OkexResponse<List<OkexTrade>> getTrades(
      @QueryParam("instId") String instrument,
      @QueryParam("limit") int limit,
      @HeaderParam("X-SIMULATED-TRADING") String simulatedTrading)
      throws IOException, OkexException;

  @GET
  @Path("/market/ticker")
  @Consumes(APPLICATION_JSON)
  OkexResponse<List<OkexTicker>> getTicker(
      @QueryParam("instId") String instrument,
      @HeaderParam("X-SIMULATED-TRADING") String simulatedTrading)
      throws IOException, OkexException;

  @GET
  @Path("/market/books")
  OkexResponse<List<OkexOrderbook>> getOrderbook(
      @QueryParam("instId") String instrument,
      @QueryParam("sz") int depth,
      @HeaderParam("X-SIMULATED-TRADING") String simulatedTrading)
      throws IOException, OkexException;

  @GET
  @Path("/market/history-candles")
  OkexResponse<List<OkexCandleStick>> getHistoryCandles(
      @QueryParam("instId") String instrument,
      @QueryParam("after") String after,
      @QueryParam("before") String before,
      @QueryParam("bar") String bar,
      @QueryParam("limit") String limit,
      @HeaderParam("X-SIMULATED-TRADING") String simulatedTrading)
      throws IOException, OkexException;

  @GET
  @Path("/public/funding-rate")
  OkexResponse<List<OkexFundingRate>> getFundingRate(
      @QueryParam("instId") String instrument,
      @HeaderParam("X-SIMULATED-TRADING") String simulatedTrading)
      throws IOException, OkexException;

}
