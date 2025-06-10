package com.booking.clinic.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentRequestDto {
    private String purpose;
    private LocalDate appointmentDate;
    @Schema(type = "string", example = "11:30")
    private LocalTime appointmentTime;
    private PatientDto patient;
    private Long doctorId;
}
