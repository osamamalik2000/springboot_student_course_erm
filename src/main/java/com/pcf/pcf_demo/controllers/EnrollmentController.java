package com.pcf.pcf_demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcf.pcf_demo.entities.Enrollment;
import com.pcf.pcf_demo.payloads.ApiResponse;
import com.pcf.pcf_demo.services.EnrollmentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/Enrollment")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("")
    public List<Enrollment> getAllEnrollment() {
        return this.enrollmentService.getAllEnrollment();
    }

    @GetMapping("/{enrollmentID}")
    public Enrollment getEnrollmentById(@PathVariable Integer enrollmentID) {
        return this.enrollmentService.getEnrollmentById(enrollmentID);
    }

    @PostMapping("")
    public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {
        return this.enrollmentService.createEnrollment(enrollment);
    }

    @DeleteMapping("/{enrollmentID}")
    public ResponseEntity<ApiResponse> updateEnrollment(@PathVariable Integer enrollmentID) {
        this.enrollmentService.deleteEnrollment(enrollmentID);
        return ResponseEntity.ok(new ApiResponse("Enrollment deleted successfully", true));
    }
}

