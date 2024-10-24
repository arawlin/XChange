package info.bitrich.xchangestream.binance.old;

import com.fasterxml.jackson.databind.JsonNode;
import info.bitrich.xchangestream.core.ProductSubscription;
import info.bitrich.xchangestream.service.netty.JsonNettyStreamingService;
import info.bitrich.xchangestream.service.netty.WebSocketClientCompressionAllowClientNoContextAndServerNoContextHandler;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketClientExtensionHandler;
import java.io.IOException;
import java.time.Duration;

public class BinanceStreamingService extends JsonNettyStreamingService {

  private final ProductSubscription productSubscription;

  public BinanceStreamingService(String baseUri, ProductSubscription productSubscription) {
    super(baseUri, Integer.MAX_VALUE);
    this.productSubscription = productSubscription;
  }

  public BinanceStreamingService(
      String baseUri,
      ProductSubscription productSubscription,
      int maxFramePayloadLength,
      Duration connectionTimeout,
      Duration retryDuration,
      int idleTimeoutSeconds) {
    super(baseUri, maxFramePayloadLength, connectionTimeout, retryDuration, idleTimeoutSeconds);
    this.productSubscription = productSubscription;
  }

  @Override
  protected String getChannelNameFromMessage(JsonNode message) throws IOException {
    return message.get("stream").asText();
  }

  @Override
  public String getSubscribeMessage(String channelName, Object... args) throws IOException {
    // No op. Disconnecting from the web socket will cancel subscriptions.
    return null;
  }

  @Override
  public String getUnsubscribeMessage(String channelName, Object... args) throws IOException {
    // No op. Disconnecting from the web socket will cancel subscriptions.
    return null;
  }

  @Override
  public void sendMessage(String message) {
    // Subscriptions are made upon connection - no messages are sent.
  }

  @Override
  protected WebSocketClientExtensionHandler getWebSocketClientExtensionHandler() {
    return WebSocketClientCompressionAllowClientNoContextAndServerNoContextHandler.INSTANCE;
  }

  /**
   * The available subscriptions for this streaming service.
   *
   * @return The subscriptions for the currently open connection.
   */
  public ProductSubscription getProductSubscription() {
    return productSubscription;
  }
}
