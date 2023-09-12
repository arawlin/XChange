package org.knowm.xchange.huobi;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.knowm.xchange.huobi.dto.HuobiStringResult;
import org.knowm.xchange.huobi.dto.trade.HuobiFutureCreateOrderRequest;
import si.mazi.rescu.ParamsDigest;

import java.io.IOException;

/**
 * Created by lin on 2021-01-29.
 */
@Path("/swap-api")
@Produces(MediaType.APPLICATION_JSON)
public interface HuobiSwap extends HuobiFutureBase {

  @POST
  @Path("/v1/swap_order")
  @Consumes(MediaType.APPLICATION_JSON)
  HuobiStringResult placeOrder(
      HuobiFutureCreateOrderRequest body,
      @QueryParam("SignatureMethod") String signatureMethod,
      @QueryParam("SignatureVersion") int signatureVersion,
      @QueryParam("Timestamp") String nonce,
      @QueryParam("AccessKeyId") String apiKey,
      @QueryParam("Signature") ParamsDigest signature)
      throws IOException;

}
