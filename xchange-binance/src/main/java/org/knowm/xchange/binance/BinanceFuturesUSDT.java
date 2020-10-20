package org.knowm.xchange.binance;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("fapi")
@Produces(MediaType.APPLICATION_JSON)
public interface BinanceFuturesUSDT extends BinanceFutures {

  String HOST = "fapi.binance.com";
  String URL = "https://" + HOST;
}
