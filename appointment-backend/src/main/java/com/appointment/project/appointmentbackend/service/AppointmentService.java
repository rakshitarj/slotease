package com.appointment.project.appointmentbackend.service;

import java.util.List;

import com.appointment.project.appointmentbackend.dto.AppointmentRequestDTO;
import com.appointment.project.appointmentbackend.dto.AppointmentResponseDTO;

public interface AppointmentService  {
    AppointmentResponseDTO bookAppointment(AppointmentRequestDTO dto);

    List<AppointmentResponseDTO> getAppointmentsForUser(Long userId);

    List<AppointmentResponseDTO> getAppointmentsForProvider(Long providerId);

    AppointmentResponseDTO updateStatus(Long id, String status);
}