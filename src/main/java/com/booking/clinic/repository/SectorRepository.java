package com.booking.clinic.repository;

import com.booking.clinic.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectorRepository extends JpaRepository<Sector,Long> {
    List<Sector> findByClinicId(Long clinicId);

}
