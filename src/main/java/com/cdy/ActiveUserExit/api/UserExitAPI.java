package com.cdy.ActiveUserExit.api;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cdy.ActiveUserExit.cache.GlobalCache;
import com.cdy.ActiveUserExit.entity.InputPayload;
import com.cdy.ActiveUserExit.entity.MethodConfig;
import com.cdy.ActiveUserExit.entity.OutputPayload;
import com.cdy.ActiveUserExit.entity.PredefinedOutput;
import com.cdy.ActiveUserExit.pojo.InputWithOutputPayload;
import com.cdy.ActiveUserExit.pojo.MethodConfigWithPredefinedOutput;
import com.cdy.ActiveUserExit.service.InputPayloadService;
import com.cdy.ActiveUserExit.service.MethodConfigService;
import com.cdy.ActiveUserExit.service.OutputPayloadService;
import com.cdy.ActiveUserExit.service.PredefinedOutputService;

import jakarta.persistence.Access;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@CrossOrigin(origins = "*")
@RestController
public class UserExitAPI implements Serializable
{

   
    @Autowired
    GlobalCache globalCache;
    @Autowired
    MethodConfigService methodConfigService;
    @Autowired
    InputPayloadService inputPayloadService;
    @Autowired
    OutputPayloadService outputPayloadService;
    @Autowired
    PredefinedOutputService predefinedOutputService;
    private static final Logger logger = LoggerFactory.getLogger(UserExitAPI.class);

    @PostMapping("/StoreMethodConfig")
    public boolean saveMethodConfig(HttpSession session, @RequestBody MethodConfig methodConfig)
    {
       logger.info("Storing data for"+ methodConfig.getMethodIdentifier());
       return methodConfigService.saveMethodConfig(methodConfig);
       
    }
    
    @PostMapping("/FindMethodConfig")
    public MethodConfig findMethodConfigbyMethodName(HttpSession session, @RequestBody String methodName)
    {
       return methodConfigService.findMethodConfigbyMethodName(methodName);
     
    }

   @PostMapping("/FindMethodConfigWithPredefinedOutput")
    public MethodConfigWithPredefinedOutput findMethodConfigWithPredefinedOutput(HttpSession session, @RequestBody String methodName)
    {
       return methodConfigService.findMethodConfigWithPredefinedOutput(methodName);
     
    }

    @PostMapping("/StoreMethodConfigWithPredefinedOutput")
    public boolean saveMethodConfigWithPredefinedOutput(HttpSession session, @RequestBody MethodConfigWithPredefinedOutput methodConfigWithPredefinedOutput)
    {
       logger.info("Storing data for"+ methodConfigWithPredefinedOutput.getMethodIdentifier());
       MethodConfig methodConfig = new MethodConfig();
       methodConfig.setMethodIdentifier(methodConfigWithPredefinedOutput.getMethodIdentifier());
       methodConfig.setInputAsOutput(methodConfigWithPredefinedOutput.getInputAsOutput());
       methodConfig.setOutputPredefined(methodConfigWithPredefinedOutput.getOutputPredefined());
       boolean status = methodConfigService.saveMethodConfig(methodConfig);
       if (status == false)
       return status;
       PredefinedOutput predefinedOutput = new PredefinedOutput();
       predefinedOutput.setMethodIdentifier(methodConfigWithPredefinedOutput.getMethodIdentifier());
       predefinedOutput.setOutputPayload(methodConfigWithPredefinedOutput.getOutputPayload());
       status = predefinedOutputService.savePredefinedOutput(predefinedOutput);
       if (status == false)
       return status;
       return status;
   
    }

   @PostMapping("/RemoveMethodRecord") 
   public boolean removeMethodRecord(HttpSession session, @RequestBody String methodName)
   {
      return methodConfigService.removeRecord(methodName);
   }

 
   @PostMapping("/RemoveMethodRecordWithPredefinedOutput") 
   public boolean removeMethodRecordWithPredefinedOutput(HttpSession session, @RequestBody String methodName)
   {
      try
      {
      boolean status = predefinedOutputService.removeRecord(methodName); 
      if (status == false)
       return false;
      status = methodConfigService.removeRecord(methodName);
      if (status == false)
       return status;
       return status;
      }
      catch (Exception exception)
      {
        
         return false;

      }
   }

   @PostMapping("/RemovePredefinedOutput") 
   public boolean RemovePredefinedOutput(HttpSession session, @RequestBody String methodName)
   {
      return predefinedOutputService.removeRecord(methodName);
   }

   
   @PostMapping("/StoreInputPayload") 
   public boolean saveInputPayload(HttpSession session, @RequestBody InputPayload inputpayload)
   {
      return inputPayloadService.saveInputPayload(inputpayload, false); 
   }


