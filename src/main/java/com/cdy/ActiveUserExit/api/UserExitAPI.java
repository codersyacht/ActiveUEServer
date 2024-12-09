package com.cdy.ActiveUserExit.api;

import com.cdy.ActiveUserExit.cache.GlobalCache;
import com.cdy.ActiveUserExit.entity.InputPayload;
import com.cdy.ActiveUserExit.entity.MethodConfig;
import com.cdy.ActiveUserExit.entity.OutputPayload;
import com.cdy.ActiveUserExit.entity.PredefinedOutput;
import com.cdy.ActiveUserExit.entity.UserDetails;
import com.cdy.ActiveUserExit.pojo.ActiveRequestsList;
import com.cdy.ActiveUserExit.pojo.InputWithOutputPayload;
import com.cdy.ActiveUserExit.pojo.MethodConfigWithPredefinedOutput;
import com.cdy.ActiveUserExit.service.InputPayloadService;
import com.cdy.ActiveUserExit.service.MethodConfigService;
import com.cdy.ActiveUserExit.service.OutputPayloadService;
import com.cdy.ActiveUserExit.service.PredefinedOutputService;
import com.cdy.ActiveUserExit.service.UserDetailsService;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"*"})
@RestController
public class UserExitAPI implements Serializable {
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
  
  @Autowired
  UserDetailsService userDetailsService;
  
  private static final Logger logger = LoggerFactory.getLogger(com.cdy.ActiveUserExit.api.UserExitAPI.class);
  
  @PostMapping({"/SaveUserDetails"})
  public boolean saveUserDetails(HttpSession session, @RequestBody UserDetails userDetails) {
    logger.info("Checking for existing user account");
    UserDetails validUser = getUserDetails(session, userDetails);
    if (validUser != null)
      return false; 
    logger.info("Storing data for" + userDetails.getUserId());
    this.userDetailsService.saveUserDetails(userDetails);
    return true;
  }
  
  @PostMapping({"/GetUserDetails"})
  public UserDetails getUserDetails(HttpSession session, @RequestBody UserDetails userDetails) {
    logger.info("Fetching data for" + userDetails.getUserId());
    UserDetails returnedUserDetails = this.userDetailsService.getUserDetails(userDetails.getUserId());
    if (returnedUserDetails == null)
      return null; 
    if (returnedUserDetails.getPassword().equals(userDetails.getPassword())) {
      logger.info("User authenticated " + userDetails.getUserId());
      return returnedUserDetails;
    } 
    logger.info("User password mismatch " + userDetails.getUserId());
    return userDetails;
  }
  
  @PostMapping({"/StoreMethodConfig"})
  public boolean saveMethodConfig(HttpSession session, @RequestBody MethodConfig methodConfig) {
    logger.info("Storing data for" + methodConfig.getMethodIdentifier());
    return this.methodConfigService.saveMethodConfig(methodConfig);
  }
  
  @PostMapping({"/StoreMethodConfigWithPredefinedOutput"})
  public boolean saveMethodConfigWithPredefinedOutput(HttpSession session, @RequestBody MethodConfigWithPredefinedOutput methodConfigWithPredefinedOutput) {
    logger.info("Storing data for" + methodConfigWithPredefinedOutput.getMethodIdentifier());
    MethodConfig methodConfig = new MethodConfig();
    methodConfig.setMethodIdentifier(methodConfigWithPredefinedOutput.getMethodIdentifier());
    methodConfig.setInputAsOutput(methodConfigWithPredefinedOutput.getInputAsOutput());
    methodConfig.setOutputPredefined(methodConfigWithPredefinedOutput.getOutputPredefined());
    methodConfig.setUserToken(methodConfigWithPredefinedOutput.getUserToken());
    boolean status = this.methodConfigService.saveMethodConfig(methodConfig);
    if (!status)
      return status; 
    PredefinedOutput predefinedOutput = new PredefinedOutput();
    predefinedOutput.setMethodIdentifier(methodConfigWithPredefinedOutput.getMethodIdentifier());
    predefinedOutput.setOutputPayload(methodConfigWithPredefinedOutput.getOutputPayload());
    predefinedOutput.setUserToken(methodConfigWithPredefinedOutput.getUserToken());
    status = this.predefinedOutputService.savePredefinedOutput(predefinedOutput);
    if (!status)
      return status; 
    return status;
  }
  
  @PostMapping({"/FindAllMethodConfig"})
  public MethodConfig[] findAllMethodConfig(HttpSession session, @RequestBody String userToken) {
    return this.methodConfigService.findAllMethods(userToken);
  }
  
