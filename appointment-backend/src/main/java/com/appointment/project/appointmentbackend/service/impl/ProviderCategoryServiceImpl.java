package com.appointment.project.appointmentbackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appointment.project.appointmentbackend.dto.CategoryResponseDTO;
import com.appointment.project.appointmentbackend.entity.ProviderCategory;
import com.appointment.project.appointmentbackend.repository.ProviderCategoryRepository;
import com.appointment.project.appointmentbackend.service.ProviderCategoryService;
@Service
public class ProviderCategoryServiceImpl implements ProviderCategoryService {
	
	   @Autowired
	    private ProviderCategoryRepository categoryRepo;

	@Override
	public CategoryResponseDTO createCategory(String categoryName) {
		   // 1. Check duplicate
        if (categoryRepo.findByCategoryName(categoryName).isPresent()) {
            throw new RuntimeException("Category already exists");
        }

        // 2. DTO → Entity
        ProviderCategory category = new ProviderCategory();
        category.setCategoryName(categoryName);

        // 3. Save
        ProviderCategory saved = categoryRepo.save(category);

        // 4. Entity → DTO
        return new CategoryResponseDTO(
                saved.getCategoryId(),
                saved.getCategoryName()
        );
	}

	@Override
		public List<CategoryResponseDTO> getAll() {

	        List<ProviderCategory> categories = categoryRepo.findAll();

	        return categories.stream()
	                .map(cat -> new CategoryResponseDTO(
	                        cat.getCategoryId(),
	                        cat.getCategoryName()
	                ))
	                .toList();
	    }

}
