package com.appointment.project.appointmentbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appointment.project.appointmentbackend.entity.ProviderCategory;
public interface ProviderCategoryRepository extends JpaRepository<ProviderCategory, Long> {

	Optional<ProviderCategory> findByCategoryName(String name);
    boolean existsByCategoryName(String categoryName);


}
