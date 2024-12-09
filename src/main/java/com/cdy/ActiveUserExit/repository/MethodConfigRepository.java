package com.cdy.ActiveUserExit.repository;

import com.cdy.ActiveUserExit.entity.MethodConfig;
import com.cdy.ActiveUserExit.pojo.MethodConfigWithPredefinedOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MethodConfigRepository extends JpaRepository<MethodConfig, String> {
  @Query("select mc from  MethodConfig mc where mc.methodIdentifier = ?#{[0]} and mc.userToken=?#{[1]}")
  MethodConfig findMethodConfigbyMethodName(String paramString1, String paramString2);
  
  @Query("select mc from  MethodConfig mc where mc.userToken=?#{[0]}")
  MethodConfig[] findAllMethods(String paramString);
  
  @Query("select new com.cdy.ActiveUserExit.pojo.MethodConfigWithPredefinedOutput (m.methodIdentifier, m.inputAsOutput, m.outputPredefined, p.outputPayload, m.userToken) from MethodConfig m left outer join PredefinedOutput p on m.methodIdentifier=p.methodIdentifier where m.methodIdentifier= ?#{[0]} and m.userToken=?#{[1]}")
  MethodConfigWithPredefinedOutput MethodConfigWithPredefinedOutput(String paramString1, String paramString2);
}
