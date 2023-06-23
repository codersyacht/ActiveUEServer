package com.cdy.ActiveUserExit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cdy.ActiveUserExit.entity.InputPayload;
import com.cdy.ActiveUserExit.pojo.InputWithOutputPayload;

@Repository
public interface InputPayloadRepository extends JpaRepository<InputPayload, String>
{
  
    @Query("select new com.cdy.ActiveUserExit.pojo.InputWithOutputPayload (in.requestId, in.methodIdentifier, in.inputPayload, op.outputPayload) from InputPayload in, OutputPayload op where in.requestId=op.requestId and in.requestId= ?#{[0]}")
    public InputWithOutputPayload findInputWithOutputbyRequestId(String requestId);
    
}
