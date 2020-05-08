package com.example.demo.repositories;

import com.example.demo.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

   UserRole findByUsername(String username);

   @Modifying
   @Query("update UserRole u set u.userRole = ?1 where u.username = ?2")
   int setFixedRole(String role, String username);
}
