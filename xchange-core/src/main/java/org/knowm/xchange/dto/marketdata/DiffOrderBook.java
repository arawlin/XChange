package org.knowm.xchange.dto.marketdata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;
import org.knowm.xchange.dto.trade.LimitOrder;

/**
 * Created by lin on 2020-11-05.
 */
public class DiffOrderBook extends OrderBook {

  protected boolean isFullUpdate;
  protected long lastUpdateId;
  protected long firstUpdateId;
  protected long lastLastUpdateId;

  protected List<OrderBookUpdate> asksUpdate = new ArrayList<>();
  protected List<OrderBookUpdate> bidsUpdate = new ArrayList<>();

  public DiffOrderBook(Date timeStamp, List<LimitOrder> asks, List<LimitOrder> bids) {
    super(timeStamp, asks, bids);
    isFullUpdate = true;
  }

  public DiffOrderBook(Date timeStamp, List<LimitOrder> asks, List<LimitOrder> bids, boolean sort) {
    super(timeStamp, asks, bids, sort);
    isFullUpdate = true;
  }

  public DiffOrderBook(Date timeStamp, Stream<LimitOrder> asks, Stream<LimitOrder> bids) {
    super(timeStamp, asks, bids);
    isFullUpdate = true;
  }

  public DiffOrderBook(Date timeStamp, Stream<LimitOrder> asks, Stream<LimitOrder> bids, boolean sort) {
    super(timeStamp, asks, bids, sort);
    isFullUpdate = true;
  }

  public void preupdate() {
    asksUpdate.clear();
    bidsUpdate.clear();
  }

  @Override
  public void update(OrderBookUpdate orderBookUpdate) {
    switch (orderBookUpdate.getLimitOrder().getType()) {
      case ASK:
        asksUpdate.add(orderBookUpdate);
        break;
      case BID:
        bidsUpdate.add(orderBookUpdate);
        break;
    }

    super.update(orderBookUpdate);
  }

  public boolean isFullUpdate() {
    return isFullUpdate;
  }

  public void setFullUpdate(boolean fullUpdate) {
    isFullUpdate = fullUpdate;
  }

  public List<OrderBookUpdate> getAsksUpdate() {
    return asksUpdate;
  }

  public List<OrderBookUpdate> getBidsUpdate() {
    return bidsUpdate;
  }

  public long getLastUpdateId() {
    return lastUpdateId;
  }

  public void setLastUpdateId(long lastUpdateId) {
    this.lastUpdateId = lastUpdateId;
  }

  public long getFirstUpdateId() {
    return firstUpdateId;
  }

  public void setFirstUpdateId(long firstUpdateId) {
    this.firstUpdateId = firstUpdateId;
  }

  public long getLastLastUpdateId() {
    return lastLastUpdateId;
  }

  public void setLastLastUpdateId(long lastLastUpdateId) {
    this.lastLastUpdateId = lastLastUpdateId;
  }
}
