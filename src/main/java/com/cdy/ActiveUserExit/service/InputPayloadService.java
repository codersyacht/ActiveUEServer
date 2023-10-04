package com.cdy.ActiveUserExit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdy.ActiveUserExit.cache.GlobalCache;
import com.cdy.ActiveUserExit.entity.InputPayload;
import com.cdy.ActiveUserExit.pojo.InputWithOutputPayload;
import com.cdy.ActiveUserExit.repository.InputPayloadRepository;

@Service
public class InputPayloadService
{
    private static final Logger logger = LoggerFactory.getLogger(InputPayloadService.class);
    @Autowired
    GlobalCache globalCache;
    @Autowired
    InputPayloadRepository inputPayloadRepository;
   
    public boolean saveInputPayload(InputPayload inputPayload, boolean cacheEnable)
    {     
         try
         {
          inputPayloadRepository.save(inputPayload);
          logger.info("Input payload successfully saved "+ inputPayload);
          if(cacheEnable)
           {
            globalCache.outputCommitted.put(inputPayload.getRequestId(), false);
            logger.info("Cache data created for "+inputPayload.getRequestId());
           }
          return true;
         }
         catch (Exception exception)
         {
          logger.info("Error encountered while storing input payload \n"+ exception.toString());
          exception.printStackTrace();
          return false;
         }
    }

    public InputWithOutputPayload findInputWithOutputbyRequestId(String requestId)
    {
      InputWithOutputPayload inputWithOutputPayload = inputPayloadRepository.findInputWithOutputbyRequestId(requestId);
      logger.info("Retrieved data for requestId "+requestId+ " is :"+ inputWithOutputPayload);
      return inputWithOutputPayload;   
    }

    public InputPayload findInputPayloadByRequestId(String requestId)
    {
       InputPayload inputPayload = inputPayloadRepository.findById(requestId).orElse(new InputPayload());
       logger.info("Retrieved data for requestId "+requestId+ " is :"+ inputPayload);
       return inputPayload;
    }    
}
