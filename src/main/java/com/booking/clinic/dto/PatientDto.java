package com.booking.clinic.dto;

import com.booking.clinic.enumm.Gender;
import lombok.Data;

@Data
public class PatientDto {
    private String firstName;
    private String lastName;
    private String fatherName;
    private Gender   gender;
    private Integer age;
    private String mobileNumber;
}
