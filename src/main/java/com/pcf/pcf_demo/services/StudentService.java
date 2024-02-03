package com.pcf.pcf_demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcf.pcf_demo.entities.Student;
import com.pcf.pcf_demo.exceptions.ResourceNotFoundException;
import com.pcf.pcf_demo.repositories.StudentRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudent(){
        return this.studentRepository.findAll();
    }

    public Student getStudentById(Integer studentId){
        return this.studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
    }

    public Student createStudent(Student student) {
        this.studentRepository.save(student);
        return student;
    }

    public void deleteStudent(Integer studentId) {
        Student student = this.studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", " id", studentId));
        this.studentRepository.delete(student);
    }

    public Student updateStudent(Student student) {
        Student studentToUpdate = this.studentRepository.findById(student.getId()).orElseThrow(() -> new ResourceNotFoundException("Student", "id", student.getId()));

        studentToUpdate.setName(student.getName());
        this.studentRepository.save(studentToUpdate);
        return studentToUpdate;
    }
}
