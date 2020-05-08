package com.example.demo.repositories;

import com.example.demo.models.UserContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContactMessageRepository extends JpaRepository<UserContactMessage,Integer> {
}
