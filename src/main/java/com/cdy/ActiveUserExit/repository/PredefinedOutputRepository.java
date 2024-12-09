package com.cdy.ActiveUserExit.repository;

import com.cdy.ActiveUserExit.entity.PredefinedOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PredefinedOutputRepository extends JpaRepository<PredefinedOutput, String> {
  @Query("select predefinedOutput from  PredefinedOutput predefinedOutput where predefinedOutput.methodIdentifier = ?#{[0]} and  predefinedOutput.userToken= ?#{[1]}")
  PredefinedOutput findPredefinedOutput(String paramString1, String paramString2);
}
