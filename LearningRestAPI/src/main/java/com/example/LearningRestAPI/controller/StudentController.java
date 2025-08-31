package com.example.LearningRestAPI.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.LearningRestAPI.dto.AddStudentDto;
import com.example.LearningRestAPI.dto.StudentDot;
import com.example.LearningRestAPI.service.StudentService;

import jakarta.validation.Valid;
import lombok.val;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDot>> getAllStudent() {
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDot> getstudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
    @PostMapping
    public ResponseEntity<StudentDot> createNewStudent(@RequestBody @Valid AddStudentDto addStudentDto) {
        
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(addStudentDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentDot> updateStudent(@PathVariable @Valid Long id ,@RequestBody AddStudentDto addStudentDto){
        return ResponseEntity.ok(studentService.updateStudent(id, addStudentDto));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<StudentDot> parcialUpdateStudent(@PathVariable @Valid Long id, @RequestBody Map<String,Object> updates){
        return ResponseEntity.ok(studentService.parcialUpdateStudent(id,updates));
    }
}