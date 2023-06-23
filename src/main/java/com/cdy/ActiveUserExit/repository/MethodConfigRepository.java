package com.cdy.ActiveUserExit.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cdy.ActiveUserExit.entity.MethodConfig;

@Repository
public interface MethodConfigRepository  extends JpaRepository<MethodConfig, String>

{
    @Query("select mc from  MethodConfig mc where mc.methodIdentifier = ?#{[0]}")
    public MethodConfig findMethodConfigbyMethodName (String methodindentifier);

}
