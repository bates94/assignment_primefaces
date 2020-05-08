package com.example.demo.repositories;

import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.username = :userName")
    User findByUserName(@Param("userName") String userName);

    @Query("select u from User u where u.username =:userName and u.password =:password")
    User findUser(@Param("userName") String userName, @Param("password") String password);

    @Query(value = "select username from assignment_users", nativeQuery = true)
    List<String> findAllUserName();



}
