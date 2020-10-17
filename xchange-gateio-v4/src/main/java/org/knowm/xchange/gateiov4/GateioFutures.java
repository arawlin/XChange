package org.knowm.xchange.gateiov4;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.knowm.xchange.gateiov4.dto.marketdata.GateioContractInfo;
import org.knowm.xchange.gateiov4.dto.marketdata.GateioFundingRate;

/** Created by lin on 2020-10-14. */
@Path("futures")
@Produces(MediaType.APPLICATION_JSON)
public interface GateioFutures {

  @GET
  @Path("{settle}/contracts/{contract}")
  GateioContractInfo getContractsInfoOne(
      @PathParam("settle") String settle, @PathParam("contract") String contract);

  @GET
  @Path("{settle}/funding_rate")
  List<GateioFundingRate> getContractFundingRate(
      @PathParam("settle") String settle,
      @QueryParam("contract") String contract,
      @QueryParam("limit") Integer limit);
}
