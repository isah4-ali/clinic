package com.booking.clinic.exception;

import com.booking.clinic.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(AppointmentException.class)
    public ResponseEntity<ErrorResponseDto> handleAppointmentNotFoundException(AppointmentException ex) {
        String message = ex.getMessage().equals("already_exist_appointment")
                ? "Bu saat üçün həkimin artıq rezervasiyası var"
                : "Belə rezervasiya yoxdur";
        ErrorResponseDto errorResponseDto = new ErrorResponseDto("FAILED",ex.getMessage(), message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    @ExceptionHandler(DoctorException.class)
    public ResponseEntity<ErrorResponseDto> handleDoctorNotFoundException(DoctorException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto("FAILED",ex.getMessage(),"Belə həkim yoxdur");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }
    @ExceptionHandler(PatientException.class)
    public ResponseEntity<ErrorResponseDto> handlePatientNotFoundException(PatientException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto("FAILED",ex.getMessage(),"Belə pasient yoxdur");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

}
