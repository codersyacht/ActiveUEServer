package com.cdy.ActiveUserExit.service;

import com.cdy.ActiveUserExit.cache.GlobalCache;
import com.cdy.ActiveUserExit.entity.UserDetails;
import com.cdy.ActiveUserExit.repository.UserDetailsRepository;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {
  private static final Logger logger = LoggerFactory.getLogger(InputPayloadService.class);
  
  @Autowired
  GlobalCache globalCache;
  
  @Autowired
  UserDetailsRepository userDetailsRepository;
  
  public boolean saveUserDetails(UserDetails userDetailsPayload) {
    try {
      Random random = new Random();
      String id = String.format("%04d", new Object[] { Integer.valueOf(random.nextInt(10000)) });
      userDetailsPayload.setUserToken(id);
      this.userDetailsRepository.save(userDetailsPayload);
      logger.info("User payload successfully saved " + String.valueOf(userDetailsPayload));
      return true;
    } catch (Exception exception) {
      logger.info("Error encountered while storing user payload \n" + exception.toString());
      exception.printStackTrace();
      return false;
    } 
  }
  
  public UserDetails getUserDetails(String userId) {
    UserDetails output = this.userDetailsRepository.getUserDetails(userId);
    if (output != null)
      logger.info("UserToken is" + output.getUserToken()); 
    return output;
  }
}
