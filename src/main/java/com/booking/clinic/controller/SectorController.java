package com.booking.clinic.controller;

import com.booking.clinic.dto.ResponseDto;
import com.booking.clinic.dto.SectorRequestDto;
import com.booking.clinic.entity.Sector;
import com.booking.clinic.service.impl.SectorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sectors")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class SectorController {
    private final SectorServiceImpl sectorServiceImpl;

    @PostMapping
    public ResponseEntity<ResponseDto> createSector(@RequestBody SectorRequestDto sectorRequestDto) {
        sectorServiceImpl.createSector(sectorRequestDto);
        return ResponseEntity.ok(new ResponseDto("SUCCESS", "Uğurla yaradıldı", "success_creating_sector"));
    }

    @GetMapping
    public ResponseEntity<List<Sector>> getAllSectors() {
        return ResponseEntity.ok(sectorServiceImpl.getAllSectors());
    }

    @GetMapping("/{clinicId}")
    public ResponseEntity<List<Sector>> getSectorsByClinic(@PathVariable Long clinicId) {
        return ResponseEntity.ok(sectorServiceImpl.getSectorsByClinicId(clinicId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sector> updateSector(@PathVariable Long id, @RequestBody Sector updated) {
        return ResponseEntity.ok(sectorServiceImpl.updateSector(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSector(@PathVariable Long id) {
        sectorServiceImpl.deleteSector(id);
        return ResponseEntity.noContent().build();
    }
}
