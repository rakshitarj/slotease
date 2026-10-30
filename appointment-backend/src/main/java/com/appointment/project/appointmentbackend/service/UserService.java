package com.appointment.project.appointmentbackend.service;

import java.util.List;

import com.appointment.project.appointmentbackend.dto.CategoryResponseDTO;
import com.appointment.project.appointmentbackend.dto.LoginDTO;
import com.appointment.project.appointmentbackend.dto.ProviderResponseDTO;
import com.appointment.project.appointmentbackend.dto.UserRegisterDTO;
import com.appointment.project.appointmentbackend.dto.UserResponseDTO;

public interface UserService {
	
	  UserResponseDTO registerProvider(UserRegisterDTO dto);

    CategoryResponseDTO registerCategory(String name);

    ProviderResponseDTO getProvider(Long providerId);

    UserResponseDTO registerUser(UserRegisterDTO dto);

    UserResponseDTO login(LoginDTO dto);

    List<ProviderResponseDTO> getProvidersByCategory(Long categoryId);

	List<CategoryResponseDTO> getAll();
}

