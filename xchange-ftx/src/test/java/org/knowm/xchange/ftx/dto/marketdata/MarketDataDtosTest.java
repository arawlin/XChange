package org.knowm.xchange.ftx.dto.marketdata;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.ftx.FtxExchange;
import org.knowm.xchange.ftx.dto.FtxResponse;
import org.knowm.xchange.ftx.dto.trade.TradeDtosTest;
import org.knowm.xchange.ftx.service.FtxMarketDataServiceRaw;
import org.knowm.xchange.utils.jackson.CurrencyPairDeserializer;

public class MarketDataDtosTest {

  @Test
  public void marketsDtoUnmarshall() throws IOException {
    // Read in the JSON from the example resources
    InputStream is = TradeDtosTest.class.getResourceAsStream("/responses/example-ftxMarkets.json");

    // Use Jackson to parse it
    ObjectMapper mapper = new ObjectMapper();
    FtxResponse<FtxMarketsDto> ftxResponse =
        mapper.readValue(is, new TypeReference<FtxResponse<FtxMarketsDto>>() {});

    // Verify that the example data was unmarshalled correctly
    assertThat(ftxResponse.getResult().getMarketList().size()).isEqualTo(6);

    assertThat(ftxResponse.getResult().getMarketList().get(0).getAsk())
        .isEqualTo(BigDecimal.valueOf(128.09));
    assertThat(ftxResponse.getResult().getMarketList().get(0).getBaseCurrency()).isNull();
    assertThat(ftxResponse.getResult().getMarketList().get(0).getType()).isEqualTo("future");
    assertThat(ftxResponse.getResult().getMarketList().get(0).getName()).isEqualTo("AAPL-1225");
  }

  @Test
  public void tradesDtoUnmarshall() throws IOException {
    // Read in the JSON from the example resources
    InputStream is = TradeDtosTest.class.getResourceAsStream("/responses/example-ftxTrades.json");

    // Use Jackson to parse it
    ObjectMapper mapper = new ObjectMapper();
    FtxResponse<List<FtxTradeDto>> ftxResponse =
        mapper.readValue(is, new TypeReference<FtxResponse<List<FtxTradeDto>>>() {});

    // Verify that the example data was unmarshalled correctly
    assertThat(ftxResponse.getResult().size()).isEqualTo(1);
    assertThat(ftxResponse.getResult().get(0).getTime()).isBefore(Date.from(Instant.now()));
    assertThat(ftxResponse.getResult().get(0).isLiquidation()).isFalse();
  }

  @Test
  public void currencyDeserializerTest() throws IOException {
    // Read in the JSON from the example resources
    InputStream is = TradeDtosTest.class.getResourceAsStream("/responses/example-ftxMarkets.json");

    // Use Jackson to parse it
    ObjectMapper mapper = new ObjectMapper();
    FtxResponse<FtxMarketsDto> ftxResponse =
        mapper.readValue(is, new TypeReference<FtxResponse<FtxMarketsDto>>() {});

    // Deserialize CurrencyPair

    ftxResponse
        .getResult()
        .getMarketList()
        .forEach(
            ftxMarketDto -> {
              System.out.println(
                  CurrencyPairDeserializer.getCurrencyPairFromString(ftxMarketDto.getName())
                      .toString());
            });
  }

  private FtxMarketDataServiceRaw serivce;

  @Before
  public void init() {
    ExchangeSpecification spec = new ExchangeSpecification(FtxExchange.class);

    spec.setSslUri("https://ftx.com");
    spec.setHost("ftx.com");
    spec.setPort(80);
    spec.setExchangeName("Ftx");
    spec.setExchangeDescription("Ftx is a spot and derivatives exchange.");

    spec.setProxyHost("192.168.1.100");
    spec.setProxyPort(1081);

    spec.setShouldLoadRemoteMetaData(false);

    Exchange ftx = ExchangeFactory.INSTANCE.createExchange(spec);
    serivce = (FtxMarketDataServiceRaw) ftx.getMarketDataService();
  }

  @Test
  public void testListAllFutures() throws IOException {
    FtxResponse<FtxFutureInfos> resp = serivce.listAllFutures();
    Assert.assertNotNull(resp);
  }

  @Test
  public void testGetFuture() throws IOException {
    FtxResponse<FtxFutureInfo> resp = serivce.getFuture("BTC-PERP");
    Assert.assertNotNull(resp);
  }

  @Test
  public void testGetFutureStats() throws IOException {
    FtxResponse<FtxFutureStats> resp = serivce.getFutureStats("BTC-PERP");
    Assert.assertNotNull(resp);
  }

  @Test
  public void testGetFutureFundingRates() throws IOException {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    Long endTime = c.getTimeInMillis() / 1000L;

    c.add(Calendar.HOUR, -10);
    Long startTime = c.getTimeInMillis() / 1000L;

    FtxResponse<FtxFutureFundingRates> resp = serivce.getFutureFundingRates(startTime, endTime, "BTC-PERP");
    Assert.assertNotNull(resp);
  }

}
