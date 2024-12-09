package com.cdy.ActiveUserExit.repository;

import com.cdy.ActiveUserExit.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {
  @Query("select userDetails from  UserDetails userDetails where userDetails.userId = ?#{[0]}")
  UserDetails getUserDetails(String paramString);
}
