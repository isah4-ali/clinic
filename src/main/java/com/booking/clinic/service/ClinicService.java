package com.booking.clinic.service;

import com.booking.clinic.dto.ClinicRequestDto;
import com.booking.clinic.dto.ClinicResponseDto;
import com.booking.clinic.entity.Clinic;

import java.util.List;

public interface ClinicService {
    Clinic createClinic(ClinicRequestDto dto);
    List<ClinicResponseDto> getAllClinics();
    Clinic updateClinic(Long id, ClinicRequestDto dto);
    void deleteClinic(Long id);
}
