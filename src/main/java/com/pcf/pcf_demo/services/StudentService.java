package com.pcf.pcf_demo.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcf.pcf_demo.entities.Course;
import com.pcf.pcf_demo.entities.Enrollment;
import com.pcf.pcf_demo.entities.Student;
import com.pcf.pcf_demo.exceptions.ResourceNotFoundException;
import com.pcf.pcf_demo.repositories.CourseRepository;
import com.pcf.pcf_demo.repositories.EnrollmentRepository;
import com.pcf.pcf_demo.repositories.StudentRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EnrollmentRepository enrollRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Student> getAllStudent(){
        return this.studentRepository.findAll();
    }

    public Student getStudentById(Integer studentId){
        return this.studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
    }

    public List getAllCourseByStudentId(Integer studentId) {
        List<Enrollment> enrollmentList = this.enrollRepository.findAll();
        // Get an iterator for the list
        Iterator<Enrollment> iterator = enrollmentList.iterator();

        List responseList = new ArrayList<>();

        // Use the iterator to traverse the elements
        while (iterator.hasNext()) {
            Enrollment element = iterator.next();
            if (element.getStudentId() == studentId) {
                Optional<Course> course = this.courseRepository.findById(element.getCourseId());

                if (course.isPresent()) {
                    responseList.add(course);
                }
            }
        }
        return responseList;
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

    public void deleteStudentFromCourse(Integer studentID, Integer courseID) {
        List<Enrollment> enrollmentList = this.enrollRepository.findAll();
        // Get an iterator for the list
        Iterator<Enrollment> iterator = enrollmentList.iterator();

        // Use the iterator to traverse the elements
        while (iterator.hasNext()) {
            Enrollment element = iterator.next();
            if (element.getStudentId() == studentID && element.getCourseId() == courseID) {
                this.enrollRepository.delete(element);
            }
        }
    }
}
