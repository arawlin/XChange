package org.knowm.xchange.huobi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.huobi.HuobiContract;
import org.knowm.xchange.huobi.HuobiSwap;
import org.knowm.xchange.huobi.HuobiSwapLinear;
import org.knowm.xchange.huobi.HuobiUtils;
import org.knowm.xchange.huobi.dto.HuobiStringResult;
import org.knowm.xchange.huobi.dto.trade.HuobiFutureCreateOrderRequest;
import si.mazi.rescu.ParamsDigest;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * Created by lin on 2021-01-29.
 */
public class HuobiFutureTradeServiceRaw extends HuobiFutureBaseService {

  public HuobiFutureTradeServiceRaw(Exchange exchange) {
    super(exchange);
  }

  public String placeOrder(
      HuobiFutureCreateOrderRequest req,
      String apiKeyAnother,
      ParamsDigest signatureAnother
  ) throws IOException {
    HuobiStringResult res = null;

    switch (this.futureType) {
      case CONTRACT:
        res = ((HuobiContract) api).placeOrder(
            req,
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            Optional.ofNullable(apiKeyAnother).orElse(this.apiKeyCreator),
            Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)
        );
        break;
      case SWAP:
        res = ((HuobiSwap) api).placeOrder(
            req,
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            Optional.ofNullable(apiKeyAnother).orElse(this.apiKeyCreator),
            Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)
        );
        break;
      case SWAP_LINEAR:
        res = ((HuobiSwapLinear) api).placeOrder(
            req,
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            Optional.ofNullable(apiKeyAnother).orElse(this.apiKeyCreator),
            Optional.ofNullable(signatureAnother).orElse(this.signatureCreator)
        );
        break;
    }

    if (!res.isSuccess()) {
      return null;
    }
    if (StringUtils.isEmpty(res.getResult())) {
      return null;
    }

    Map<String, Object> r = new ObjectMapper().readValue(res.getResult(), Map.class);
    return (String) r.get("order_id_str");
  }
}
