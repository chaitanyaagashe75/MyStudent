package com.example.mystudent.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {
   @Id
   private String addressId;
   private String landmark;
   private String city;

    @Column(nullable =false )
    private String studentId;
}
