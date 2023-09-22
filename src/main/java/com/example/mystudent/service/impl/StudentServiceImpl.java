package com.example.mystudent.service.impl;

import com.example.mystudent.dto.StudentDataDTO;
import com.example.mystudent.model.Address;
import com.example.mystudent.model.Student;
import com.example.mystudent.repository.AddressRepository;
import com.example.mystudent.repository.StudentRepository;
import com.example.mystudent.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public StudentDataDTO addStudent(StudentDataDTO studentDataDTO) {

        String studentId = UUID.randomUUID().toString();

        Student student1 = new Student();
        student1.setStudentId(studentId);
        student1.setName(studentDataDTO.getName());
        student1.setGrade(studentDataDTO.getGrade());
        Student savedsStudent = studentRepository.save(student1);

        for (Address address : studentDataDTO.getAddressList()) {
            address.setStudentId(studentId);
            String addressId = UUID.randomUUID().toString();
            address.setAddressId(addressId);
            //addressRepository.save(address);
        }
        List<Address> addresses = addressRepository.saveAll(studentDataDTO.getAddressList());

        studentDataDTO.setStudentId(savedsStudent.getStudentId());
        studentDataDTO.setName(savedsStudent.getName());
        studentDataDTO.setGrade(savedsStudent.getGrade());
        studentDataDTO.setAddressList(addresses);

        return studentDataDTO;
    }

    @Override
    public StudentDataDTO updateStudent(StudentDataDTO studentDataDTO) {
        Optional<Student> OptExisting = studentRepository.findById(studentDataDTO.getStudentId());
        if (OptExisting.isPresent()) {
            Student existingStudent = OptExisting.get();

            existingStudent.setName(studentDataDTO.getName());
            existingStudent.setGrade(studentDataDTO.getGrade());

            List<Address> updatedAddresses = new ArrayList<>();

            for (Address updatedAddress : studentDataDTO.getAddressList()) {
                List<Address> OptExistingAddress = addressRepository.findAllByStudentId(studentDataDTO.getStudentId());
                if (!OptExistingAddress.isEmpty()) {
                    Address existingAddress = OptExistingAddress.get();
                    existingAddress.setLandmark(updatedAddress.getLandmark());
                    existingAddress.setCity(updatedAddress.getCity());

                    updatedAddresses.add(existingAddress);
                }
            }
            addressRepository.saveAll(studentDataDTO.getAddressList());
            studentRepository.save(existingStudent);

            studentDataDTO.setStudentId(studentDataDTO.getStudentId());

            return studentDataDTO;

        }
        return null;
    }



    @Override
    public StudentDataDTO getStudent(String studentId) {
        Optional<Student> OptExistedStudent = studentRepository.findById(studentId);
        if(OptExistedStudent.isPresent()){
            Student ExistingStudent = OptExistedStudent.get();
            studentRepository.save(OptExistedStudent.get());

           List<Address> addresses = addressRepository.findAllByStudentId(studentId);

           StudentDataDTO studentDataDTO =new StudentDataDTO();
            studentDataDTO.setStudentId(ExistingStudent.getStudentId());
            studentDataDTO.setName(ExistingStudent.getName());
            studentDataDTO.setGrade(ExistingStudent.getGrade());
            studentDataDTO.setAddressList(addresses);

            return studentDataDTO;
        }else {
            log.info("Student not found");
            return null;
        }
    }
}

//the update API implementation is going wrong 