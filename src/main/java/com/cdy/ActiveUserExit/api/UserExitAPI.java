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
import com.cdy.ActiveUserExit.service.InputPayloadService;
import com.cdy.ActiveUserExit.service.MethodConfigService;
import com.cdy.ActiveUserExit.service.OutputPayloadService;
import com.cdy.ActiveUserExit.service.PredefinedOutputService;

import jakarta.servlet.http.HttpSession;

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
       return methodConfigService.saveMethodConfig(methodConfig);
       
    }
    
    @PostMapping("/FindMethodConfig")
    public MethodConfig findMethodConfigbyMethodName(HttpSession session, @RequestBody String methodName)
    {
       return methodConfigService.findMethodConfigbyMethodName(methodName);
     
    }
   @PostMapping("/StoreInputPayload") 
   public boolean saveInputPayload(HttpSession session, @RequestBody InputPayload inputpayload)
   {
      return inputPayloadService.saveInputPayload(inputpayload); 
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
      String requestId=inputPayload.getRequestId();
      String outputData=null;
      boolean response = inputPayloadService.saveInputPayload(inputPayload);
      if (response == false)
      {
         return null;
      }
      globalCache.setHTTPRequestCounterInc(requestId);
      logger.info("HTTP Request Count for "+ requestId+"=" + globalCache.getHTTPRequestCounter(requestId));
      MethodConfig methodConfig = methodConfigService.findMethodConfigbyMethodName(inputPayload.getMethodIdentifier());
      logger.info("Method config for "+inputPayload.getMethodIdentifier()+" retrieved with the following settings, inputAsOutput: "+ methodConfig.getInputAsOutput()+" outputPredefined: "+ methodConfig.getOutputPredefined());
     
      if (methodConfig.getInputAsOutput())
      {
        OutputPayload outputPayload =  new OutputPayload(requestId, inputPayload.getInputPayload());
        if (StoreOutputPayload(session, outputPayload) == false)
        {
         return null;
      }
        return outputPayload.getOutputPayload();

      }
      else if (methodConfig.getOutputPredefined())
      {
         return getPredefinedOutput(session, new PredefinedOutput(inputPayload.getMethodIdentifier(), null));
      }
      else
      {
         logger.info("Awiting output payload in loop");
         while((globalCache.outputCommitted.get(requestId)==null) || (globalCache.outputCommitted.get(requestId)==false))
         {

            Thread.sleep(5000);
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