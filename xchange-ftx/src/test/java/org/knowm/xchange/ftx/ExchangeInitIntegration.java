package org.knowm.xchange.ftx;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;

public class ExchangeInitIntegration {

  @Test
  public void ftxInitializationTest() {
    ExchangeSpecification spec = new ExchangeSpecification(FtxExchange.class);

    spec.setSslUri("https://ftx.com");
    spec.setHost("ftx.com");
    spec.setPort(80);
    spec.setExchangeName("Ftx");
    spec.setExchangeDescription("Ftx is a spot and derivatives exchange.");

    spec.setProxyHost("192.168.1.100");
    spec.setProxyPort(1081);

    Exchange ftx = ExchangeFactory.INSTANCE.createExchange(spec);

    assertThat(ftx.getExchangeSymbols().isEmpty()).isFalse();
    assertThat(ftx.getExchangeInstruments().isEmpty()).isFalse();
  }
}
