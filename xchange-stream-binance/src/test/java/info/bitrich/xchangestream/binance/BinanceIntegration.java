package info.bitrich.xchangestream.binance;

import static info.bitrich.xchangestream.binance.BinanceStreamingExchange.USE_HIGHER_UPDATE_FREQUENCY;
import static info.bitrich.xchangestream.service.netty.StreamingObjectMapperHelper.getObjectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.bitrich.xchangestream.binance.dto.BinanceWebsocketTransaction;
import info.bitrich.xchangestream.binance.dto.FutureForceOrderBinanceWebsocketTransaction;
import info.bitrich.xchangestream.core.ProductSubscription;
import info.bitrich.xchangestream.core.StreamingExchangeFactory;
import info.bitrich.xchangestream.service.netty.StreamingObjectMapperHelper;
import org.junit.Assert;
import org.junit.Test;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class BinanceIntegration {

  private static final Logger LOG = LoggerFactory.getLogger(BinanceIntegration.class);

  @Test
  public void channelCreateUrlTest() {
    BinanceStreamingExchange exchange =
        (BinanceStreamingExchange)
            StreamingExchangeFactory.INSTANCE.createExchange(BinanceStreamingExchange.class);
    ProductSubscription.ProductSubscriptionBuilder builder = ProductSubscription.create();
    builder.addTicker(CurrencyPair.BTC_USD).addTicker(CurrencyPair.DASH_BTC);
    String buildSubscriptionStreams =
        BinanceStreamingUtil.buildSubscriptionStreams(builder.build());
    Assert.assertEquals("btcusd@ticker/dashbtc@ticker", buildSubscriptionStreams);

    ProductSubscription.ProductSubscriptionBuilder builder2 = ProductSubscription.create();
    builder2
        .addTicker(CurrencyPair.BTC_USD)
        .addTicker(CurrencyPair.DASH_BTC)
        .addOrderbook(CurrencyPair.ETH_BTC);
    String buildSubscriptionStreams2 =
        BinanceStreamingUtil.buildSubscriptionStreams(builder2.build());
    Assert.assertEquals("btcusd@ticker/dashbtc@ticker/ethbtc@depth", buildSubscriptionStreams2);
  }

  @Test
  public void channelCreateUrlWithUpdateFrequencyTest() {
    ProductSubscription.ProductSubscriptionBuilder builder = ProductSubscription.create();
    builder
        .addTicker(CurrencyPair.BTC_USD)
        .addTicker(CurrencyPair.DASH_BTC)
        .addOrderbook(CurrencyPair.ETH_BTC);
    ExchangeSpecification spec =
        StreamingExchangeFactory.INSTANCE
            .createExchange(BinanceStreamingExchange.class)
            .getDefaultExchangeSpecification();
    spec.setExchangeSpecificParametersItem(USE_HIGHER_UPDATE_FREQUENCY, true);
    BinanceStreamingExchange exchange =
        (BinanceStreamingExchange) StreamingExchangeFactory.INSTANCE.createExchange(spec);
    String buildSubscriptionStreams =
        BinanceStreamingUtil.buildSubscriptionStreams(builder.build());
    Assert.assertEquals(
        "btcusd@ticker/dashbtc@ticker/ethbtc@depth@100ms", buildSubscriptionStreams);
  }

  @Test
  public void testBinanceForceOrder() throws IOException {
    String json = "{\"stream\":\"ForceOrder\",\"data\": {\n" +
        "    \"e\":\"forceOrder\",                   \n" +
        "    \"E\":1568014460893,                  \n" +
        "    \"o\":{\n" +
        "        \"s\":\"BTCUSDT\",                   \n" +
        "        \"S\":\"SELL\",                      \n" +
        "        \"o\":\"LIMIT\",                     \n" +
        "        \"f\":\"IOC\",                       \n" +
        "        \"q\":\"0.014\",                     \n" +
        "        \"p\":\"9910\",                      \n" +
        "        \"ap\":\"9910\",                     \n" +
        "        \"X\":\"FILLED\",                    \n" +
        "        \"l\":\"0.014\",                     \n" +
        "        \"z\":\"0.014\",                     \n" +
        "        \"T\":1568014460893                 \n" +
        "\n" +
        "    }\n" +
        "\n" +
        "}\n" +
        "}";

    JavaType FORCE_ORDER_TYPE =
        getObjectMapper()
            .getTypeFactory()
            .constructType(
                new TypeReference<
                                    BinanceWebsocketTransaction<FutureForceOrderBinanceWebsocketTransaction>>() {
                });

    ObjectMapper mapper = StreamingObjectMapperHelper.getObjectMapper();
    JsonNode j = mapper.readTree(json);
    BinanceWebsocketTransaction<FutureForceOrderBinanceWebsocketTransaction> t = mapper.readValue(mapper.treeAsTokens(j), FORCE_ORDER_TYPE);
    LOG.info("{}", t);
  }
}
