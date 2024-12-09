package com.cdy.ActiveUserExit.service;

import com.cdy.ActiveUserExit.cache.GlobalCache;
import com.cdy.ActiveUserExit.entity.PredefinedOutput;
import com.cdy.ActiveUserExit.repository.PredefinedOutputRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PredefinedOutputService {
  @Autowired
  PredefinedOutputRepository predefinedOutputRepository;
  
  @Autowired
  GlobalCache globalcache;
  
  private static final Logger logger = LoggerFactory.getLogger(com.cdy.ActiveUserExit.service.PredefinedOutputService.class);
  
  public boolean savePredefinedOutput(PredefinedOutput predefinedOutput) {
    try {
      this.predefinedOutputRepository.save(predefinedOutput);
      logger.info("Predefined payload successfully stored for methodIdentifier " + predefinedOutput.getMethodIdentifier());
      return true;
    } catch (Exception exception) {
      logger.info("Error encountered while storing predefined payload \n" + exception.toString());
      exception.printStackTrace();
      return false;
    } 
  }
  
  public PredefinedOutput findOutputPayload(String methodIdentifier, String userToken) {
    return this.predefinedOutputRepository.findPredefinedOutput(methodIdentifier, userToken);
  }
  
  public Boolean removeRecord(String methodName, String userToken) {
    try {
      PredefinedOutput predefinedOutput = this.predefinedOutputRepository.findPredefinedOutput(methodName, userToken);
      if (predefinedOutput != null) {
        this.predefinedOutputRepository.delete(predefinedOutput);
        logger.info("Predefined Output for Method " + methodName + " deleted successfully");
        return Boolean.valueOf(true);
      } 
      logger.info("Predefined Output for Method " + methodName + " does not exist");
      return Boolean.valueOf(true);
    } catch (Exception exception) {
      logger.info("Error encountered while deleting method \n" + exception.toString());
      exception.printStackTrace();
      return Boolean.valueOf(false);
    } 
  }
}
