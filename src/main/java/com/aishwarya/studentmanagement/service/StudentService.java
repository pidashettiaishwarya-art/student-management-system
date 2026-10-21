package com.aishwarya.studentmanagement.service;

import com.aishwarya.studentmanagement.dto.StudentRequest;
import com.aishwarya.studentmanagement.exception.DuplicateResourceException;
import com.aishwarya.studentmanagement.exception.ResourceNotFoundException;
import com.aishwarya.studentmanagement.model.Course;
import com.aishwarya.studentmanagement.model.Student;
import com.aishwarya.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseService courseService;

    public StudentService(StudentRepository studentRepository, CourseService courseService) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
    }

    public Student createStudent(StudentRequest request) {
        if (studentRepository.existsByEmailIgnoreCase(request.getEmail())) {
            throw new DuplicateResourceException("Email already registered: " + request.getEmail());
        }
        Course course = courseService.getCourseById(request.getCourseId());
        Student student = new Student(request.getName(), request.getEmail(), course);
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    public Student updateStudent(Long id, StudentRequest request) {
        Student existing = getStudentById(id);

        if (studentRepository.existsByEmailIgnoreCaseAndIdNot(request.getEmail(), id)) {
            throw new DuplicateResourceException("Email already registered: " + request.getEmail());
        }
        Course course = courseService.getCourseById(request.getCourseId());

        existing.setName(request.getName());
        existing.setEmail(request.getEmail());
        existing.setCourse(course);
        return studentRepository.save(existing);
    }

    public void deleteStudent(Long id) {
        Student existing = getStudentById(id);
        studentRepository.delete(existing);
    }

    public List<Student> searchByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Student> getStudentsByCourse(Long courseId) {
        // throws 404 if the course does not exist
        courseService.getCourseById(courseId);
        return studentRepository.findByCourseId(courseId);
    }
}
