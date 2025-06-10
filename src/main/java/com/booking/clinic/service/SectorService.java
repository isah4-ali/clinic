package com.booking.clinic.service;

import com.booking.clinic.dto.SectorRequestDto;
import com.booking.clinic.entity.Sector;

import java.util.List;
import java.util.Optional;

public interface SectorService {
    List<Sector> getAllSectors();
    List<Sector> getSectorsByClinicId(Long clinicId);
    Optional<Sector> getSectorById(Long id);

    Sector createSector(SectorRequestDto sectorRequestDto);

    Sector updateSector(Long id, Sector sector);
    void deleteSector(Long id);

}
