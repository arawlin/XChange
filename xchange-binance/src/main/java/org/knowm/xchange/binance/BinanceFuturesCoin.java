package org.knowm.xchange.binance;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("dapi")
@Produces(MediaType.APPLICATION_JSON)
public interface BinanceFuturesCoin extends BinanceFutures {

  String HOST = "dapi.binance.com";
  String URL = "https://" + HOST;
}
