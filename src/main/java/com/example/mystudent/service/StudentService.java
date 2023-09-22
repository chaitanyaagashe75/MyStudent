package com.example.mystudent.service;

import com.example.mystudent.dto.StudentDataDTO;
import com.example.mystudent.model.Student;

public interface StudentService {
   public StudentDataDTO addStudent(StudentDataDTO studentDataDTO);

   public StudentDataDTO updateStudent(StudentDataDTO studentDataDTO);

  public StudentDataDTO getStudent(String studentId);
}
