package com.appointment.project.appointmentbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.project.appointmentbackend.dto.AppointmentRequestDTO;
import com.appointment.project.appointmentbackend.dto.AppointmentResponseDTO;
import com.appointment.project.appointmentbackend.service.AppointmentService;
import com.appointment.project.appointmentbackend.service.UserService;

@RestController
@RequestMapping("/api/appointment")
@CrossOrigin
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // 1. Book Appointment
    @PostMapping("/book")
    public AppointmentResponseDTO book(@RequestBody AppointmentRequestDTO dto) {
        return appointmentService.bookAppointment(dto);
    }

    // 2. Get Appointments of a User
    @GetMapping("/user/{userId}")
    public List<AppointmentResponseDTO> getUserAppointments(@PathVariable Long userId) {
        return appointmentService.getAppointmentsForUser(userId);
    }

    // 3. Get appointments for a Provider
    @GetMapping("/provider/{providerId}")
    public List<AppointmentResponseDTO> getProviderAppointments(@PathVariable Long providerId) {
        return appointmentService.getAppointmentsForProvider(providerId);
    }

    // 4. Update Appointment Status (cancel/approve/reject)
    @PutMapping("/status/{id}")
    public AppointmentResponseDTO updateStatus(@PathVariable Long id,
                                               @RequestParam String status) {
        return appointmentService.updateStatus(id, status);
    }
}
