package org.knowm.xchange.binance;

import org.knowm.xchange.binance.dto.BinanceException;
import org.knowm.xchange.binance.dto.marketdata.BinanceFuturesFundingRate;
import org.knowm.xchange.binance.dto.marketdata.BinanceFuturesOpenInterest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public interface BinanceFuturesCommon {

  @GET
  @Path("futures/data/openInterestHist")
  List<BinanceFuturesOpenInterest> openInterestHist(
      @QueryParam("symbol") String symbol,
      @QueryParam("period") String period,
      @QueryParam("limit") int limit,
      @QueryParam("startTime") long startTime,
      @QueryParam("endTime") long endTime
  ) throws IOException, BinanceException;
}
