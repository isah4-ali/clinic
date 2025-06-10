package com.booking.clinic.controller;

import com.booking.clinic.dto.AppointmentRequestDto;
import com.booking.clinic.dto.AppointmentResponseDto;
import com.booking.clinic.entity.*;
import com.booking.clinic.service.impl.AppointmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class AppointmentController {

    private final AppointmentServiceImpl appointmentServiceImpl;
    @PostMapping
    public ResponseEntity<AppointmentResponseDto> createAppointment(@RequestBody AppointmentRequestDto dto) {
        AppointmentResponseDto response = appointmentServiceImpl.createAppointment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentServiceImpl.getAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentServiceImpl.getAppointmentById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentServiceImpl.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
