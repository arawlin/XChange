package org.knowm.xchange.binance;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.knowm.xchange.binance.dto.BinanceException;
import org.knowm.xchange.binance.dto.marketdata.BinanceFuturesFundingRate;
import org.knowm.xchange.binance.dto.marketdata.BinanceFuturesPremiumIndex;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public interface BinanceFutures {

  @GET
  @Path("futures/data/openInterestHist")
  void openInterestHist() throws IOException;

  @GET
  @Path("v1/premiumIndex")
  BinanceFuturesPremiumIndex premiumIndex(@QueryParam("symbol") String symbol)
      throws IOException, BinanceException;

  @GET
  @Path("v1/fundingRate")
  List<BinanceFuturesFundingRate> fundingRate(
      @QueryParam("symbol") String symbol,
      @QueryParam("startTime") long startTime,
      @QueryParam("endTime") long endTime,
      @QueryParam("limit") int limit)
      throws IOException, BinanceException;
}
