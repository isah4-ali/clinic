package com.booking.clinic.controller;

import com.booking.clinic.dto.DoctorRequestDto;
import com.booking.clinic.entity.Doctor;
import com.booking.clinic.service.impl.DoctorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorServiceImpl doctorServiceImpl;
    @GetMapping("/{sectorId}")
    public List<Doctor> getDoctorsBySector(@PathVariable Long sectorId) {
        return doctorServiceImpl.findBySectorId(sectorId);
    }
    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody DoctorRequestDto dto) {
        Doctor doctor = doctorServiceImpl.createDoctor(dto);
        return ResponseEntity.status(201).body(doctor);
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorServiceImpl.getAllDoctors());
    }


    @GetMapping("/doctor/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorServiceImpl.getDoctorById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody DoctorRequestDto dto) {
        Doctor updated = doctorServiceImpl.updateDoctor(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorServiceImpl.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
