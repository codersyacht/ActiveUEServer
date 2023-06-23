package com.cdy.ActiveUserExit.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdy.ActiveUserExit.cache.GlobalCache;
import com.cdy.ActiveUserExit.entity.OutputPayload;
import com.cdy.ActiveUserExit.repository.OutputPayloadRepository;

@Service
public class OutputPayloadService 
{
    private static final Logger logger = LoggerFactory.getLogger(InputPayloadService.class);
    @Autowired
    OutputPayloadRepository outputPayloadRepository;
    @Autowired
    GlobalCache globalCache; 
    public boolean StoreOutputPayLoad(OutputPayload outputpayload)
    {
        try
        {
            outputPayloadRepository.save(outputpayload);
            globalCache.outputCommitted.put(outputpayload.getRequestId(), true);
            return true;
        }
        catch (Exception exception)
        {
        logger.info("Error encountered while storing output payload \n"+ exception.toString());
          exception.printStackTrace();
          return false;
        }
        
        
    }

    public Optional <OutputPayload> FindOutputPayloadById(String requestId)
    {
        return outputPayloadRepository.findById(requestId);
    }
    
}
