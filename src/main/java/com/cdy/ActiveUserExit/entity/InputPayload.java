package com.cdy.ActiveUserExit.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class InputPayload 
{
    @Id
    @Column(name="requestid")
    String requestId;
    @Column(name="methodidentifier")
    String methodIdentifier;
    @Column(name="inputpayload")
    String inputPayload;
    
}
