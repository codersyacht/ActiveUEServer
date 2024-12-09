package com.cdy.ActiveUserExit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "input_payload")
public class InputPayload {
  @Id
  @Column(name = "requestid")
  String requestId;
  
  @Column(name = "methodidentifier")
  String methodIdentifier;
  
  @Column(name = "inputpayload")
  String inputPayload;
  
  @Column(name = "usertoken")
  String userToken;
  
  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }
  
  public void setMethodIdentifier(String methodIdentifier) {
    this.methodIdentifier = methodIdentifier;
  }
  
  public void setInputPayload(String inputPayload) {
    this.inputPayload = inputPayload;
  }
  
  public void setUserToken(String userToken) {
    this.userToken = userToken;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof com.cdy.ActiveUserExit.entity.InputPayload))
      return false; 
    com.cdy.ActiveUserExit.entity.InputPayload other = (com.cdy.ActiveUserExit.entity.InputPayload)o;
    if (!other.canEqual(this))
      return false; 
    Object this$requestId = getRequestId(), other$requestId = other.getRequestId();
    if ((this$requestId == null) ? (other$requestId != null) : !this$requestId.equals(other$requestId))
      return false; 
    Object this$methodIdentifier = getMethodIdentifier(), other$methodIdentifier = other.getMethodIdentifier();
    if ((this$methodIdentifier == null) ? (other$methodIdentifier != null) : !this$methodIdentifier.equals(other$methodIdentifier))
      return false; 
    Object this$inputPayload = getInputPayload(), other$inputPayload = other.getInputPayload();
    if ((this$inputPayload == null) ? (other$inputPayload != null) : !this$inputPayload.equals(other$inputPayload))
      return false; 
    Object this$userToken = getUserToken(), other$userToken = other.getUserToken();
    return !((this$userToken == null) ? (other$userToken != null) : !this$userToken.equals(other$userToken));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof com.cdy.ActiveUserExit.entity.InputPayload;
  }
  
  public String toString() {
    return "InputPayload(requestId=" + getRequestId() + ", methodIdentifier=" + getMethodIdentifier() + ", inputPayload=" + getInputPayload() + ", userToken=" + getUserToken() + ")";
  }
  
  public String getRequestId() {
    return this.requestId;
  }
  
  public String getMethodIdentifier() {
    return this.methodIdentifier;
  }
  
  public String getInputPayload() {
    return this.inputPayload;
  }
  
  public String getUserToken() {
    return this.userToken;
  }
}
