package com.example.mystudent.repository;

import com.example.mystudent.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,String> {

    List<Address> findAllByStudentId(String studentId);


}
