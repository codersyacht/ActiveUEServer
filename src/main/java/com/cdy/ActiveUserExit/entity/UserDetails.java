package com.cdy.ActiveUserExit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_details")
public class UserDetails {
  @Id
  @Column(name = "userid")
  String userId;
  
  @Column(name = "password")
  String password;
  
  @Column(name = "usertoken")
  String userToken;
  
  public void setUserId(String userId) {
    this.userId = userId;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public void setUserToken(String userToken) {
    this.userToken = userToken;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof com.cdy.ActiveUserExit.entity.UserDetails))
      return false; 
    com.cdy.ActiveUserExit.entity.UserDetails other = (com.cdy.ActiveUserExit.entity.UserDetails)o;
    if (!other.canEqual(this))
      return false; 
    Object this$userId = getUserId(), other$userId = other.getUserId();
    if ((this$userId == null) ? (other$userId != null) : !this$userId.equals(other$userId))
      return false; 
    Object this$password = getPassword(), other$password = other.getPassword();
    if ((this$password == null) ? (other$password != null) : !this$password.equals(other$password))
      return false; 
    Object this$userToken = getUserToken(), other$userToken = other.getUserToken();
    return !((this$userToken == null) ? (other$userToken != null) : !this$userToken.equals(other$userToken));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof com.cdy.ActiveUserExit.entity.UserDetails;
  }

  public String toString() {
    return "UserDetails(userId=" + getUserId() + ", password=" + getPassword() + ", userToken=" + getUserToken() + ")";
  }
  
  public String getUserId() {
    return this.userId;
  }
  
  public String getPassword() {
    return this.password;
  }
  
  public String getUserToken() {
    return this.userToken;
  }
}
