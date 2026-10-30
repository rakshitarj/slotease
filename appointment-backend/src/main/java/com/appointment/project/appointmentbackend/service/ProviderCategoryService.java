package com.appointment.project.appointmentbackend.service;

import java.util.List;

import com.appointment.project.appointmentbackend.dto.CategoryResponseDTO;

public interface ProviderCategoryService {
	CategoryResponseDTO createCategory(String categoryName);
    List<CategoryResponseDTO> getAll();
}
