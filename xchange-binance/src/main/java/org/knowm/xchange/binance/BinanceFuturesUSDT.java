package org.knowm.xchange.binance;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.knowm.xchange.binance.dto.BinanceException;
import org.knowm.xchange.binance.dto.account.BinanceFutureAccount;
import org.knowm.xchange.binance.dto.marketdata.BinanceFuturesPremiumIndex;
import org.knowm.xchange.binance.dto.trade.BinanceFuturePositionRisk;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

import java.io.IOException;
import java.util.List;

@Path("fapi")
@Produces(MediaType.APPLICATION_JSON)
public interface BinanceFuturesUSDT extends BinanceFuturesOld {

  String HOST = "fapi.binance.com";
  String URL = "https://" + HOST;

  @GET
  @Path("v1/premiumIndex")
  BinanceFuturesPremiumIndex premiumIndex(@QueryParam("symbol") String symbol)
      throws IOException, BinanceException;

  @GET
  @Path("v1/premiumIndex")
  List<BinanceFuturesPremiumIndex> premiumIndex() throws IOException, BinanceException;

  @GET
  @Path("v1/positionRisk")
  List<BinanceFuturePositionRisk> positionRisk(
      @QueryParam("symbol") String symbol,
      @QueryParam("recvWindow") Long recvWindow,
      @QueryParam("timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam(X_MBX_APIKEY) String apiKey,
      @QueryParam(SIGNATURE) ParamsDigest signature)
      throws IOException, BinanceException;

  @GET
  @Path("v2/account")
  BinanceFutureAccount accountInfo(
      @QueryParam("recvWindow") Long recvWindow,
      @QueryParam("timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam(X_MBX_APIKEY) String apiKey,
      @QueryParam(SIGNATURE) ParamsDigest signature)
      throws IOException, BinanceException;

}
