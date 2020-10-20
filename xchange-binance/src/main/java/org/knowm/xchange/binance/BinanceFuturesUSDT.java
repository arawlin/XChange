package org.knowm.xchange.binance;

import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.knowm.xchange.binance.dto.BinanceException;
import org.knowm.xchange.binance.dto.marketdata.BinanceFuturesPremiumIndex;

@Path("fapi")
@Produces(MediaType.APPLICATION_JSON)
public interface BinanceFuturesUSDT extends BinanceFutures {

  String HOST = "fapi.binance.com";
  String URL = "https://" + HOST;

  @GET
  @Path("v1/premiumIndex")
  BinanceFuturesPremiumIndex premiumIndex(@QueryParam("symbol") String symbol)
      throws IOException, BinanceException;
}
