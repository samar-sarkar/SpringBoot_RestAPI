package com.example.LearningRestAPI.service.serviceImplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.LearningRestAPI.dto.StudentDot;
import com.example.LearningRestAPI.entity.Student;
import com.example.LearningRestAPI.repository.StudentRepository;
import com.example.LearningRestAPI.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDot> getAllStudent() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> new StudentDot(student.getId(), student.getName(), student.getEmail()))
                .toList();
    }

    @Override
    public StudentDot getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with this id " + id));

        return new StudentDot(student.getId(), student.getName(), student.getEmail());
    }

}
