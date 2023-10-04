package com.cdy.ActiveUserExit.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class GlobalCache 
{
    public Map < String, Boolean > outputCommitted = new HashMap < > (); 
    public Map <String, Integer> requestCount = new HashMap < >();

   public int getHTTPRequestCounter(String key)
   {
      if ( requestCount.get(key) != null)
      {
       return  requestCount.get(key);
      }
      else
      {
      System.out.println("Counter is null");   
      return 0;
      }
   }

   public boolean setHTTPRequestCounterInc(String key)
   {
      if ( requestCount.get(key) != null)
      {
        requestCount.put(key, requestCount.get(key)+1);
       
      }
      else
      {
         requestCount.put(key, 1);
      }
      return true;
   }
   public boolean setHTTPRequestCounterDec(String key)
   {
      if ( requestCount.get(key) != null)
      {
        requestCount.put(key, requestCount.get(key)-1);
        return true;
      }
      return false;
   }

    public void clearCache(String requestId)
    {
      System.out.println("===============Prior to Clear Cache Record for requestId=============");
        for (Map.Entry<String, Boolean> entry : outputCommitted.entrySet()) 
            System.out.println("Key = " + entry.getKey() +
                             ", Value = " + entry.getValue());

        requestCount.remove(requestId);
        outputCommitted.remove(requestId);
        System.out.println("===============After Clear Cache Record for requestId=============");
        for (Map.Entry<String, Boolean> entry : outputCommitted.entrySet()) 
            System.out.println("Key = " + entry.getKey() +
                             ", Value = " + entry.getValue());
    }

}