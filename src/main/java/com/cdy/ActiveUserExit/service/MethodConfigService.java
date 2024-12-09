package com.cdy.ActiveUserExit.service;

import com.cdy.ActiveUserExit.api.UserExitAPI;
import com.cdy.ActiveUserExit.entity.MethodConfig;
import com.cdy.ActiveUserExit.pojo.MethodConfigWithPredefinedOutput;
import com.cdy.ActiveUserExit.repository.MethodConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MethodConfigService {
  private static final Logger logger = LoggerFactory.getLogger(UserExitAPI.class);
  
  @Autowired
  MethodConfigRepository methodConfigRepository;
  
  public boolean saveMethodConfig(MethodConfig methodConfig) {
    logger.info("Method name: " + methodConfig.getMethodIdentifier() + " User Token " + methodConfig.getUserToken());
    if (methodConfig.getMethodIdentifier().equals(null) || methodConfig.getMethodIdentifier().equals(""))
      return false; 
    try {
      this.methodConfigRepository.save(methodConfig);
      logger.info("Saved method config data : " + String.valueOf(methodConfig));
      return true;
    } catch (Exception exception) {
      logger.info("Error encountered while saving method config  \n" + exception.toString());
      exception.printStackTrace();
      return false;
    } 
  }
  
  public MethodConfig findMethodConfigbyMethodName(String methodName, String userToken) {
    MethodConfig methodConfig = this.methodConfigRepository.findMethodConfigbyMethodName(methodName, userToken);
    logger.info("Retrieved methodconfig for methodName " + methodName + " is :" + String.valueOf(methodConfig));
    return methodConfig;
  }
  
  public MethodConfigWithPredefinedOutput findMethodConfigWithPredefinedOutput(String methodName, String userToken) {
    logger.info("Receibed Data " + methodName + " " + userToken);
    MethodConfigWithPredefinedOutput methodConfigWithPredefinedOutput = this.methodConfigRepository.MethodConfigWithPredefinedOutput(methodName, userToken);
    logger.info("Retrieved methodconfig for methodName " + methodName + " is :" + String.valueOf(methodConfigWithPredefinedOutput));
    return methodConfigWithPredefinedOutput;
  }
  
  public MethodConfig[] findAllMethods(String userToken) {
    MethodConfig[] methodConfig = this.methodConfigRepository.findAllMethods(userToken);
    logger.info("Retrieved methodconfig for all methods of " + userToken);
    return methodConfig;
  }
  
  public Boolean removeRecord(String methodName, String userId) {
    try {
      this.methodConfigRepository.delete(findMethodConfigbyMethodName(methodName, userId));
      logger.info("Method " + methodName + " deleted successfully for user" + userId);
      return Boolean.valueOf(true);
    } catch (Exception exception) {
      logger.info("Error encountered while deleting method \n" + exception.toString());
      exception.printStackTrace();
      return Boolean.valueOf(false);
    } 
  }
}
