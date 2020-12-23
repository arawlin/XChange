package org.knowm.xchange.binance;

import org.knowm.xchange.binance.dto.BinanceException;
import org.knowm.xchange.binance.dto.marketdata.BinanceAggTrades;
import org.knowm.xchange.binance.dto.marketdata.BinanceFuturesFundingRate;
import org.knowm.xchange.binance.dto.marketdata.BinanceOrderbook;
import org.knowm.xchange.binance.dto.meta.BinanceTime;
import org.knowm.xchange.binance.dto.trade.*;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public interface BinanceFutures {

  String SIGNATURE = "signature";
  String X_MBX_APIKEY = "X-MBX-APIKEY";

  String HOST_TEST = "testnet.binancefuture.com";
  String URL_TEST = "https://" + HOST_TEST;


  @GET
  @Path("v1/time")
  BinanceTime time() throws IOException;

  @GET
  @Path("v1/fundingRate")
  List<BinanceFuturesFundingRate> fundingRate(
      @QueryParam("symbol") String symbol,
      @QueryParam("startTime") Long startTime,
      @QueryParam("endTime") Long endTime,
      @QueryParam("limit") Integer limit)
      throws IOException, BinanceException;

  @GET
  @Path("v1/depth")
  BinanceOrderbook depth(@QueryParam("symbol") String symbol, @QueryParam("limit") Integer limit)
      throws IOException, BinanceException;

  @GET
  @Path("v1/allForceOrders")
  List<BinanceOrder> allForceOrders(
      @QueryParam("symbol") String symbol,
      @QueryParam("startTime") Long startTime,
      @QueryParam("endTime") Long endTime,
      @QueryParam("limit") Integer limit)
      throws IOException, BinanceException;

  @GET
  @Path("v1/aggTrades")
  List<BinanceAggTrades> aggTrades(
      @QueryParam("symbol") String symbol,
      @QueryParam("fromId") Long fromId,
      @QueryParam("startTime") Long startTime,
      @QueryParam("endTime") Long endTime,
      @QueryParam("limit") Integer limit)
      throws IOException, BinanceException;

  @GET
  @Path("v1/klines")
  List<Object[]> klines(
      @QueryParam("symbol") String symbol,
      @QueryParam("interval") String interval,
      @QueryParam("limit") Integer limit,
      @QueryParam("startTime") Long startTime,
      @QueryParam("endTime") Long endTime)
      throws IOException, BinanceException;


  @POST
  @Path("v1/order")
  BinanceFutureNewOrder newOrder(
      @FormParam("symbol") String symbol,
      @FormParam("side") OrderSide side,
      @FormParam("positionSide") PositionSide positionSide,
      @FormParam("type") FutureOrderType type,
      @FormParam("reduceOnly") Boolean reduceOnly,
      @FormParam("quantity") BigDecimal quantity,
      @FormParam("price") BigDecimal price,
      @FormParam("newClientOrderId") String newClientOrderId,
      @FormParam("stopPrice") BigDecimal stopPrice,
      @FormParam("closePosition") Boolean closePosition,
      @FormParam("activationPrice") Boolean activationPrice,
      @FormParam("callbackRate") Boolean callbackRate,
      @FormParam("timeInForce") TimeInForce timeInForce,
      @FormParam("workingType") WorkingType workingType,
      @FormParam("priceProtect") Boolean priceProtect,
      @FormParam("newOrderRespType") NewOrderRespType newOrderRespType,
      @FormParam("recvWindow") Long recvWindow,
      @FormParam("timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam(X_MBX_APIKEY) String apiKey,
      @QueryParam(SIGNATURE) ParamsDigest signature)
      throws IOException, BinanceException;

  @POST
  @Path("v1/order/test")
  Object testNewOrder(
      @FormParam("symbol") String symbol,
      @FormParam("side") OrderSide side,
      @FormParam("positionSide") PositionSide positionSide,
      @FormParam("type") FutureOrderType type,
      @FormParam("reduceOnly") Boolean reduceOnly,
      @FormParam("quantity") BigDecimal quantity,
      @FormParam("price") BigDecimal price,
      @FormParam("newClientOrderId") String newClientOrderId,
      @FormParam("stopPrice") BigDecimal stopPrice,
      @FormParam("closePosition") Boolean closePosition,
      @FormParam("activationPrice") Boolean activationPrice,
      @FormParam("callbackRate") Boolean callbackRate,
      @FormParam("timeInForce") TimeInForce timeInForce,
      @FormParam("workingType") WorkingType workingType,
      @FormParam("priceProtect") Boolean priceProtect,
      @FormParam("newOrderRespType") NewOrderRespType newOrderRespType,
      @FormParam("recvWindow") Long recvWindow,
      @FormParam("timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam(X_MBX_APIKEY) String apiKey,
      @QueryParam(SIGNATURE) ParamsDigest signature)
      throws IOException, BinanceException;

}
