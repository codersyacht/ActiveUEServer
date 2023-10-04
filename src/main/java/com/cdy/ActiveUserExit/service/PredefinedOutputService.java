package com.cdy.ActiveUserExit.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdy.ActiveUserExit.cache.GlobalCache;
import com.cdy.ActiveUserExit.entity.PredefinedOutput;
import com.cdy.ActiveUserExit.repository.PredefinedOutputRepository;

@Service
public class PredefinedOutputService 
{

    @Autowired
    PredefinedOutputRepository predefinedOutputRepository;
    @Autowired
    GlobalCache globalcache;
    private static final Logger logger = LoggerFactory.getLogger(PredefinedOutputService.class);

    public boolean savePredefinedOutput(PredefinedOutput predefinedOutput)
    {
        try
        {
            predefinedOutputRepository.save(predefinedOutput);
            logger.info("Predefined payload successfully stored for methodIdentifier "+ predefinedOutput.getMethodIdentifier());
            return true;
        }
        catch (Exception exception)
        {
          logger.info("Error encountered while storing predefined payload \n"+ exception.toString());
          exception.printStackTrace();
          return false;
        }

    }

    public Optional <PredefinedOutput> findOutputPayloadById(String methodIdentifier)
    {
        return predefinedOutputRepository.findById(methodIdentifier);
    }

    public Boolean removeRecord(String methodName)
    {
        try
        {
            predefinedOutputRepository.deleteById(methodName);
            logger.info("Predefined Output for Method "+methodName +" deleted successfully");
            return true;
        }
        catch (Exception exception)
        {
            logger.info("Error encountered while deleting method \n"+ exception.toString());
            exception.printStackTrace();
            return false;
        }
    }
    
}
