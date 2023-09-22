package com.example.mystudent.controller;

import com.example.mystudent.dto.StudentDataDTO;
import com.example.mystudent.model.Student;
import com.example.mystudent.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public StudentDataDTO addStudent(@RequestBody StudentDataDTO studentDataDTO){
        return studentService.addStudent(studentDataDTO);
    }

    @PutMapping("/update")
    public StudentDataDTO updateStudent(@RequestBody StudentDataDTO studentDataDTO){
        return studentService.updateStudent(studentDataDTO);
    }

    @GetMapping("/get/{studentId}")
    public StudentDataDTO getStudent(@PathVariable String studentId){
        return studentService.getStudent(studentId);
    }


}
