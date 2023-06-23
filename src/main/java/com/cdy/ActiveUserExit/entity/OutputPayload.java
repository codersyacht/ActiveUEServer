package com.cdy.ActiveUserExit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class OutputPayload 
{
    @Id
    @Column(name="requestid")
    String requestId;
    @Column(name="outputpayload")
    String outputPayload;
    
    public OutputPayload(String requestId, String outputPayload)
    {
        this.requestId=requestId;
        this.outputPayload=outputPayload;
    }

    public OutputPayload(){}
}
