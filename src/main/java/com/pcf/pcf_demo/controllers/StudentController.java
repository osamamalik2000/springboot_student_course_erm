package com.pcf.pcf_demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcf.pcf_demo.entities.Student;
import com.pcf.pcf_demo.payloads.ApiResponse;
import com.pcf.pcf_demo.services.StudentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/student")

public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public List<Student> getAllStudent() {
        return this.studentService.getAllStudent();
    }

    @GetMapping("/{studentID}")
    public Student getStudentById(@PathVariable Integer studentID) {
        return this.studentService.getStudentById(studentID);
    }

    @GetMapping("/getAllCourse/{studentID}")
    public List getAllCourseByStudentId(@PathVariable Integer studentID) {
        return this.studentService.getAllCourseByStudentId(studentID);
    }

    @PostMapping("")
    public Student createStudent(@RequestBody Student student) {
        return this.studentService.createStudent(student);
    }

    @PutMapping("")
    public Student updateStudent(@RequestBody Student student) {
        return this.studentService.updateStudent(student);
    }

    @DeleteMapping("/{studentID}")
    public ResponseEntity<ApiResponse> updateStudent(@PathVariable Integer studentID) {
        this.studentService.deleteStudent(studentID);
        return ResponseEntity.ok(new ApiResponse("Student deleted successfully", true));
    }
    
    
}
