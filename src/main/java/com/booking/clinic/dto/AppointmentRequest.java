package com.booking.clinic.dto;

import com.booking.clinic.enumm.Gender;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentRequest {
    private String firstName;
    private String lastName;
    private String fatherName;
    private Gender gender;
    private Integer age;
    private Long doctorId;
    private String purpose;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
}
