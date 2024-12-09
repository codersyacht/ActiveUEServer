package com.cdy.ActiveUserExit.pojo;

import jakarta.persistence.Id;

public final class MethodConfigWithPredefinedOutput {
  @Id
  private final String methodIdentifier;
  
  private final Boolean inputAsOutput;
  
  private final Boolean outputPredefined;
  
  private final String outputPayload;
  
  private final String userToken;
  
  public MethodConfigWithPredefinedOutput(String methodIdentifier, Boolean inputAsOutput, Boolean outputPredefined, String outputPayload, String userToken) {
    this.methodIdentifier = methodIdentifier;
    this.inputAsOutput = inputAsOutput;
    this.outputPredefined = outputPredefined;
    this.outputPayload = outputPayload;
    this.userToken = userToken;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof com.cdy.ActiveUserExit.pojo.MethodConfigWithPredefinedOutput))
      return false; 
    com.cdy.ActiveUserExit.pojo.MethodConfigWithPredefinedOutput other = (com.cdy.ActiveUserExit.pojo.MethodConfigWithPredefinedOutput)o;
    Object this$inputAsOutput = getInputAsOutput(), other$inputAsOutput = other.getInputAsOutput();
    if ((this$inputAsOutput == null) ? (other$inputAsOutput != null) : !this$inputAsOutput.equals(other$inputAsOutput))
      return false; 
    Object this$outputPredefined = getOutputPredefined(), other$outputPredefined = other.getOutputPredefined();
    if ((this$outputPredefined == null) ? (other$outputPredefined != null) : !this$outputPredefined.equals(other$outputPredefined))
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

  
  public String toString() {
    return "MethodConfigWithPredefinedOutput(methodIdentifier=" + getMethodIdentifier() + ", inputAsOutput=" + getInputAsOutput() + ", outputPredefined=" + getOutputPredefined() + ", outputPayload=" + getOutputPayload() + ", userToken=" + getUserToken() + ")";
  }
  
  public String getMethodIdentifier() {
    return this.methodIdentifier;
  }
  
  public Boolean getInputAsOutput() {
    return this.inputAsOutput;
  }
  
  public Boolean getOutputPredefined() {
    return this.outputPredefined;
  }
  
  public String getOutputPayload() {
    return this.outputPayload;
  }
  
  public String getUserToken() {
    return this.userToken;
  }
}
