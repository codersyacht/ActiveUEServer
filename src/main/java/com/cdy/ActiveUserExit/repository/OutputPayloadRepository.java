package com.cdy.ActiveUserExit.repository;

import com.cdy.ActiveUserExit.entity.OutputPayload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputPayloadRepository extends JpaRepository<OutputPayload, String> {
  @Query("select op from  OutputPayload op where op.requestId = ?#{[0]} and op.userToken=?#{[1]}")
  OutputPayload findOutputPayload(String paramString1, String paramString2);
}
