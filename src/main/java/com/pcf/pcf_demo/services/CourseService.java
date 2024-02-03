package com.pcf.pcf_demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcf.pcf_demo.entities.Course;
import com.pcf.pcf_demo.exceptions.ResourceNotFoundException;
import com.pcf.pcf_demo.repositories.CourseRepository;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourse(){
        return this.courseRepository.findAll();
    }

    public Course getCourseById(Integer courseId){
        return this.courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
    }

    public Course createCourse(Course course) {
        this.courseRepository.save(course);
        return course;
    }

    public void deleteCourse(Integer courseID) {
        Course course = this.courseRepository.findById(courseID).orElseThrow(() -> new ResourceNotFoundException("Course", " id", courseID));
        this.courseRepository.delete(course);
    }

    public Course updateCourse(Course course) {
        Course courseToUpdate = this.courseRepository.findById(course.getId()).orElseThrow(() -> new ResourceNotFoundException("Course", "id", course.getId()));

        courseToUpdate.setName(course.getName());
        this.courseRepository.save(courseToUpdate);
        return courseToUpdate;
    }
}
