package org.knowm.xchange.gateiov4.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/** Created by lin on 2020-10-15. */
public class GateioContractInfo {

  private String name;
  private String type;

  private BigDecimal quanto_multiplier;
  private BigDecimal leverage_min;
  private BigDecimal leverage_max;
  private BigDecimal maintenance_rate;

  private String mark_type;
  private BigDecimal mark_price;
  private BigDecimal index_price;
  private BigDecimal last_price;
  private BigDecimal maker_fee_rate;
  private BigDecimal taker_fee_rate;
  private BigDecimal order_price_round;
  private BigDecimal mark_price_round;

  private BigDecimal funding_rate;
  private long funding_interval;
  private long funding_next_apply;

  private BigDecimal risk_limit_base;
  private BigDecimal risk_limit_step;
  private BigDecimal risk_limit_max;
  private BigDecimal order_size_min;
  private BigDecimal order_size_max;
  private BigDecimal order_price_deviate;

  private BigDecimal ref_discount_rate;
  private BigDecimal ref_rebate_rate;

  private long orderbook_id;
  private long trade_id;
  private long trade_size;
  private long position_size;
  private long config_change_time;
  private int orders_limit;

  public GateioContractInfo(
      @JsonProperty("name") String name,
      @JsonProperty("type") String type,
      @JsonProperty("quanto_multiplier") BigDecimal quanto_multiplier,
      @JsonProperty("leverage_min") BigDecimal leverage_min,
      @JsonProperty("leverage_max") BigDecimal leverage_max,
      @JsonProperty("maintenance_rate") BigDecimal maintenance_rate,
      @JsonProperty("mark_type") String mark_type,
      @JsonProperty("mark_price") BigDecimal mark_price,
      @JsonProperty("index_price") BigDecimal index_price,
      @JsonProperty("last_price") BigDecimal last_price,
      @JsonProperty("maker_fee_rate") BigDecimal maker_fee_rate,
      @JsonProperty("taker_fee_rate") BigDecimal taker_fee_rate,
      @JsonProperty("order_price_round") BigDecimal order_price_round,
      @JsonProperty("mark_price_round") BigDecimal mark_price_round,
      @JsonProperty("funding_rate") BigDecimal funding_rate,
      @JsonProperty("funding_interval") long funding_interval,
      @JsonProperty("funding_next_apply") long funding_next_apply,
      @JsonProperty("risk_limit_base") BigDecimal risk_limit_base,
      @JsonProperty("risk_limit_step") BigDecimal risk_limit_step,
      @JsonProperty("risk_limit_max") BigDecimal risk_limit_max,
      @JsonProperty("order_size_min") BigDecimal order_size_min,
      @JsonProperty("order_size_max") BigDecimal order_size_max,
      @JsonProperty("order_price_deviate") BigDecimal order_price_deviate,
      @JsonProperty("ref_discount_rate") BigDecimal ref_discount_rate,
      @JsonProperty("ref_rebate_rate") BigDecimal ref_rebate_rate,
      @JsonProperty("orderbook_id") long orderbook_id,
      @JsonProperty("trade_id") long trade_id,
      @JsonProperty("trade_size") long trade_size,
      @JsonProperty("position_size") long position_size,
      @JsonProperty("config_change_time") long config_change_time,
      @JsonProperty("orders_limit") int orders_limit) {
    this.name = name;
    this.type = type;
    this.quanto_multiplier = quanto_multiplier;
    this.leverage_min = leverage_min;
    this.leverage_max = leverage_max;
    this.maintenance_rate = maintenance_rate;
    this.mark_type = mark_type;
    this.mark_price = mark_price;
    this.index_price = index_price;
    this.last_price = last_price;
    this.maker_fee_rate = maker_fee_rate;
    this.taker_fee_rate = taker_fee_rate;
    this.order_price_round = order_price_round;
    this.mark_price_round = mark_price_round;
    this.funding_rate = funding_rate;
    this.funding_interval = funding_interval;
    this.funding_next_apply = funding_next_apply;
    this.risk_limit_base = risk_limit_base;
    this.risk_limit_step = risk_limit_step;
    this.risk_limit_max = risk_limit_max;
    this.order_size_min = order_size_min;
    this.order_size_max = order_size_max;
    this.order_price_deviate = order_price_deviate;
    this.ref_discount_rate = ref_discount_rate;
    this.ref_rebate_rate = ref_rebate_rate;
    this.orderbook_id = orderbook_id;
    this.trade_id = trade_id;
    this.trade_size = trade_size;
    this.position_size = position_size;
    this.config_change_time = config_change_time;
    this.orders_limit = orders_limit;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public BigDecimal getQuanto_multiplier() {
    return quanto_multiplier;
  }

  public void setQuanto_multiplier(BigDecimal quanto_multiplier) {
    this.quanto_multiplier = quanto_multiplier;
  }

  public BigDecimal getLeverage_min() {
    return leverage_min;
  }

  public void setLeverage_min(BigDecimal leverage_min) {
    this.leverage_min = leverage_min;
  }

  public BigDecimal getLeverage_max() {
    return leverage_max;
  }

  public void setLeverage_max(BigDecimal leverage_max) {
    this.leverage_max = leverage_max;
  }

  public BigDecimal getMaintenance_rate() {
    return maintenance_rate;
  }

  public void setMaintenance_rate(BigDecimal maintenance_rate) {
    this.maintenance_rate = maintenance_rate;
  }

  public String getMark_type() {
    return mark_type;
  }

  public void setMark_type(String mark_type) {
    this.mark_type = mark_type;
  }

  public BigDecimal getMark_price() {
    return mark_price;
  }

  public void setMark_price(BigDecimal mark_price) {
    this.mark_price = mark_price;
  }

  public BigDecimal getIndex_price() {
    return index_price;
  }

  public void setIndex_price(BigDecimal index_price) {
    this.index_price = index_price;
  }

  public BigDecimal getLast_price() {
    return last_price;
  }

  public void setLast_price(BigDecimal last_price) {
    this.last_price = last_price;
  }

  public BigDecimal getMaker_fee_rate() {
    return maker_fee_rate;
  }

  public void setMaker_fee_rate(BigDecimal maker_fee_rate) {
    this.maker_fee_rate = maker_fee_rate;
  }

  public BigDecimal getTaker_fee_rate() {
    return taker_fee_rate;
  }

  public void setTaker_fee_rate(BigDecimal taker_fee_rate) {
    this.taker_fee_rate = taker_fee_rate;
  }

  public BigDecimal getOrder_price_round() {
    return order_price_round;
  }

  public void setOrder_price_round(BigDecimal order_price_round) {
    this.order_price_round = order_price_round;
  }

  public BigDecimal getMark_price_round() {
    return mark_price_round;
  }

  public void setMark_price_round(BigDecimal mark_price_round) {
    this.mark_price_round = mark_price_round;
  }

  public BigDecimal getFunding_rate() {
    return funding_rate;
  }

  public void setFunding_rate(BigDecimal funding_rate) {
    this.funding_rate = funding_rate;
  }

  public long getFunding_interval() {
    return funding_interval;
  }

  public void setFunding_interval(long funding_interval) {
    this.funding_interval = funding_interval;
  }

  public long getFunding_next_apply() {
    return funding_next_apply;
  }

  public void setFunding_next_apply(long funding_next_apply) {
    this.funding_next_apply = funding_next_apply;
  }

  public BigDecimal getRisk_limit_base() {
    return risk_limit_base;
  }

  public void setRisk_limit_base(BigDecimal risk_limit_base) {
    this.risk_limit_base = risk_limit_base;
  }

  public BigDecimal getRisk_limit_step() {
    return risk_limit_step;
  }

  public void setRisk_limit_step(BigDecimal risk_limit_step) {
    this.risk_limit_step = risk_limit_step;
  }

  public BigDecimal getRisk_limit_max() {
    return risk_limit_max;
  }

  public void setRisk_limit_max(BigDecimal risk_limit_max) {
    this.risk_limit_max = risk_limit_max;
  }

  public BigDecimal getOrder_size_min() {
    return order_size_min;
  }

  public void setOrder_size_min(BigDecimal order_size_min) {
    this.order_size_min = order_size_min;
  }

  public BigDecimal getOrder_size_max() {
    return order_size_max;
  }

  public void setOrder_size_max(BigDecimal order_size_max) {
    this.order_size_max = order_size_max;
  }

  public BigDecimal getOrder_price_deviate() {
    return order_price_deviate;
  }

  public void setOrder_price_deviate(BigDecimal order_price_deviate) {
    this.order_price_deviate = order_price_deviate;
  }

  public BigDecimal getRef_discount_rate() {
    return ref_discount_rate;
  }

  public void setRef_discount_rate(BigDecimal ref_discount_rate) {
    this.ref_discount_rate = ref_discount_rate;
  }

  public BigDecimal getRef_rebate_rate() {
    return ref_rebate_rate;
  }

  public void setRef_rebate_rate(BigDecimal ref_rebate_rate) {
    this.ref_rebate_rate = ref_rebate_rate;
  }

  public long getOrderbook_id() {
    return orderbook_id;
  }

  public void setOrderbook_id(long orderbook_id) {
    this.orderbook_id = orderbook_id;
  }

  public long getTrade_id() {
    return trade_id;
  }

  public void setTrade_id(long trade_id) {
    this.trade_id = trade_id;
  }

  public long getTrade_size() {
    return trade_size;
  }

  public void setTrade_size(long trade_size) {
    this.trade_size = trade_size;
  }

  public long getPosition_size() {
    return position_size;
  }

  public void setPosition_size(long position_size) {
    this.position_size = position_size;
  }

  public long getConfig_change_time() {
    return config_change_time;
  }

  public void setConfig_change_time(long config_change_time) {
    this.config_change_time = config_change_time;
  }

  public int getOrders_limit() {
    return orders_limit;
  }

  public void setOrders_limit(int orders_limit) {
    this.orders_limit = orders_limit;
  }
}
