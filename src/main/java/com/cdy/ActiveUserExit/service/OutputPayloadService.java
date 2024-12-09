package com.cdy.ActiveUserExit.service;

import com.cdy.ActiveUserExit.cache.GlobalCache;
import com.cdy.ActiveUserExit.entity.OutputPayload;
import com.cdy.ActiveUserExit.repository.OutputPayloadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutputPayloadService {
  private static final Logger logger = LoggerFactory.getLogger(InputPayloadService.class);
  
  @Autowired
  OutputPayloadRepository outputPayloadRepository;
  
  @Autowired
  GlobalCache globalCache;
  
  public boolean StoreOutputPayLoad(OutputPayload outputpayload) {
    try {
      this.outputPayloadRepository.save(outputpayload);
      if (this.globalCache.requestCount.get(outputpayload.getRequestId()) != null)
        this.globalCache.outputCommitted.put(outputpayload.getRequestId(), Boolean.valueOf(true)); 
      return true;
    } catch (Exception exception) {
      logger.info("Error encountered while storing output payload \n" + exception.toString());
      exception.printStackTrace();
      return false;
    } 
  }
  
  public OutputPayload FindOutputPayload(String requestId, String userToken) {
    return this.outputPayloadRepository.findOutputPayload(requestId, userToken);
  }
}
