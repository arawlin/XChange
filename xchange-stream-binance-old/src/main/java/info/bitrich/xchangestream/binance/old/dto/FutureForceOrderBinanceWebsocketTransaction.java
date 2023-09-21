package info.bitrich.xchangestream.binance.old.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.binance.dto.trade.*;

import java.math.BigDecimal;

public class FutureForceOrderBinanceWebsocketTransaction extends BaseBinanceWebSocketTransaction {

  private final BinanceForceOrder forceOrder;

  public FutureForceOrderBinanceWebsocketTransaction(
      @JsonProperty("e") String eventType,
      @JsonProperty("E") String eventTime,
      @JsonProperty("o") BinanceForceOrderRaw orderRaw) {
    super(eventType, eventTime);

    forceOrder = new BinanceForceOrder(
        orderRaw.symbol,
        orderRaw.price,
        orderRaw.origQty,
        orderRaw.executedQty,
        orderRaw.avragePrice,
        OrderStatus.getOrderStatus(orderRaw.status),
        TimeInForce.getTimeInForce(orderRaw.timeInForce),
        OrderType.getOrderType(orderRaw.type),
        OrderSide.getOrderSide(orderRaw.side),
        orderRaw.time
    );

    forceOrder.setPair(orderRaw.pair);
    forceOrder.setExecutedQtyTotal(orderRaw.executedQtyTotal);
  }

  public BinanceForceOrder getForceOrder() {
    return forceOrder;
  }

  static class BinanceForceOrderRaw {

    final String symbol;
    final String pair;
    final String side;
    final String type;
    final String timeInForce;
    final BigDecimal origQty;
    final BigDecimal price;
    final BigDecimal avragePrice;
    final String status;
    final BigDecimal executedQty;
    final BigDecimal executedQtyTotal;
    final long time;

    BinanceForceOrderRaw(
        @JsonProperty("s") String symbol,
        @JsonProperty("ps") String pair,
        @JsonProperty("S") String side,
        @JsonProperty("o") String type,
        @JsonProperty("f") String timeInForce,
        @JsonProperty("q") BigDecimal origQty,
        @JsonProperty("p") BigDecimal price,
        @JsonProperty("ap") BigDecimal avragePrice,
        @JsonProperty("X") String status,
        @JsonProperty("l") BigDecimal executedQty,
        @JsonProperty("z") BigDecimal executedQtyTotal,
        @JsonProperty("T") long time
    ) {
      this.symbol = symbol;
      this.pair = pair;
      this.side = side;
      this.type = type;
      this.timeInForce = timeInForce;
      this.origQty = origQty;
      this.price = price;
      this.avragePrice = avragePrice;
      this.status = status;
      this.executedQty = executedQty;
      this.executedQtyTotal = executedQtyTotal;
      this.time = time;
    }
  }
}
