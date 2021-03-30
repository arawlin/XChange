package org.knowm.xchange.binance.dto.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.knowm.xchange.binance.dto.trade.PositionSide;

/**
 * Created by lin on 2021-03-29.
 */
public enum AssetTransferType {

  MAIN_C2C,// 现货钱包转向C2C钱包
  MAIN_UMFUTURE,// 现货钱包转向U本位合约钱包
  MAIN_CMFUTURE,// 现货钱包转向币本位合约钱包
  MAIN_MARGIN,// 现货钱包转向杠杆全仓钱包
  MAIN_MINING,// 现货钱包转向矿池钱包
  C2C_MAIN,// C2C钱包转向现货钱包
  C2C_UMFUTURE,// C2C钱包转向U本位合约钱包
  C2C_MINING,// C2C钱包转向矿池钱包
  UMFUTURE_MAIN,// U本位合约钱包转向现货钱包
  UMFUTURE_C2C,// U本位合约钱包转向C2C钱包
  UMFUTURE_MARGIN,// U本位合约钱包转向杠杆全仓钱包
  CMFUTURE_MAIN,// 币本位合约钱包转向现货钱包
  MARGIN_MAIN,// 杠杆全仓钱包转向现货钱包
  MARGIN_UMFUTURE,// 杠杆全仓钱包转向U本位合约钱包
  MINING_MAIN,// 矿池钱包转向现货钱包
  MINING_UMFUTURE,// 矿池钱包转向U本位合约钱包
  MINING_C2C,// 矿池钱包转向C2C钱包
  MARGIN_CMFUTURE,// 杠杆全仓钱包转向币本位合约钱包
  CMFUTURE_MARGIN,// 币本位合约钱包转向杠杆全仓钱包
  MARGIN_C2C,// 杠杆全仓钱包转向C2C钱包
  C2C_MARGIN,// C2C钱包转向杠杆全仓钱包
  MARGIN_MINING,// 杠杆全仓钱包转向矿池钱包
  MINING_MARGIN;// 矿池钱包转向杠杆全仓钱包

  @JsonCreator
  public static AssetTransferType getAssetTransferType(String s) {
    try {
      return AssetTransferType.valueOf(s);
    } catch (Exception e) {
      throw new RuntimeException("Unknown AssetTransferType " + s + ".");
    }
  }

}
