package com.booking.clinic.service.impl;

import com.booking.clinic.dto.AppointmentRequestDto;
import com.booking.clinic.dto.AppointmentResponseDto;
import com.booking.clinic.entity.Appointment;
import com.booking.clinic.entity.Doctor;
import com.booking.clinic.entity.Patient;
import com.booking.clinic.exception.AppointmentException;
import com.booking.clinic.exception.DoctorException;
import com.booking.clinic.repository.AppointmentRepository;
import com.booking.clinic.repository.DoctorRepository;
import com.booking.clinic.repository.PatientRepository;
import com.booking.clinic.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public AppointmentResponseDto createAppointment(AppointmentRequestDto dto) {
        boolean exists = appointmentExists(dto.getDoctorId(), dto.getAppointmentDate(), dto.getAppointmentTime());

        if (exists) {
            throw new AppointmentException("already_exist_appointment");
        }

        Patient patient = Patient.builder()
                .firstName(dto.getPatient().getFirstName())
                .lastName(dto.getPatient().getLastName())
                .fatherName(dto.getPatient().getFatherName())
                .gender(dto.getPatient().getGender())
                .age(dto.getPatient().getAge())
                .mobileNumber(dto.getPatient().getMobileNumber())
                .build();

        Patient savedPatient = patientRepository.save(patient);

        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new DoctorException("Doctor_not_found"));

        Appointment appointment = Appointment.builder()
                .purpose(dto.getPurpose())
                .appointmentDate(dto.getAppointmentDate())
                .appointmentTime(dto.getAppointmentTime())
                .patient(savedPatient)
                .doctor(doctor)
                .build();

        Appointment savedAppointment = appointmentRepository.save(appointment);

        AppointmentResponseDto appointmentResponseDto = new AppointmentResponseDto();
        appointmentResponseDto.setId(savedAppointment.getId());
        appointmentResponseDto.setPurpose(savedAppointment.getPurpose());
        appointmentResponseDto.setAppointmentDate(savedAppointment.getAppointmentDate());
        appointmentResponseDto.setAppointmentTime(savedAppointment.getAppointmentTime());
        appointmentResponseDto.setPatientFullName(savedPatient.getFirstName() + " " + savedPatient.getLastName() + " " + savedPatient.getFatherName());
        appointmentResponseDto.setDoctorFullName(savedAppointment.getDoctor().getFullName());

        return appointmentResponseDto;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentException("appointment_not_found"));
    }

    @Override
    public void deleteAppointment(Long id) {
        Appointment appointment = getAppointmentById(id);
        appointmentRepository.delete(appointment);
    }

    @Override
    public boolean appointmentExists(Long doctorId, LocalDate date, LocalTime time) {
        return appointmentRepository.existsByDoctorIdAndAppointmentDateAndAppointmentTime(doctorId, date, time);
    }
}
