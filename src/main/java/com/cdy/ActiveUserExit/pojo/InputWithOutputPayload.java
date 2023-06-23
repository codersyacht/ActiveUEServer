package com.cdy.ActiveUserExit.pojo;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.Value;

@Data
@Value
public class InputWithOutputPayload 
{
    @Id
    String requestId;
    String methodIdentifier;
    String inputPayload;
    String outputPayload;
    
}
