package com.pcf.pcf_demo.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcf.pcf_demo.entities.Course;
import com.pcf.pcf_demo.entities.Enrollment;
import com.pcf.pcf_demo.entities.Student;
import com.pcf.pcf_demo.payloads.ApiResponse;
import com.pcf.pcf_demo.services.CourseService;
import com.pcf.pcf_demo.services.EnrollmentService;
import com.pcf.pcf_demo.services.StudentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public List getAllEnrollment() {
        // Get all enrollmentsStored
        List<Enrollment> enrollmentList = this.enrollmentService.getAllEnrollment();

        List<Map<String, Object>> responseList = new ArrayList<>();
        // Get an iterator for the list
        Iterator<Enrollment> iterator = enrollmentList.iterator();

        // Use the iterator to traverse the elements
        while (iterator.hasNext()) {
            Enrollment element = iterator.next();
            if(element.getStudentId() != null && element.getCourseId() != null){
                Student student = this.studentService.getStudentById(element.getStudentId());
                Course course = this.courseService.getCourseById(element.getCourseId());
                
                // Create a Map to represent the response
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("id", element.getId());
                responseMap.put("student", student);
                responseMap.put("course", course);
           
                // Add the response to the list
                responseList.add(responseMap);
            }
        }
        return responseList;
    }

    @GetMapping("/{enrollmentID}")
    public Map<String, Object> getEnrollmentById(@PathVariable Integer enrollmentID) {
        Enrollment enrollment = this.enrollmentService.getEnrollmentById(enrollmentID);

        Student student = this.studentService.getStudentById(enrollment.getStudentId());
        Course course = this.courseService.getCourseById(enrollment.getCourseId());
        
        // Create a Map to represent the response
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("id", enrollment.getId());
        responseMap.put("student", student);
        responseMap.put("course", course);
    
        return responseMap;
    }

    @PostMapping("")
    public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {
        return this.enrollmentService.createEnrollment(enrollment);
    }

    @DeleteMapping("/{enrollmentID}")
    public ResponseEntity<ApiResponse> deleteEnrollment(@PathVariable Integer enrollmentID) {
        this.enrollmentService.deleteEnrollment(enrollmentID);
        return ResponseEntity.ok(new ApiResponse("Enrollment deleted successfully", true));
    }
}

