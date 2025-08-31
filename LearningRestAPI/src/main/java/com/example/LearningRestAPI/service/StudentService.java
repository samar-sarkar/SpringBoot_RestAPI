package com.example.LearningRestAPI.service;

import java.util.List;
import java.util.Map;

import com.example.LearningRestAPI.dto.AddStudentDto;
import com.example.LearningRestAPI.dto.StudentDot;

public interface StudentService {
    List<StudentDot> getAllStudent();

    StudentDot getStudentById(Long id);

    StudentDot createNewStudent(AddStudentDto addStudentDto);

    void deleteStudentById(Long id);

    StudentDot updateStudent(Long id, AddStudentDto addStudentDto);

    StudentDot parcialUpdateStudent(Long id, Map<String,Object> updates);
}
