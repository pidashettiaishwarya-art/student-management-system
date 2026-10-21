package com.aishwarya.studentmanagement.service;

import com.aishwarya.studentmanagement.exception.DuplicateResourceException;
import com.aishwarya.studentmanagement.exception.ResourceNotFoundException;
import com.aishwarya.studentmanagement.model.Course;
import com.aishwarya.studentmanagement.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course) {
        if (courseRepository.existsByNameIgnoreCase(course.getName())) {
            throw new DuplicateResourceException("Course already exists: " + course.getName());
        }
        course.setId(null);
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }
}
