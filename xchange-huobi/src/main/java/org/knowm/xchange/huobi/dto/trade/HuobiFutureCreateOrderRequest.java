package org.knowm.xchange.huobi.dto.trade;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class HuobiFutureCreateOrderRequest {

  @JsonProperty("symbol")
  private String symbol;

  @JsonProperty("contract_type")
  private String contract_type;

  @JsonProperty("contract_code")
  private String contract_code;

  @JsonProperty("client-order-id")
  private String client_order_id;

  @JsonProperty("price")
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private BigDecimal price;

  @JsonProperty("volume")
  private BigDecimal volume;

  @JsonProperty("direction")
  private String direction;

  @JsonProperty("offset")
  private String offset;

  @JsonProperty("lever_rate")
  private Integer lever_rate;

  @JsonProperty("order_price_type")
  private String order_price_type;

  @JsonProperty("tp_trigger_price")
  private BigDecimal tp_trigger_price;

  @JsonProperty("tp_order_price")
  private BigDecimal tp_order_price;

  @JsonProperty("tp_order_price_type")
  private String tp_order_price_type;

  @JsonProperty("sl_trigger_price")
  private BigDecimal sl_trigger_price;

  @JsonProperty("sl_order_price")
  private BigDecimal sl_order_price;

  @JsonProperty("sl_order_price_type")
  private String sl_order_price_type;

  public HuobiFutureCreateOrderRequest(
      String symbol,
      String contract_type,
      String contract_code,
      String client_order_id,
      BigDecimal price,
      BigDecimal volume,
      String direction,
      String offset,
      Integer lever_rate,
      String order_price_type,
      BigDecimal tp_trigger_price,
      BigDecimal tp_order_price,
      String tp_order_price_type,
      BigDecimal sl_trigger_price,
      BigDecimal sl_order_price,
      String sl_order_price_type
  ) {
    this.symbol = symbol;
    this.contract_type = contract_type;
    this.contract_code = contract_code;
    this.client_order_id = client_order_id;
    this.price = price;
    this.volume = volume;
    this.direction = direction;
    this.offset = offset;
    this.lever_rate = lever_rate;
    this.order_price_type = order_price_type;
    this.tp_trigger_price = tp_trigger_price;
    this.tp_order_price = tp_order_price;
    this.tp_order_price_type = tp_order_price_type;
    this.sl_trigger_price = sl_trigger_price;
    this.sl_order_price = sl_order_price;
    this.sl_order_price_type = sl_order_price_type;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getContract_type() {
    return contract_type;
  }

  public String getContract_code() {
    return contract_code;
  }

  public String getClient_order_id() {
    return client_order_id;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BigDecimal getVolume() {
    return volume;
  }

  public String getDirection() {
    return direction;
  }

  public String getOffset() {
    return offset;
  }

  public Integer getLever_rate() {
    return lever_rate;
  }

  public String getOrder_price_type() {
    return order_price_type;
  }

  public BigDecimal getTp_trigger_price() {
    return tp_trigger_price;
  }

  public BigDecimal getTp_order_price() {
    return tp_order_price;
  }

  public String getTp_order_price_type() {
    return tp_order_price_type;
  }

  public BigDecimal getSl_trigger_price() {
    return sl_trigger_price;
  }

  public BigDecimal getSl_order_price() {
    return sl_order_price;
  }

  public String getSl_order_price_type() {
    return sl_order_price_type;
  }
}
