package com.aishwarya.studentmanagement.controller;

import com.aishwarya.studentmanagement.model.Course;
import com.aishwarya.studentmanagement.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // 1. Create a course
    @PostMapping
    public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(course));
    }

    // 2. View all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }
}
