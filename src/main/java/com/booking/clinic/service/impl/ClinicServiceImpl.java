package com.booking.clinic.service.impl;

import com.booking.clinic.dto.ClinicRequestDto;
import com.booking.clinic.dto.ClinicResponseDto;
import com.booking.clinic.entity.Clinic;
import com.booking.clinic.repository.ClinicRepository;
import com.booking.clinic.service.ClinicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ClinicServiceImpl implements ClinicService {

    private final ClinicRepository clinicRepository;


    @Override
    public Clinic createClinic(ClinicRequestDto clinicRequestDto) {
        Clinic clinic = Clinic.builder()
                .name(clinicRequestDto.getName())
                .build();
        return clinicRepository.save(clinic);
    }

    @Override
    public List<ClinicResponseDto> getAllClinics() {
        return clinicRepository.findAll()
                .stream()
                .map(clinic -> {
                    ClinicResponseDto dto = new ClinicResponseDto();
                    dto.setId(clinic.getId());
                    dto.setName(clinic.getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Clinic updateClinic(Long id, ClinicRequestDto clinicRequestDto) {
        Clinic clinic = clinicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clinic not found"));
        clinic.setName(clinicRequestDto.getName());
        return clinicRepository.save(clinic);    }

    @Override
    public void deleteClinic(Long id) {
            clinicRepository.deleteById(id);
    }
}
