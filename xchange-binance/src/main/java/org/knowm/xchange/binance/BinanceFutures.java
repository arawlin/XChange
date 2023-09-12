package org.knowm.xchange.binance;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.knowm.xchange.binance.dto.BinanceException;
import org.knowm.xchange.binance.dto.BinanceResponse;
import org.knowm.xchange.binance.dto.marketdata.BinanceAggTrades;
import org.knowm.xchange.binance.dto.marketdata.BinanceFuturesFundingRate;
import org.knowm.xchange.binance.dto.marketdata.BinanceOrderbook;
import org.knowm.xchange.binance.dto.meta.BinanceTime;
import org.knowm.xchange.binance.dto.meta.exchangeinfo.BinanceExchangeInfo;
import org.knowm.xchange.binance.dto.trade.*;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public interface BinanceFutures {

  String SIGNATURE = "signature";
  String X_MBX_APIKEY = "X-MBX-APIKEY";

  String HOST_TEST = "testnet.binancefuture.com";
  String URL_TEST = "https://" + HOST_TEST;

  @GET
  @Path("v1/exchangeInfo")
  BinanceExchangeInfo exchangeInfo() throws IOException;

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


  @GET
  @Path("v1/positionSide/dual")
  BinancePositionSide getPositionSide(
      @QueryParam("recvWindow") Long recvWindow,
      @QueryParam("timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam(X_MBX_APIKEY) String apiKey,
      @QueryParam(SIGNATURE) ParamsDigest signature)
      throws IOException, BinanceException;

  @POST
  @Path("v1/positionSide/dual")
  BinanceResponse setPositionSide(
      @FormParam("dualSidePosition") Boolean dualSidePosition,
      @FormParam("recvWindow") Long recvWindow,
      @FormParam("timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam(X_MBX_APIKEY) String apiKey,
      @QueryParam(SIGNATURE) ParamsDigest signature)
      throws IOException, BinanceException;

  @POST
  @Path("v1/leverage")
  BinanceLeverageStatus setLeverage(
      @FormParam("symbol") String symbol,
      @FormParam("leverage") Integer leverage,
      @FormParam("recvWindow") Long recvWindow,
      @FormParam("timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam(X_MBX_APIKEY) String apiKey,
      @QueryParam(SIGNATURE) ParamsDigest signature)
      throws IOException, BinanceException;

  @POST
  @Path("v1/marginType")
  BinanceResponse setMarginType(
      @FormParam("symbol") String symbol,
      @FormParam("marginType") MarginType marginType,
      @FormParam("recvWindow") Long recvWindow,
      @FormParam("timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam(X_MBX_APIKEY) String apiKey,
      @QueryParam(SIGNATURE) ParamsDigest signature)
      throws IOException, BinanceException;

  @POST
  @Path("v1/positionMargin")
  BinanceResponsePositionMargin setPositionMargin(
      @FormParam("symbol") String symbol,
      @FormParam("positionSide") PositionSide positionSide,
      @FormParam("amount") BigDecimal amount,
      @FormParam("type") Integer type,
      @FormParam("recvWindow") Long recvWindow,
      @FormParam("timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam(X_MBX_APIKEY) String apiKey,
      @QueryParam(SIGNATURE) ParamsDigest signature)
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

  @GET
  @Path("v1/openOrders")
  List<BinanceFutureOrder> openOrders(
      @QueryParam("symbol") String symbol,
      @QueryParam("pair") String pair,
      @QueryParam("recvWindow") Long recvWindow,
      @QueryParam("timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam(X_MBX_APIKEY) String apiKey,
      @QueryParam(SIGNATURE) ParamsDigest signature)
      throws IOException, BinanceException;

  @DELETE
  @Path("v1/order")
  BinanceFutureOrder cancelOrder(
      @QueryParam("symbol") String symbol,
      @QueryParam("orderId") long orderId,
      @QueryParam("origClientOrderId") String origClientOrderId,
      @QueryParam("recvWindow") Long recvWindow,
      @QueryParam("timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam(X_MBX_APIKEY) String apiKey,
      @QueryParam(SIGNATURE) ParamsDigest signature)
      throws IOException, BinanceException;

  @GET
  @Path("v1/order")
  BinanceFutureOrder orderStatus(
      @QueryParam("symbol") String symbol,
      @QueryParam("orderId") long orderId,
      @QueryParam("origClientOrderId") String origClientOrderId,
      @QueryParam("recvWindow") Long recvWindow,
      @QueryParam("timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam(X_MBX_APIKEY) String apiKey,
      @QueryParam(SIGNATURE) ParamsDigest signature)
      throws IOException, BinanceException;

  @DELETE
  @Path("v1/allOpenOrders")
  Map<String, Object> cancelAllOpenOrders(
      @QueryParam("symbol") String symbol,
      @QueryParam("recvWindow") Long recvWindow,
      @QueryParam("timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam(X_MBX_APIKEY) String apiKey,
      @QueryParam(SIGNATURE) ParamsDigest signature)
      throws IOException, BinanceException;

  @GET
  @Path("v1/allOrders")
  List<BinanceFutureOrder> allOrders(
      @QueryParam("symbol") String symbol,
      @QueryParam("pair") String pair,
      @QueryParam("orderId") Long orderId,
      @QueryParam("startTime") Long startTime,
      @QueryParam("endTime") Long endTime,
      @QueryParam("limit") Integer limit,
      @QueryParam("recvWindow") Long recvWindow,
      @QueryParam("timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam(X_MBX_APIKEY) String apiKey,
      @QueryParam(SIGNATURE) ParamsDigest signature)
      throws IOException, BinanceException;

  @GET
  @Path("v1/userTrades")
  List<BinanceFutureTrade> userTrades(
      @QueryParam("symbol") String symbol,
      @QueryParam("pair") String pair,
      @QueryParam("startTime") Long startTime,
      @QueryParam("endTime") Long endTime,
      @QueryParam("fromId") Long fromId,
      @QueryParam("limit") Integer limit,
      @QueryParam("recvWindow") Long recvWindow,
      @QueryParam("timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam(X_MBX_APIKEY) String apiKey,
      @QueryParam(SIGNATURE) ParamsDigest signature)
      throws IOException, BinanceException;

  @GET
  @Path("v1/income")
  List<BinanceIncomeRecord> income(
      @QueryParam("symbol") String symbol,
      @QueryParam("incomeType") FutureIncomeType incomeType,
      @QueryParam("startTime") Long startTime,
      @QueryParam("endTime") Long endTime,
      @QueryParam("limit") Integer limit,
      @QueryParam("recvWindow") Long recvWindow,
      @QueryParam("timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam(X_MBX_APIKEY) String apiKey,
      @QueryParam(SIGNATURE) ParamsDigest signature)
      throws IOException, BinanceException;

}
