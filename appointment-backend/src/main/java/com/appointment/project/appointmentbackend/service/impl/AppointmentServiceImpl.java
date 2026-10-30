package com.appointment.project.appointmentbackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appointment.project.appointmentbackend.dto.AppointmentRequestDTO;
import com.appointment.project.appointmentbackend.dto.AppointmentResponseDTO;
import com.appointment.project.appointmentbackend.entity.Appointment;
import com.appointment.project.appointmentbackend.entity.User;
import com.appointment.project.appointmentbackend.repository.AppointmentRepository;
import com.appointment.project.appointmentbackend.repository.UserRepository;
import com.appointment.project.appointmentbackend.service.AppointmentService;
@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Autowired
    private UserRepository userRepo;


	@Override
	public AppointmentResponseDTO bookAppointment(AppointmentRequestDTO dto)  {

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        User provider = userRepo.findById(dto.getProviderId())
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        if (!"PROVIDER".equals(provider.getRole())) {
            throw new RuntimeException("Selected user is not a provider");
        }

        // DTO → Entity
        Appointment appt = new Appointment();
        appt.setUser(user);
        appt.setProvider(provider);
        appt.setAppointmentDate(dto.getAppointmentDate());
        appt.setAppointmentTime(dto.getAppointmentTime());
        appt.setStatus("PENDING");

        Appointment saved = appointmentRepo.save(appt);

        // Entity → ResponseDTO
        AppointmentResponseDTO res = new AppointmentResponseDTO();
        res.setAppointmentId(saved.getAppointmentId());
        res.setAppointmentDate(saved.getAppointmentDate());
        res.setAppointmentTime(saved.getAppointmentTime());
        res.setStatus(saved.getStatus());
        res.setUserId(user.getUserId());
        res.setUserName(user.getName());
        res.setProviderId(provider.getUserId());
        res.setProviderName(provider.getName());

        return res;
    }


	@Override
	public List<AppointmentResponseDTO> getAppointmentsForUser(Long userId) {
		  List<Appointment> list = appointmentRepo.findByUserUserId(userId);

	        return list.stream().map(a -> {
	            AppointmentResponseDTO dto = new AppointmentResponseDTO();
	            dto.setAppointmentId(a.getAppointmentId());
	            dto.setAppointmentDate(a.getAppointmentDate());
	            dto.setAppointmentTime(a.getAppointmentTime());
	            dto.setStatus(a.getStatus());
	            dto.setUserId(a.getUser().getUserId());
	            dto.setUserName(a.getUser().getName());
	            dto.setProviderId(a.getProvider().getUserId());
	            dto.setProviderName(a.getProvider().getName());
	            return dto;
	        }).toList();
	    }	

	@Override
	public List<AppointmentResponseDTO> getAppointmentsForProvider(Long providerId) {
		 User provider = userRepo.findById(providerId)
	                .orElseThrow(() -> new RuntimeException("Provider not found"));

	        if (!"PROVIDER".equals(provider.getRole())) {
	            throw new RuntimeException("User is not a provider");
	        }

	        List<Appointment> list = appointmentRepo.findByProviderUserId(providerId);

	        return list.stream().map(a -> {
	            AppointmentResponseDTO dto = new AppointmentResponseDTO();
	            dto.setAppointmentId(a.getAppointmentId());
	            dto.setAppointmentDate(a.getAppointmentDate());
	            dto.setAppointmentTime(a.getAppointmentTime());
	            dto.setStatus(a.getStatus());
	            dto.setUserId(a.getUser().getUserId());
	            dto.setUserName(a.getUser().getName());
	            dto.setProviderId(a.getProvider().getUserId());
	            dto.setProviderName(a.getProvider().getName());
	            return dto;
	        }).toList();
	}

	@Override
	public AppointmentResponseDTO updateStatus(Long id, String status) {
		  Appointment appt = appointmentRepo.findById(id)
	                .orElseThrow(() -> new RuntimeException("Appointment not found"));

	        appt.setStatus(status);
	        Appointment saved = appointmentRepo.save(appt);

	        AppointmentResponseDTO dto = new AppointmentResponseDTO();
	        dto.setAppointmentId(saved.getAppointmentId());
	        dto.setAppointmentDate(saved.getAppointmentDate());
	        dto.setAppointmentTime(saved.getAppointmentTime());
	        dto.setStatus(saved.getStatus());
	        dto.setUserId(saved.getUser().getUserId());
	        dto.setUserName(saved.getUser().getName());
	        dto.setProviderId(saved.getProvider().getUserId());
	        dto.setProviderName(saved.getProvider().getName());

	        return dto;
	    }
	}


