package com.booking.clinic.service;

import com.booking.clinic.dto.AppointmentRequestDto;
import com.booking.clinic.dto.AppointmentResponseDto;
import com.booking.clinic.entity.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentService {
    AppointmentResponseDto createAppointment(AppointmentRequestDto dto);
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(Long id);
    void deleteAppointment(Long id);
    boolean appointmentExists(Long doctorId, LocalDate date, LocalTime time);

}
