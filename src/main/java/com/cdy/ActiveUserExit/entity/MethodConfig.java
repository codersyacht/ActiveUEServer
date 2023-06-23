package com.cdy.ActiveUserExit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="method_cofig")
public class MethodConfig
{
 
    @Id
    @Column(name="methodidentifier")
    String methodIdentifier;
    @Column(name="inputasoutput")
    Boolean inputAsOutput;
    @Column(name="outputpredefined")
    Boolean outputPredefined;
}
