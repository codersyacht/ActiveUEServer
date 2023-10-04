package com.cdy.ActiveUserExit.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cdy.ActiveUserExit.entity.MethodConfig;
import com.cdy.ActiveUserExit.pojo.MethodConfigWithPredefinedOutput;


@Repository
public interface MethodConfigRepository  extends JpaRepository<MethodConfig, String>
{
   @Query("select mc from  MethodConfig mc where mc.methodIdentifier = ?#{[0]}")
   public MethodConfig findMethodConfigbyMethodName (String methodindentifier);

   @Query("select mc from  MethodConfig mc")
   public MethodConfig[] findAllMethods ();

  
   @Query("select new com.cdy.ActiveUserExit.pojo.MethodConfigWithPredefinedOutput (m.methodIdentifier, m.inputAsOutput, m.outputPredefined, p.outputPayload) from MethodConfig m left outer join PredefinedOutput p on m.methodIdentifier=p.methodIdentifier where m.methodIdentifier= ?#{[0]}")
   public MethodConfigWithPredefinedOutput MethodConfigWithPredefinedOutput(String methodIdentifier);

}
