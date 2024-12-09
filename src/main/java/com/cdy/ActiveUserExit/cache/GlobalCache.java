package com.cdy.ActiveUserExit.cache;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class GlobalCache {
  public Map<String, Boolean> outputCommitted = new HashMap<>();
  
  public Map<String, Integer> requestCount = new HashMap<>();
  
  public Map<String, String> requestIdTime = new HashMap<>();
  
  public int getHTTPRequestCounter(String key) {
    if (this.requestCount.get(key) != null)
      return ((Integer)this.requestCount.get(key)).intValue(); 
    System.out.println("Counter is null");
    return 0;
  }
  
  public boolean setHTTPRequestCounterInc(String key) {
    if (this.requestCount.get(key) != null) {
      this.requestCount.put(key, Integer.valueOf(((Integer)this.requestCount.get(key)).intValue() + 1));
    } else {
      this.requestCount.put(key, Integer.valueOf(1));
      Date currentDate = new Date();
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String threadBegionDateTime = dateFormat.format(currentDate);
      this.requestIdTime.put(key, threadBegionDateTime);
    } 
    return true;
  }
  
  public boolean setHTTPRequestCounterDec(String key) {
    if (this.requestCount.get(key) != null) {
      this.requestCount.put(key, Integer.valueOf(((Integer)this.requestCount.get(key)).intValue() - 1));
      return true;
    } 
    return false;
  }
  
  public void clearCache(String requestId) {
    System.out.println("===============Prior to Clear Cache Record for requestId=============");
    for (Map.Entry<String, Boolean> entry : this.outputCommitted.entrySet())
      System.out.println("Key = " + (String)entry.getKey() + ", Value = " + 
          String.valueOf(entry.getValue())); 
    this.requestCount.remove(requestId);
    this.outputCommitted.remove(requestId);
    this.requestIdTime.remove(requestId);
    System.out.println("===============After Clear Cache Record for requestId=============");
    for (Map.Entry<String, Boolean> entry : this.outputCommitted.entrySet())
      System.out.println("Key = " + (String)entry.getKey() + ", Value = " + 
          String.valueOf(entry.getValue())); 
  }
}
