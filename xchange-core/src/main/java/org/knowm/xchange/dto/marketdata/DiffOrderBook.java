package org.knowm.xchange.dto.marketdata;

import org.knowm.xchange.dto.trade.LimitOrder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by lin on 2020-11-05.
 */
public class DiffOrderBook extends OrderBook {

  protected boolean isFullUpdate;

  protected List<OrderBookUpdate> asksUpdate;
  protected List<OrderBookUpdate> bidsUpdate;

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
    asksUpdate = new ArrayList<>();
    bidsUpdate = new ArrayList<>();
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
}
