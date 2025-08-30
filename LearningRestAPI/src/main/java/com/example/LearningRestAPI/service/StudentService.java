package com.example.LearningRestAPI.service;

import java.util.List;

import com.example.LearningRestAPI.dto.AddStudentDto;
import com.example.LearningRestAPI.dto.StudentDot;

public interface StudentService {
    List<StudentDot> getAllStudent();

    StudentDot getStudentById(Long id);

    StudentDot createNewStudent(AddStudentDto addStudentDto);

}