   @PostMapping("/FindInputPayLoadbyRequestId")
    public InputPayload findInputPayLoadbyRequestId(HttpSession session, @RequestBody String requestId)
    {
      return inputPayloadService.findInputPayloadByRequestId(requestId);
    }


    @PostMapping("/FindInputWithOutputPayLoadbyRequestId")
    public InputWithOutputPayload findInputWithOutputbyRequestId(HttpSession session, @RequestBody String requestId)
    {

       return inputPayloadService.findInputWithOutputbyRequestId(requestId);
     
    }

   @GetMapping("/FindAllMethodConfig")
    public MethodConfig[] findAllMethodConfig(HttpSession session)
    {

       return methodConfigService.findAllMethods();
     
    }
      
  

   @PostMapping("/StorePredefinedOutput") 
   public boolean savePredefinedOutput(HttpSession session, @RequestBody PredefinedOutput predefinedOutputPayload)
   {

     return predefinedOutputService.savePredefinedOutput(predefinedOutputPayload);
     
   }

   @PostMapping("/StoreOutputPayload") 
   public boolean StoreOutputPayload (HttpSession sesssion, @RequestBody OutputPayload outputPayload)
   {
      
      return outputPayloadService.StoreOutputPayLoad(outputPayload);
     
   }

   @PostMapping("/GetPredefinedOutput")
   public String getPredefinedOutput(HttpSession sesssion, @RequestBody PredefinedOutput predefined)
   {
      PredefinedOutput predefinedOutput =null;
      logger.info("getPredefinedOutput method called for methodIdentifier as "+ predefined.getMethodIdentifier());
      predefinedOutput = predefinedOutputService.findOutputPayloadById(predefined.getMethodIdentifier()).orElse(new PredefinedOutput());
      logger.info("predefinedOutput loaded with predefined data as "+ predefinedOutput.getOutputPayload());
      String outputData = predefinedOutput.getOutputPayload();
      return outputData;
   }

   @GetMapping("/GetActiveRequests")
   public String GetActiveRequests(HttpSession sesssion)
   {
   String retVal =  globalCache.outputCommitted.keySet().toString();
   return retVal;
   }

   @PostMapping("/InvokeUserExit")
   public String InvokeUserExit(HttpSession session,  @RequestBody InputPayload inputPayload) throws InterruptedException
   {
      logger.info("UserExit Triggered for method "+inputPayload.getMethodIdentifier()+" with requestId "+inputPayload.getRequestId());
      MethodConfig methodConfig = methodConfigService.findMethodConfigbyMethodName(inputPayload.getMethodIdentifier());
      logger.info("Method config for "+inputPayload.getMethodIdentifier()+" retrieved with the following settings, inputAsOutput: "+ methodConfig.getInputAsOutput()+" outputPredefined: "+ methodConfig.getOutputPredefined());
      String requestId=inputPayload.getRequestId();
      String outputData=null;
       if (methodConfig.getInputAsOutput())
      {
         boolean response = inputPayloadService.saveInputPayload(inputPayload, false);
               if (response == false)
                  {
                     return null;
                  }
        OutputPayload outputPayload =  new OutputPayload(requestId, inputPayload.getInputPayload());
        if (StoreOutputPayload(session, outputPayload) == false)
        {
         return null;
      }
        return outputPayload.getOutputPayload();

      }
      else if (methodConfig.getOutputPredefined())
      {
         boolean response = inputPayloadService.saveInputPayload(inputPayload, false);
               if (response == false)
                  {
                     return null;
                  }
         return getPredefinedOutput(session, new PredefinedOutput(inputPayload.getMethodIdentifier(), null));
      }
      else
      {
         boolean response = inputPayloadService.saveInputPayload(inputPayload, true);
               if (response == false)
                  {
                     return null;
                  }
         globalCache.setHTTPRequestCounterInc(requestId);
         logger.info("HTTP Request Count for "+ requestId+"=" + globalCache.getHTTPRequestCounter(requestId));         
         logger.info("Awiting output payload in loop");
         while((globalCache.outputCommitted.get(requestId)==null) || (globalCache.outputCommitted.get(requestId)==false))
         {

            Thread.sleep(5000);
            System.out.println(globalCache.outputCommitted.get(requestId));
         }    
         logger.info("Exiting output payload");
            
            outputData = outputPayloadService.FindOutputPayloadById(requestId).orElse(new OutputPayload()).getOutputPayload();
            globalCache.setHTTPRequestCounterDec(requestId);
            logger.info("HTTP Request Count for "+ requestId+"=" + globalCache.getHTTPRequestCounter(requestId));
            if ((globalCache.getHTTPRequestCounter(requestId) == 0))
            {
                globalCache.clearCache(requestId);
                logger.info("Cache cleared for requestId"+ requestId);
            }
      
      }
      return outputData;
   }
   
}