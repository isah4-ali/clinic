package com.booking.clinic.service.impl;

import com.booking.clinic.dto.DoctorRequestDto;
import com.booking.clinic.entity.Clinic;
import com.booking.clinic.entity.Doctor;
import com.booking.clinic.entity.Sector;
import com.booking.clinic.repository.ClinicRepository;
import com.booking.clinic.repository.DoctorRepository;
import com.booking.clinic.repository.SectorRepository;
import com.booking.clinic.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final SectorRepository sectorRepository;
    private final ClinicRepository clinicRepository;

    @Override
    public Doctor createDoctor(DoctorRequestDto dto) {
        Sector sector = sectorRepository.findById(dto.getSectorId())
                .orElseThrow(() -> new NoSuchElementException("Sector not found"));

        Clinic clinic = clinicRepository.findById(dto.getClinicId())
                .orElseThrow(() -> new NoSuchElementException("Clinic not found"));

        // Validation: sektorun clinic ilə uyğunluğunu yoxla
        if (!sector.getClinic().getId().equals(clinic.getId())) {
            throw new IllegalArgumentException("Sector does not belong to the specified Clinic");
        }

        Doctor doctor = Doctor.builder()
                .fullName(dto.getFullName())
                .sector(sector)
                .build();

        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Doctor not found"));
    }

    @Override
    public Doctor updateDoctor(Long id, DoctorRequestDto dto) {
        Doctor doctor = getDoctorById(id);

        Sector sector = sectorRepository.findById(dto.getSectorId())
                .orElseThrow(() -> new NoSuchElementException("Sector not found"));

        Clinic clinic = clinicRepository.findById(dto.getClinicId())
                .orElseThrow(() -> new NoSuchElementException("Clinic not found"));

        if (!sector.getClinic().getId().equals(clinic.getId())) {
            throw new IllegalArgumentException("Sector does not belong to the specified Clinic");
        }

        doctor.setFullName(dto.getFullName());
        doctor.setSector(sector);

        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
    @Override
    public List<Doctor> findBySectorId(Long sectorId) {
        return doctorRepository.findBySectorId(sectorId);
    }
}
