package org.knowm.xchange.binance;

import org.knowm.xchange.binance.dto.BinanceException;
import org.knowm.xchange.binance.dto.marketdata.BinanceFuturesPremiumIndex;
import org.knowm.xchange.binance.dto.trade.BinanceFuturePositionRisk;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("fapi")
@Produces(MediaType.APPLICATION_JSON)
public interface BinanceFuturesUSDT extends BinanceFutures {

  String HOST = "fapi.binance.com";
  String URL = "https://" + HOST;

  @GET
  @Path("v1/premiumIndex")
  BinanceFuturesPremiumIndex premiumIndex(@QueryParam("symbol") String symbol)
      throws IOException, BinanceException;

  @GET
  @Path("v1/positionRisk")
  List<BinanceFuturePositionRisk> positionRisk(
      @QueryParam("symbol") String symbol,
      @QueryParam("recvWindow") Long recvWindow,
      @QueryParam("timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam(X_MBX_APIKEY) String apiKey,
      @QueryParam(SIGNATURE) ParamsDigest signature)
      throws IOException, BinanceException;

}
