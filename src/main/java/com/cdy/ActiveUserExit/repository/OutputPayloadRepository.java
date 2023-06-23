package com.cdy.ActiveUserExit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdy.ActiveUserExit.entity.OutputPayload;

@Repository
public interface OutputPayloadRepository extends JpaRepository<OutputPayload, String>
{
 
    
}
