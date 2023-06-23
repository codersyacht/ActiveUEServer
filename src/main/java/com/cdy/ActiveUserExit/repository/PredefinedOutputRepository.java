package com.cdy.ActiveUserExit.repository;

import org.springframework.stereotype.Repository;

import com.cdy.ActiveUserExit.entity.PredefinedOutput;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface PredefinedOutputRepository extends JpaRepository <PredefinedOutput, String> 
{
    @Query("select predefinedOutput from  PredefinedOutput predefinedOutput where predefinedOutput.methodIdentifier = ?#{[0]}")
    public PredefinedOutput findPredefinedOutput(String methodindentifier);
    
}
