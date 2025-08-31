package com.example.LearningRestAPI.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddStudentDto {

    @NotBlank(message = "Name is Required")
    @Size(min = 3, max = 30 , message = "name length should be between 3 to 30")
    private String name;
    
    @NotBlank(message = "Email is Required")
    @Email
    private String email;

    
}
