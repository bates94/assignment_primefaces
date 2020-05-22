package com.example.demo.repositories;

import com.example.demo.models.UserContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContactMessageRepository extends JpaRepository<UserContactMessage,Integer> {

    @Modifying
    @Query("delete from UserContactMessage ucs where ucs.username = :username and ucs.message = :message")
    int deleteMessageForUsername(@Param("username") String username, @Param("message") String message);

}
