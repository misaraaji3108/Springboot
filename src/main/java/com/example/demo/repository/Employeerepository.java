package com.example.demo.repository;

import com.example.demo.entities.employeeentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Employeerepository extends JpaRepository<employeeentity, Long> {

}

