package com.cdy.ActiveUserExit.pojo;

import jakarta.persistence.Id;

public final class InputWithOutputPayload {
  @Id
  private final String requestId;
  
  private final String methodIdentifier;
  
  private final String inputPayload;
  
  private final String outputPayload;
  
  public InputWithOutputPayload(String requestId, String methodIdentifier, String inputPayload, String outputPayload) {
    this.requestId = requestId;
    this.methodIdentifier = methodIdentifier;
    this.inputPayload = inputPayload;
    this.outputPayload = outputPayload;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof com.cdy.ActiveUserExit.pojo.InputWithOutputPayload))
      return false; 
    com.cdy.ActiveUserExit.pojo.InputWithOutputPayload other = (com.cdy.ActiveUserExit.pojo.InputWithOutputPayload)o;
    Object this$requestId = getRequestId(), other$requestId = other.getRequestId();
    if ((this$requestId == null) ? (other$requestId != null) : !this$requestId.equals(other$requestId))
      return false; 
    Object this$methodIdentifier = getMethodIdentifier(), other$methodIdentifier = other.getMethodIdentifier();
    if ((this$methodIdentifier == null) ? (other$methodIdentifier != null) : !this$methodIdentifier.equals(other$methodIdentifier))
      return false; 
    Object this$inputPayload = getInputPayload(), other$inputPayload = other.getInputPayload();
    if ((this$inputPayload == null) ? (other$inputPayload != null) : !this$inputPayload.equals(other$inputPayload))
      return false; 
    Object this$outputPayload = getOutputPayload(), other$outputPayload = other.getOutputPayload();
    return !((this$outputPayload == null) ? (other$outputPayload != null) : !this$outputPayload.equals(other$outputPayload));
  }
    
  public String toString() {
    return "InputWithOutputPayload(requestId=" + getRequestId() + ", methodIdentifier=" + getMethodIdentifier() + ", inputPayload=" + getInputPayload() + ", outputPayload=" + getOutputPayload() + ")";
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
  
  public String getOutputPayload() {
    return this.outputPayload;
  }
}