  @PostMapping({"/FindMethodConfigbyMethodName"})
  public MethodConfig findMethodConfigbyMethodName(HttpSession session, @RequestBody MethodConfig methodConfig) {
    return this.methodConfigService.findMethodConfigbyMethodName(methodConfig.getMethodIdentifier(), methodConfig.getUserToken());
  }
  
  @PostMapping({"/FindMethodConfigWithPredefinedOutput"})
  public MethodConfigWithPredefinedOutput findMethodConfigWithPredefinedOutput(HttpSession session, @RequestBody MethodConfig methodConfig) {
    return this.methodConfigService.findMethodConfigWithPredefinedOutput(methodConfig.getMethodIdentifier(), methodConfig.getUserToken());
  }
  
  @PostMapping({"/RemoveMethodRecordWithPredefinedOutput"})
  public boolean removeMethodRecordWithPredefinedOutput(HttpSession session, @RequestBody MethodConfig methodConfig) {
    try {
      boolean status = this.predefinedOutputService.removeRecord(methodConfig.getMethodIdentifier(), methodConfig.getUserToken()).booleanValue();
      if (!status)
        return false; 
      status = this.methodConfigService.removeRecord(methodConfig.getMethodIdentifier(), methodConfig.getUserToken()).booleanValue();
      if (!status)
        return status; 
      return status;
    } catch (Exception exception) {
      return false;
    } 
  }
  
  @PostMapping({"/RemovePredefinedOutput"})
  public boolean RemovePredefinedOutput(HttpSession session, @RequestBody String methodName, @RequestBody String userId) {
    return this.predefinedOutputService.removeRecord(methodName, userId).booleanValue();
  }
  
  @PostMapping({"/StoreInputPayload"})
  public boolean saveInputPayload(HttpSession session, @RequestBody InputPayload inputpayload) {
    return this.inputPayloadService.saveInputPayload(inputpayload, false);
  }
  
  @PostMapping({"/FindInputPayLoadbyRequestId"})
  public InputPayload findInputPayLoadbyRequestId(HttpSession session, @RequestBody String requestId) {
    return this.inputPayloadService.findInputPayloadByRequestId(requestId);
  }
  
  @PostMapping({"/FindInputWithOutputPayLoadbyRequestId"})
  public InputWithOutputPayload findInputWithOutputbyRequestId(HttpSession session, @RequestBody String requestId) {
    return this.inputPayloadService.findInputWithOutputbyRequestId(requestId);
  }
  
  @PostMapping({"/StorePredefinedOutput"})
  public boolean savePredefinedOutput(HttpSession session, @RequestBody PredefinedOutput predefinedOutputPayload) {
    return this.predefinedOutputService.savePredefinedOutput(predefinedOutputPayload);
  }
  
  @PostMapping({"/StoreOutputPayload"})
  public boolean StoreOutputPayload(HttpSession sesssion, @RequestBody OutputPayload outputPayload) {
    return this.outputPayloadService.StoreOutputPayLoad(outputPayload);
  }
  
  @PostMapping({"/GetPredefinedOutput"})
  public String getPredefinedOutput(HttpSession sesssion, @RequestBody PredefinedOutput predefined) {
    PredefinedOutput predefinedOutput = null;
    logger.info("getPredefinedOutput method called for methodIdentifier as " + predefined.getMethodIdentifier());
    predefinedOutput = this.predefinedOutputService.findOutputPayload(predefined.getMethodIdentifier(), predefined.getUserToken());
    logger.info("predefinedOutput loaded with predefined data as " + predefinedOutput.getOutputPayload());
    String outputData = predefinedOutput.getOutputPayload();
    return outputData;
  }
  
  @PostMapping({"/GetActiveRequests"})
  public ActiveRequestsList GetActiveRequests(HttpSession sesssion, @RequestBody String userToken) {
    logger.info("Get active requests triggered for user " + userToken);
    Map<String, Boolean> temp = (Map<String, Boolean>)this.globalCache.outputCommitted.entrySet().stream().filter(map -> ((String)map.getKey()).startsWith(userToken)).collect(Collectors.toMap(map -> (String)map.getKey(), map -> (Boolean)map.getValue()));
    ActiveRequestsList aqList = new ActiveRequestsList();
    aqList.ActiveRequests = new String[temp.size()];
    for (int i = 0; i < temp.size(); i++)
      aqList.ActiveRequests[i] = temp.keySet().toArray()[i].toString(); 
    return aqList;
  }
  
