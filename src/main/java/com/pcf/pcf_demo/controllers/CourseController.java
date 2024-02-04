package com.pcf.pcf_demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcf.pcf_demo.entities.Course;
import com.pcf.pcf_demo.payloads.ApiResponse;
import com.pcf.pcf_demo.services.CourseService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("")
    public List<Course> getAllCourse() {
        return this.courseService.getAllCourse();
    }

    @GetMapping("/{courseID}")
    public Course getCourseById(@PathVariable Integer courseID) {
        return this.courseService.getCourseById(courseID);
    }

    @GetMapping("/getAllStudent/{courseID}")
    public List getAllStudentByCourseId(@PathVariable Integer courseID) {
        return this.courseService.getAllStudentByCourseId(courseID);
    }

    @PostMapping("")
    public Course createCourse(@RequestBody Course course) {
        return this.courseService.createCourse(course);
    }

    @PutMapping("")
    public Course updateCourse(@RequestBody Course course) {
        return this.courseService.updateCourse(course);
    }

    @DeleteMapping("/{courseID}")
    public ResponseEntity<ApiResponse> updateCourse(@PathVariable Integer courseID) {
        this.courseService.deleteCourse(courseID);
        return ResponseEntity.ok(new ApiResponse("Course deleted successfully", true));
    }
    
    
}
