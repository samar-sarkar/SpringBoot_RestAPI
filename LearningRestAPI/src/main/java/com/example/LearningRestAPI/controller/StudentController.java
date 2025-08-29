package com.example.LearningRestAPI.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.LearningRestAPI.dto.StudentDot;
import com.example.LearningRestAPI.service.StudentService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController

public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<StudentDot> getAllStudent() {
        return studentService.getAllStudent();
    }

    @GetMapping("/students/{id}")
    public StudentDot getstudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

}
