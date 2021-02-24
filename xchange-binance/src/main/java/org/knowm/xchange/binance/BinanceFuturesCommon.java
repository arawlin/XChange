package org.knowm.xchange.binance;

import org.knowm.xchange.binance.dto.BinanceException;
import org.knowm.xchange.binance.dto.account.IfNewUser;
import org.knowm.xchange.binance.dto.account.RebateFutureInfo;
import org.knowm.xchange.binance.dto.marketdata.*;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public interface BinanceFuturesCommon {

  String SIGNATURE = "signature";
  String X_MBX_APIKEY = "X-MBX-APIKEY";

  @GET
  @Path("futures/data/openInterestHist")
  List<BinanceFuturesOpenInterest> openInterestHist(
      @QueryParam("symbol") String symbol,
      @QueryParam("period") String period,
      @QueryParam("limit") Integer limit,
      @QueryParam("startTime") Long startTime,
      @QueryParam("endTime") Long endTime)
      throws IOException, BinanceException;

  @GET
  @Path("futures/data/topLongShortAccountRatio")
  List<BinanceTopLongShortAccountRatio> topLongShortAccountRatio(
      @QueryParam("symbol") String symbol,
      @QueryParam("period") String period,
      @QueryParam("limit") Integer limit,
      @QueryParam("startTime") Long startTime,
      @QueryParam("endTime") Long endTime)
      throws IOException, BinanceException;

  @GET
  @Path("futures/data/topLongShortPositionRatio")
  List<BinanceTopLongShortPositionRatio> topLongShortPositionRatio(
      @QueryParam("symbol") String symbol,
      @QueryParam("period") String period,
      @QueryParam("limit") Integer limit,
      @QueryParam("startTime") Long startTime,
      @QueryParam("endTime") Long endTime)
      throws IOException, BinanceException;

  @GET
  @Path("futures/data/globalLongShortAccountRatio")
  List<BinanceGlobalLongShortAccountRatio> globalLongShortAccountRatio(
      @QueryParam("symbol") String symbol,
      @QueryParam("period") String period,
      @QueryParam("limit") Integer limit,
      @QueryParam("startTime") Long startTime,
      @QueryParam("endTime") Long endTime)
      throws IOException, BinanceException;

  @GET
  @Path("futures/data/takerlongshortRatio")
  List<BinanceTakerLongShortRatio> takerlongshortRatio(
      @QueryParam("symbol") String symbol,
      @QueryParam("period") String period,
      @QueryParam("limit") Integer limit,
      @QueryParam("startTime") Long startTime,
      @QueryParam("endTime") Long endTime)
      throws IOException, BinanceException;

  @GET
  @Path("/fapi/v1/apiReferral/ifNewUser")
  IfNewUser ifNewUser(
      @QueryParam("brokerId") String brokerId,
      @QueryParam("type") Integer type,
      @QueryParam("recvWindow") Long recvWindow,
      @QueryParam("timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam(X_MBX_APIKEY) String apiKey,
      @QueryParam(SIGNATURE) ParamsDigest signature)
      throws IOException, BinanceException;

  @POST
  @Path("/fapi/v1/apiReferral/userCustomization")
  Map<String, String> userCustomizationSet(
      @FormParam("brokerId") String brokerId,
      @FormParam("customerId") String customerId,
      @FormParam("recvWindow") Long recvWindow,
      @FormParam("timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam(X_MBX_APIKEY) String apiKey,
      @QueryParam(SIGNATURE) ParamsDigest signature)
      throws IOException, BinanceException;

  @GET
  @Path("/fapi/v1/apiReferral/userCustomization")
  Map<String, String> userCustomizationGet(
      @QueryParam("brokerId") String brokerId,
      @QueryParam("recvWindow") Long recvWindow,
      @QueryParam("timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam(X_MBX_APIKEY) String apiKey,
      @QueryParam(SIGNATURE) ParamsDigest signature)
      throws IOException, BinanceException;

  @GET
  @Path("/fapi/v1/apiReferral/traderSummary")
  List<RebateFutureInfo> rebateRecentRecord(
      @QueryParam("customerId") String customerId,
      @QueryParam("type") Integer type,
      @QueryParam("startTime") Long startTime,
      @QueryParam("endTime") Long endTime,
      @QueryParam("limit") Integer limit,
      @QueryParam("recvWindow") Long recvWindow,
      @QueryParam("timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam(X_MBX_APIKEY) String apiKey,
      @QueryParam(SIGNATURE) ParamsDigest signature)
      throws IOException, BinanceException;

}
