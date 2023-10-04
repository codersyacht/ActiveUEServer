package com.cdy.ActiveUserExit.pojo;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Value;

@Data
@Value
public class MethodConfigWithPredefinedOutput 
{
     @Id
    String methodIdentifier;
    Boolean inputAsOutput;
    Boolean outputPredefined;
    String outputPayload;
    
}
