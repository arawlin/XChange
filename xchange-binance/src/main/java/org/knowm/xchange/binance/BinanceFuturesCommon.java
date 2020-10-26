package org.knowm.xchange.binance;

import org.knowm.xchange.binance.dto.BinanceException;
import org.knowm.xchange.binance.dto.marketdata.*;

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

  @GET
  @Path("futures/data/topLongShortAccountRatio")
  List<BinanceTopLongShortAccountRatio> topLongShortAccountRatio(
      @QueryParam("symbol") String symbol,
      @QueryParam("period") String period,
      @QueryParam("limit") int limit,
      @QueryParam("startTime") long startTime,
      @QueryParam("endTime") long endTime
  ) throws IOException, BinanceException;

  @GET
  @Path("futures/data/topLongShortPositionRatio")
  List<BinanceTopLongShortPositionRatio> topLongShortPositionRatio(
      @QueryParam("symbol") String symbol,
      @QueryParam("period") String period,
      @QueryParam("limit") int limit,
      @QueryParam("startTime") long startTime,
      @QueryParam("endTime") long endTime
  ) throws IOException, BinanceException;

  @GET
  @Path("futures/data/globalLongShortAccountRatio")
  List<BinanceGlobalLongShortAccountRatio> globalLongShortAccountRatio(
      @QueryParam("symbol") String symbol,
      @QueryParam("period") String period,
      @QueryParam("limit") int limit,
      @QueryParam("startTime") long startTime,
      @QueryParam("endTime") long endTime
  ) throws IOException, BinanceException;


  @GET
  @Path("futures/data/takerlongshortRatio")
  List<BinanceTakerLongShortRatio> takerlongshortRatio(
      @QueryParam("symbol") String symbol,
      @QueryParam("period") String period,
      @QueryParam("limit") int limit,
      @QueryParam("startTime") long startTime,
      @QueryParam("endTime") long endTime
  ) throws IOException, BinanceException;

}
