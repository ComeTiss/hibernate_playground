package com.example.database_struct.repository;

import com.example.database_struct.model.Property;
import com.example.database_struct.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {}
