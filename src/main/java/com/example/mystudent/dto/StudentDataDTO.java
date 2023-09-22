package com.example.mystudent.dto;

import com.example.mystudent.model.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class StudentDataDTO {

    private String studentId;
    private String name;
    private String grade;

    private List<Address> addressList;
}
