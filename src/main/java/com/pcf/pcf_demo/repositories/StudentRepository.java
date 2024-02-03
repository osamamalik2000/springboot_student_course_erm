package com.pcf.pcf_demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcf.pcf_demo.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
    
}
