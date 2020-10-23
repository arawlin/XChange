package org.knowm.xchange.binance;

import org.knowm.xchange.binance.dto.BinanceException;
import org.knowm.xchange.binance.dto.marketdata.BinanceFuturesFundingRate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public interface BinanceFutures {

  @GET
  @Path("v1/fundingRate")
  List<BinanceFuturesFundingRate> fundingRate(
      @QueryParam("symbol") String symbol,
      @QueryParam("startTime") long startTime,
      @QueryParam("endTime") long endTime,
      @QueryParam("limit") int limit)
      throws IOException, BinanceException;
}
