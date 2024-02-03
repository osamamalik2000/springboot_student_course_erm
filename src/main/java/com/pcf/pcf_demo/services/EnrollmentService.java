package com.pcf.pcf_demo.services;

import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pcf.pcf_demo.entities.Enrollment;
import com.pcf.pcf_demo.exceptions.ResourceNotFoundException;
import com.pcf.pcf_demo.repositories.EnrollmentRepository;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<Enrollment> getAllEnrollment(){
        return this.enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(Integer enrollmentID){
        return this.enrollmentRepository.findById(enrollmentID).orElseThrow(() -> new ResourceNotFoundException("Enrollment", "id", enrollmentID));
    }

    public Enrollment createEnrollment(Enrollment enrollment) {
        this.enrollmentRepository.save(enrollment);
        return enrollment;
    }

    public void deleteEnrollment(Integer enrollmentID) {
        Enrollment enrollment = this.enrollmentRepository.findById(enrollmentID).orElseThrow(() -> new ResourceNotFoundException("Enrollment", " id", enrollmentID));
        this.enrollmentRepository.delete(enrollment);
    }
}

