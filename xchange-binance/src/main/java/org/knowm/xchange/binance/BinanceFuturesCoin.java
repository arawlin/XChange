package org.knowm.xchange.binance;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.knowm.xchange.binance.dto.BinanceException;
import org.knowm.xchange.binance.dto.marketdata.BinanceFuturesPremiumIndex;

@Path("dapi")
@Produces(MediaType.APPLICATION_JSON)
public interface BinanceFuturesCoin extends BinanceFutures {

  String HOST = "dapi.binance.com";
  String URL = "https://" + HOST;

  @GET
  @Path("v1/premiumIndex")
  List<BinanceFuturesPremiumIndex> premiumIndex(
      @QueryParam("symbol") String symbol, @QueryParam("pair") String pair)
      throws IOException, BinanceException;
}
