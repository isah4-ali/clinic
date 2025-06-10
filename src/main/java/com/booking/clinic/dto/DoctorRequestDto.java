package com.booking.clinic.dto;

import lombok.Data;

@Data
public class DoctorRequestDto {
    private String fullName;
    private Long sectorId;
    private Long clinicId;
}
