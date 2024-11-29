package com.zetalabs.mood.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zetalabs.mood.app.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);  // Note: userName matches your entity field name
}