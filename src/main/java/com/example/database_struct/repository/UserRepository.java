package com.example.database_struct.repository;

import com.example.database_struct.model.Customer;
import com.example.database_struct.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Note: 'FETCH' is important here
    // Initialize the properties list
    // In case the user has not 'likes' yet
    @Query("SELECT u FROM User u " +
            "LEFT JOIN FETCH u.properties liked_properties")
    List<User> findAllWithProperties();
}
