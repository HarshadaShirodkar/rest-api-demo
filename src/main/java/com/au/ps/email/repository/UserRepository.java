package com.au.ps.email.repository;

import com.au.ps.email.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT * FROM User u WHERE " +
            "u.name LIKE CONCAT('%',:query, '%')")
    List<UserEntity> getEmailByName(String name);

    @Query("SELECT * FROM User u")
    List<UserEntity> getAllEmails();

}
