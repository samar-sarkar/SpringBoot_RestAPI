package com.example.LearningRestAPI.service.serviceImplementation;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.LearningRestAPI.dto.AddStudentDto;
import com.example.LearningRestAPI.dto.StudentDot;
import com.example.LearningRestAPI.entity.Student;
import com.example.LearningRestAPI.repository.StudentRepository;
import com.example.LearningRestAPI.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;


    public StudentServiceImpl(StudentRepository studentRepository , ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }
    

    @Override
    public List<StudentDot> getAllStudent() {
        List<Student> students = studentRepository.findAll();
        // return students.stream()
        //         .map(student -> new StudentDot(student.getId(), student.getName(), student.getEmail()))
        //         .toList();
        return students.stream().map(student -> modelMapper.map(student, StudentDot.class)).toList();
    }

    @Override
    public StudentDot getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with this id " + id));

        StudentDot studentDot = modelMapper.map(student, StudentDot.class);
        return studentDot;    
        // return new StudentDot(student.getId(), student.getName(), student.getEmail());
    }


    @Override
    public StudentDot createNewStudent(AddStudentDto addStudentDto) {
        Student newstudent = modelMapper.map(addStudentDto, Student.class);
        Student student = studentRepository.save(newstudent);
        return modelMapper.map(student, StudentDot.class);
    }


    @Override
    public void deleteStudentById(Long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("Student Does not exist by id"+id);
        }
        studentRepository.deleteById(id);
        
    }

    @Override
    public StudentDot updateStudent(Long id, AddStudentDto addStudentDto) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Student not found with id "+id));
        modelMapper.map(addStudentDto, student); 
        student = studentRepository.save(student);
       return modelMapper.map(student, StudentDot.class);
    }


    @Override
    public StudentDot parcialUpdateStudent(Long id, Map<String, Object> updates) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Student not found with id "+id));
        updates.forEach((field,value)->{
            switch (field) {
                case "name": student.setName((String) value);
                    break;
                case "email": student.setEmail((String) value);
                    break;
            
                default:
                    throw new IllegalArgumentException("Field is not Supported");
            }
        });
        Student saveStudent = studentRepository.save(student);
        return modelMapper.map(saveStudent, StudentDot.class);
    }
    

}
