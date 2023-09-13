package info.bitrich.xchangestream.binance;

import info.bitrich.xchangestream.core.ProductSubscription;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.instrument.Instrument;

/** Created by lin on 2020-11-04. */
public class BinanceStreamingUtil {

  public static String buildSubscriptionStreams(ProductSubscription subscription, String... args) {
    return Stream.of(
            buildSubscriptionStrings(subscription.getTicker(), "ticker", args[0]),
            buildSubscriptionStrings(subscription.getOrderBook(), "depth", args[1]),
            buildSubscriptionStrings(subscription.getTrades(), "trade", args[2]),
            buildSubscriptionStrings(subscription.getForceOrders(), "forceOrder", args[3]),
            buildSubscriptionStrings(subscription.getAggTrades(), "aggTrade", args[4])
        )
        .filter(s -> !s.isEmpty())
        .collect(Collectors.joining("/"));
  }

  private static String buildSubscriptionStrings(
          List<Instrument> currencyPairs, String subscriptionType, String arg) {
    return subscriptionStrings(currencyPairs)
        .map(s -> s + "@" + subscriptionType + arg)
        .collect(Collectors.joining("/"));
  }

  private static Stream<String> subscriptionStrings(List<Instrument> currencyPairs) {
    return currencyPairs.stream()
        .map(pair -> String.join("", pair.toString().split("/")).toLowerCase());
  }
}
