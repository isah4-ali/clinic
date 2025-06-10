package com.booking.clinic.controller;

import com.booking.clinic.dto.ClinicRequestDto;
import com.booking.clinic.dto.ClinicResponseDto;
import com.booking.clinic.dto.ResponseDto;
import com.booking.clinic.entity.Clinic;
import com.booking.clinic.service.impl.ClinicServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clinics")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClinicController {
    private final ClinicServiceImpl clinicServiceImpl;

    @PostMapping
    public ResponseEntity<ResponseDto> createClinic(@RequestBody ClinicRequestDto clinicRequestDto) {
        clinicServiceImpl.createClinic(clinicRequestDto);
        return ResponseEntity.status(201).body(new ResponseDto("SUCCESS","Uğurla yaradıldı","success_creating_clinic"));
    }

    @GetMapping
    public ResponseEntity<List<ClinicResponseDto>> getAllClinics() {
        return ResponseEntity.ok(clinicServiceImpl.getAllClinics());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateClinic(@PathVariable Long id, @RequestBody ClinicRequestDto clinicRequestDto) {
        clinicServiceImpl.updateClinic( id,clinicRequestDto);
        return ResponseEntity.ok(new ResponseDto("SUCCESS","Uğurla dəyişdirildi","updated_clinic_data"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinic(@PathVariable Long id) {
        clinicServiceImpl.deleteClinic(id);
        return ResponseEntity.noContent().build();
    }


}
