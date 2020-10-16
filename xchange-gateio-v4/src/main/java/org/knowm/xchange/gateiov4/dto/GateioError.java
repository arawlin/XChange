package org.knowm.xchange.gateiov4.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Created by lin on 2020-10-15. */
public class GateioError {

  private String label;
  private String message;

  public GateioError(@JsonProperty("label") String label, @JsonProperty("message") String message) {
    this.label = label;
    this.message = message;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
