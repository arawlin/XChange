package org.knowm.xchange.huobi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.huobi.HuobiExchangeSpecification;
import org.knowm.xchange.huobi.HuobiFutureExchange;
import org.knowm.xchange.huobi.dto.FutureType;
import org.knowm.xchange.huobi.dto.trade.HuobiFutureCreateOrderRequest;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by lin on 2021-01-30.
 */
public class HuobiFutureTradeServiceRawTest {

  private HuobiFutureExchange exchange;
  private HuobiFutureTradeService tradeService;

  private String apiKey;
  private String secretKey;

  @Before
  public void setup() {
    apiKey = System.getProperty("apiKey");
    secretKey = System.getProperty("secretKey");

    HuobiExchangeSpecification spec = new HuobiExchangeSpecification(HuobiFutureExchange.class);
    spec.setSslUri("https://api.hbdm.com");
    spec.setHost("api.hbdm.com");
    spec.setPort(80);
    spec.setExchangeName("Huobi Future");

    spec.setProxyHost("192.168.1.100");
    spec.setProxyPort(1081);

    spec.setFutureType(FutureType.CONTRACT);

    spec.setApiKey(apiKey);
    spec.setSecretKey(secretKey);

    exchange = (HuobiFutureExchange) ExchangeFactory.INSTANCE.createExchange(spec);

    tradeService = (HuobiFutureTradeService) exchange.getTradeService();

  }

  @Test
  public void placeOrder() throws Exception {
    HuobiFutureCreateOrderRequest req = new HuobiFutureCreateOrderRequest(
        null,
        null,
        "BTC210326",
        null,
        new BigDecimal("20000"),
        new BigDecimal("1"),
        "buy",
        "open",
        1,
        "limit",
        null,
        null,
        null,
        null,
        null,
        null
    );
    String orderId = tradeService.placeOrder(req, null, null);
    Assume.assumeNotNull(orderId);
  }
}