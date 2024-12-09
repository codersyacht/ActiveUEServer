package com.cdy.ActiveUserExit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "output_predefined")
public class PredefinedOutput {
  @Id
  @Column(name = "methodidentifier")
  String methodIdentifier;
  
  @Column(name = "outputpayload")
  String outputPayload;
  
  @Column(name = "usertoken")
  String userToken;
  
  public void setMethodIdentifier(String methodIdentifier) {
    this.methodIdentifier = methodIdentifier;
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
    if (!(o instanceof com.cdy.ActiveUserExit.entity.PredefinedOutput))
      return false; 
    com.cdy.ActiveUserExit.entity.PredefinedOutput other = (com.cdy.ActiveUserExit.entity.PredefinedOutput)o;
    if (!other.canEqual(this))
      return false; 
    Object this$methodIdentifier = getMethodIdentifier(), other$methodIdentifier = other.getMethodIdentifier();
    if ((this$methodIdentifier == null) ? (other$methodIdentifier != null) : !this$methodIdentifier.equals(other$methodIdentifier))
      return false; 
    Object this$outputPayload = getOutputPayload(), other$outputPayload = other.getOutputPayload();
    if ((this$outputPayload == null) ? (other$outputPayload != null) : !this$outputPayload.equals(other$outputPayload))
      return false; 
    Object this$userToken = getUserToken(), other$userToken = other.getUserToken();
    return !((this$userToken == null) ? (other$userToken != null) : !this$userToken.equals(other$userToken));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof com.cdy.ActiveUserExit.entity.PredefinedOutput;
  }

  public String toString() {
    return "PredefinedOutput(methodIdentifier=" + getMethodIdentifier() + ", outputPayload=" + getOutputPayload() + ", userToken=" + getUserToken() + ")";
  }
  
  public String getMethodIdentifier() {
    return this.methodIdentifier;
  }
  
  public String getOutputPayload() {
    return this.outputPayload;
  }
  
  public String getUserToken() {
    return this.userToken;
  }
  
  public PredefinedOutput(String methodIdentifier, String outputPayload, String userToken) {
    this.methodIdentifier = methodIdentifier;
    this.outputPayload = outputPayload;
    this.userToken = userToken;
  }
  
  public PredefinedOutput() {}
}
