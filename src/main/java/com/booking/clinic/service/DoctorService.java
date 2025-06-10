package com.booking.clinic.service;

import com.booking.clinic.dto.DoctorRequestDto;
import com.booking.clinic.entity.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> findBySectorId(Long sectorId);
    Doctor createDoctor(DoctorRequestDto doctorRequestDto);
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(Long id);
    Doctor updateDoctor(Long id, DoctorRequestDto doctorRequestDto);
    void deleteDoctor(Long id);

}