  @GetMapping({"/GetAllActiveRequests"})
  public ActiveRequestsList GetAllActiveRequests(HttpSession sesssion) {
    Map<String, Boolean> temp = this.globalCache.outputCommitted;
    ActiveRequestsList aqList = new ActiveRequestsList();
    aqList.ActiveRequests = new String[temp.size()];
    for (int i = 0; i < temp.size(); i++)
      aqList.ActiveRequests[i] = temp.keySet().toArray()[i].toString(); 
    return aqList;
  }
  
  @PostMapping({"/InvokeUserExit"})
  public String InvokeUserExit(HttpSession session, @RequestBody InputPayload inputPayload) throws InterruptedException, ParseException {
    int requestCounter = 0;
    logger.info("UserExit Triggered for method " + inputPayload.getMethodIdentifier() + " with requestId " + inputPayload.getRequestId() + " for user " + inputPayload.getUserToken());
    MethodConfig methodConfig = this.methodConfigService.findMethodConfigbyMethodName(inputPayload.getMethodIdentifier(), inputPayload.getUserToken());
    logger.info("Method config for " + inputPayload.getMethodIdentifier() + " for user " + methodConfig.getUserToken() + " retrieved with the following settings, inputAsOutput: " + methodConfig.getInputAsOutput() + " outputPredefined: " + methodConfig.getOutputPredefined());
    if (methodConfig.equals(null)) {
      logger.info("Method not configured for the userId on the User-Exit server");
      return null;
    } 
    String requestId = inputPayload.getRequestId();
    String outputData = null;
    if (methodConfig.getInputAsOutput().booleanValue()) {
      boolean bool = this.inputPayloadService.saveInputPayload(inputPayload, false);
      if (!bool)
        return null; 
      OutputPayload outputPayload = new OutputPayload(requestId, inputPayload.getInputPayload(), inputPayload.getUserToken());
      if (!StoreOutputPayload(session, outputPayload))
        return null; 
      return outputPayload.getOutputPayload();
    } 
    if (methodConfig.getOutputPredefined().booleanValue()) {
      boolean bool = this.inputPayloadService.saveInputPayload(inputPayload, false);
      if (!bool)
        return null; 
      return getPredefinedOutput(session, new PredefinedOutput(inputPayload.getMethodIdentifier(), null, inputPayload.getUserToken()));
    } 
    boolean response = this.inputPayloadService.saveInputPayload(inputPayload, true);
    if (!response)
      return null; 
    this.globalCache.setHTTPRequestCounterInc(requestId);
    requestCounter = this.globalCache.getHTTPRequestCounter(requestId);
    logger.info("HTTP Request Count for " + requestId + "=" + this.globalCache.getHTTPRequestCounter(requestId));
    logger.info("Awiting output payload in loop");
    while (this.globalCache.outputCommitted.get(requestId) == null || !((Boolean)this.globalCache.outputCommitted.get(requestId)).booleanValue()) {
      logger.info("Counter Information: ");
      logger.info("Thread Id: " + Thread.currentThread().getName());
      logger.info("Thresd Request Count: " + requestCounter);
      logger.info("Global Request Count: " + this.globalCache.getHTTPRequestCounter(requestId));
      if (requestCounter < this.globalCache.getHTTPRequestCounter(requestId))
        return null; 
      Date presentDate = new Date();
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String threadpresentTime = dateFormat.format(presentDate);
      System.out.println("Thread Id: " + requestId);
      System.out.println("Thread Id Request Time: " + (String)this.globalCache.requestIdTime.get(requestId));
      System.out.println("Thread Id Present Time: " + threadpresentTime);
      Date d1 = dateFormat.parse((String)this.globalCache.requestIdTime.get(requestId));
      Date d2 = dateFormat.parse(threadpresentTime);
      long diff = d2.getTime() - d1.getTime();
      if (diff >= 3600000L) {
        logger.info("Returning due to Elapsed Tme of 1 Hour");
        this.globalCache.clearCache(requestId);
        return null;
      } 
      System.out.println("Elapsed Time: " + diff);
      Thread.sleep(5000L);
      System.out.println(this.globalCache.outputCommitted.get(requestId));
    } 
    logger.info("Exiting output payload");
    outputData = this.outputPayloadService.FindOutputPayload(requestId, inputPayload.getUserToken()).getOutputPayload();
    logger.info("HTTP Request Count for " + requestId + "=" + this.globalCache.getHTTPRequestCounter(requestId));
    this.globalCache.clearCache(requestId);
    logger.info("Cache cleared for requestId" + requestId);
    return outputData;
  }
}
