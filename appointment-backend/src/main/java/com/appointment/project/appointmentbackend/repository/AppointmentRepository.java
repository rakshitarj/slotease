package com.appointment.project.appointmentbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appointment.project.appointmentbackend.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	List<Appointment> findByUserUserId(Long userId);
	List<Appointment> findByProviderUserId(Long providerId);

}
