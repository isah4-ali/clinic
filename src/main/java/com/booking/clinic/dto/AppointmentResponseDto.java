package com.booking.clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponseDto {
    private Long id;
    private String purpose;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String patientFullName;
    private String doctorFullName;
}
