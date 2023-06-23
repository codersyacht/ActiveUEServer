package com.cdy.ActiveUserExit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="output_predefined")
public class PredefinedOutput
{
    @Id 
    @Column(name="methodidentifier")
    String methodIdentifier;
    @Column(name="outputpayload")
    String outputPayload;
 
    public PredefinedOutput(String methodIdentifier, String outputPayload)
    {
        this.methodIdentifier = methodIdentifier;
        this.outputPayload = outputPayload;
    } 

    public PredefinedOutput()
    {
        
    }
    
}
