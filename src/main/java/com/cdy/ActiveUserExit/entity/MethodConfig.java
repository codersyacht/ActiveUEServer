package com.cdy.ActiveUserExit.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@IdClass(MethodConfigId.class)
@Table(name = "method_cofig")
public class MethodConfig {
  @Id
  @Column(name = "methodidentifier")
  String methodIdentifier;
  
  @Column(name = "inputasoutput")
  Boolean inputAsOutput;
  
  @Column(name = "outputpredefined")
  Boolean outputPredefined;
  
  @Id
  @Column(name = "usertoken")
  String userToken;
  
  public void setMethodIdentifier(String methodIdentifier) {
    this.methodIdentifier = methodIdentifier;
  }
  
  public void setInputAsOutput(Boolean inputAsOutput) {
    this.inputAsOutput = inputAsOutput;
  }
  
  public void setOutputPredefined(Boolean outputPredefined) {
    this.outputPredefined = outputPredefined;
  }
  
  public void setUserToken(String userToken) {
    this.userToken = userToken;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof com.cdy.ActiveUserExit.entity.MethodConfig))
      return false; 
    com.cdy.ActiveUserExit.entity.MethodConfig other = (com.cdy.ActiveUserExit.entity.MethodConfig)o;
    if (!other.canEqual(this))
      return false; 
    Object this$inputAsOutput = getInputAsOutput(), other$inputAsOutput = other.getInputAsOutput();
    if ((this$inputAsOutput == null) ? (other$inputAsOutput != null) : !this$inputAsOutput.equals(other$inputAsOutput))
      return false; 
    Object this$outputPredefined = getOutputPredefined(), other$outputPredefined = other.getOutputPredefined();
    if ((this$outputPredefined == null) ? (other$outputPredefined != null) : !this$outputPredefined.equals(other$outputPredefined))
      return false; 
    Object this$methodIdentifier = getMethodIdentifier(), other$methodIdentifier = other.getMethodIdentifier();
    if ((this$methodIdentifier == null) ? (other$methodIdentifier != null) : !this$methodIdentifier.equals(other$methodIdentifier))
      return false; 
    Object this$userToken = getUserToken(), other$userToken = other.getUserToken();
    return !((this$userToken == null) ? (other$userToken != null) : !this$userToken.equals(other$userToken));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof com.cdy.ActiveUserExit.entity.MethodConfig;
  }

  public String toString() {
    return "MethodConfig(methodIdentifier=" + getMethodIdentifier() + ", inputAsOutput=" + getInputAsOutput() + ", outputPredefined=" + getOutputPredefined() + ", userToken=" + getUserToken() + ")";
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
  
  public String getUserToken() {
    return this.userToken;
  }
}
