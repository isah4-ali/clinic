package com.booking.clinic.service.impl;

import com.booking.clinic.dto.SectorRequestDto;
import com.booking.clinic.entity.Clinic;
import com.booking.clinic.entity.Sector;
import com.booking.clinic.repository.ClinicRepository;
import com.booking.clinic.repository.SectorRepository;
import com.booking.clinic.service.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SectorServiceImpl implements SectorService {
    private final SectorRepository sectorRepository;
    private final ClinicRepository clinicRepository;

    @Override
    public List<Sector> getAllSectors() {
        return sectorRepository.findAll();
    }

    @Override
    public List<Sector> getSectorsByClinicId(Long clinicId) {
        return sectorRepository.findByClinicId(clinicId);
    }

    @Override
    public Optional<Sector> getSectorById(Long id) {
        return sectorRepository.findById(id);
    }

    @Override
    public Sector createSector(SectorRequestDto sectorRequestDto) {
        Clinic clinic = clinicRepository.findById(sectorRequestDto.getClinicId())
                .orElseThrow(() -> new RuntimeException("Clinic not found"));

        Sector sector = Sector.builder()
                .name(sectorRequestDto.getName())
                .clinic(clinic) // <-- Düzgün əlaqə burada qurulur
                .build();

        return sectorRepository.save(sector);
    }

    @Override
    public Sector updateSector(Long id, Sector sector) {
        return sectorRepository.findById(id).map(existingSector -> {
            existingSector.setName(sector.getName());
            existingSector.setClinic(sector.getClinic());
            return sectorRepository.save(existingSector);
        }).orElseThrow(() -> new RuntimeException("Sector not found"));
    }

    @Override
    public void deleteSector(Long id) {
        sectorRepository.deleteById(id);
    }
}
