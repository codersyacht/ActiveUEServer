package com.cdy.ActiveUserExit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "output_payload")
public class OutputPayload {
  @Id
  @Column(name = "requestid")
  String requestId;
  
  @Column(name = "outputpayload")
  String outputPayload;
  
  @Column(name = "usertoken")
  String userToken;
  
  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }
  
  public void setOutputPayload(String outputPayload) {
    this.outputPayload = outputPayload;
  }
  
  public void setUserToken(String userToken) {
    this.userToken = userToken;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof com.cdy.ActiveUserExit.entity.OutputPayload))
      return false; 
    com.cdy.ActiveUserExit.entity.OutputPayload other = (com.cdy.ActiveUserExit.entity.OutputPayload)o;
    if (!other.canEqual(this))
      return false; 
    Object this$requestId = getRequestId(), other$requestId = other.getRequestId();
    if ((this$requestId == null) ? (other$requestId != null) : !this$requestId.equals(other$requestId))
      return false; 
    Object this$outputPayload = getOutputPayload(), other$outputPayload = other.getOutputPayload();
    if ((this$outputPayload == null) ? (other$outputPayload != null) : !this$outputPayload.equals(other$outputPayload))
      return false; 
    Object this$userToken = getUserToken(), other$userToken = other.getUserToken();
    return !((this$userToken == null) ? (other$userToken != null) : !this$userToken.equals(other$userToken));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof com.cdy.ActiveUserExit.entity.OutputPayload;
  }

  public String toString() {
    return "OutputPayload(requestId=" + getRequestId() + ", outputPayload=" + getOutputPayload() + ", userToken=" + getUserToken() + ")";
  }
  
  public String getRequestId() {
    return this.requestId;
  }
  
  public String getOutputPayload() {
    return this.outputPayload;
  }
  
  public String getUserToken() {
    return this.userToken;
  }
  
  public OutputPayload(String requestId, String outputPayload, String userToken) {
    this.requestId = requestId;
    this.outputPayload = outputPayload;
    this.userToken = userToken;
  }
  
  public OutputPayload() {}
}
