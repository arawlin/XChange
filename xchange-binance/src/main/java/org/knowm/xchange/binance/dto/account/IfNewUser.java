package org.knowm.xchange.binance.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IfNewUser {

  private final String apiAgentCode;
  private final Boolean rebateWorking;
  private final Boolean ifNewUser;
  private final Long referrerId;

  public IfNewUser(
      @JsonProperty("apiAgentCode") String apiAgentCode,
      @JsonProperty("rebateWorking") Boolean rebateWorking,
      @JsonProperty("ifNewUser") Boolean ifNewUser,
      @JsonProperty("referrerId") Long referrerId
  ) {
    this.apiAgentCode = apiAgentCode;
    this.rebateWorking = rebateWorking;
    this.ifNewUser = ifNewUser;
    this.referrerId = referrerId;
  }

  public String getApiAgentCode() {
    return apiAgentCode;
  }

  public Boolean getRebateWorking() {
    return rebateWorking;
  }

  public Boolean getIfNewUser() {
    return ifNewUser;
  }

  public Long getReferrerId() {
    return referrerId;
  }
}
