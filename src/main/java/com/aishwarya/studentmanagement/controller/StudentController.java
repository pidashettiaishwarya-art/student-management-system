package com.aishwarya.studentmanagement.controller;

import com.aishwarya.studentmanagement.dto.StudentRequest;
import com.aishwarya.studentmanagement.model.Student;
import com.aishwarya.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 3. Add a student
    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody StudentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(request));
    }

    // 4. View all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // 5. View a student by ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // 6. Update a student
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequest request) {
        return studentService.updateStudent(id, request);
    }

    // 7. Delete a student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // 8. Search students by name (partial, case-insensitive)
    @GetMapping("/search")
    public List<Student> searchByName(@RequestParam String name) {
        return studentService.searchByName(name);
    }

    // 9. List students enrolled in a course
    @GetMapping("/course/{courseId}")
    public List<Student> getStudentsByCourse(@PathVariable Long courseId) {
        return studentService.getStudentsByCourse(courseId);
    }
}
