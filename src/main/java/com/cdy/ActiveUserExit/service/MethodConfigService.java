package com.cdy.ActiveUserExit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdy.ActiveUserExit.api.UserExitAPI;
import com.cdy.ActiveUserExit.entity.MethodConfig;
import com.cdy.ActiveUserExit.pojo.MethodConfigWithPredefinedOutput;
import com.cdy.ActiveUserExit.repository.MethodConfigRepository;

@Service
public class MethodConfigService
{
    private static final Logger logger = LoggerFactory.getLogger(UserExitAPI.class);
    @Autowired
    MethodConfigRepository methodConfigRepository;

    public boolean saveMethodConfig(MethodConfig methodConfig)
    {
        if (methodConfig.getMethodIdentifier().equals(null) || methodConfig.getMethodIdentifier().equals(""))
        return false;
       try
       {  
            
             methodConfigRepository.save(methodConfig);
             logger.info("Saved method config data : "+ methodConfig);
             return true;
       }
       
       catch (Exception exception)
       {
          logger.info("Error encountered while saving method config  \n"+ exception.toString());
          exception.printStackTrace();
          return false;
       }
    }

    public MethodConfig findMethodConfigbyMethodName (String methodName)
    {
       
        MethodConfig methodConfig = methodConfigRepository.findMethodConfigbyMethodName(methodName);
        logger.info("Retrieved methodconfig for methodName "+methodName+ " is :"+ methodConfig);
        return methodConfig;   
    

    }

    public MethodConfigWithPredefinedOutput findMethodConfigWithPredefinedOutput (String methodName)
    {
       
        MethodConfigWithPredefinedOutput methodConfigWithPredefinedOutput = methodConfigRepository.MethodConfigWithPredefinedOutput(methodName);
        logger.info("Retrieved methodconfig for methodName "+methodName+ " is :"+ methodConfigWithPredefinedOutput);
        return methodConfigWithPredefinedOutput;   
    

    }

    public MethodConfig[] findAllMethods()
    {
         MethodConfig[] methodConfig = methodConfigRepository.findAllMethods();
         logger.info("Retrieved methodconfig for all methods");
         return methodConfig;
    }

    public Boolean removeRecord(String methodName)
    {
        try
        {
            methodConfigRepository.deleteById(methodName);
            logger.info("Method "+methodName +" deleted successfully");
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
