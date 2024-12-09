package com.cdy.ActiveUserExit.entity;

import java.io.Serializable;

public class MethodConfigId implements Serializable {
  @SuppressWarnings("unused")
  private String methodIdentifier;
  @SuppressWarnings("unused")
  private String userToken;
  
  public MethodConfigId() {}
  
  public MethodConfigId(String methodIdentifier, String userToken) 
  {
    this.methodIdentifier = methodIdentifier;
    this.userToken = userToken;
  }
}
