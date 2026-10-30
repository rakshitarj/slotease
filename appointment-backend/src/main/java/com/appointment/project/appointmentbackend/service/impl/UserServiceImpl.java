package com.appointment.project.appointmentbackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.appointment.project.appointmentbackend.dto.CategoryResponseDTO;
import com.appointment.project.appointmentbackend.dto.LoginDTO;
import com.appointment.project.appointmentbackend.dto.ProviderResponseDTO;
import com.appointment.project.appointmentbackend.dto.UserRegisterDTO;
import com.appointment.project.appointmentbackend.dto.UserResponseDTO;
import com.appointment.project.appointmentbackend.entity.ProviderCategory;
import com.appointment.project.appointmentbackend.entity.User;
import com.appointment.project.appointmentbackend.repository.ProviderCategoryRepository;
import com.appointment.project.appointmentbackend.repository.UserRepository;
import com.appointment.project.appointmentbackend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private ProviderCategoryRepository categoryRepo;

    @Autowired
    private PasswordEncoder encoder;


    // -------------------------------------------
    // REGISTER PROVIDER
    // -------------------------------------------
    @Override
    public UserResponseDTO registerProvider(UserRegisterDTO dto) {

        if (repo.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setMobileNo(dto.getMobileNo());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole("PROVIDER");

        ProviderCategory category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Invalid category"));

        user.setCategory(category);

        User savedUser = repo.save(user);

        return new UserResponseDTO(
                savedUser.getUserId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole(),
                savedUser.getCategory().getCategoryName()
        );
    }


    // -------------------------------------------
    // REGISTER CATEGORY
    // -------------------------------------------
    @Override
    public CategoryResponseDTO registerCategory(String name) {

        if (categoryRepo.findByCategoryName(name).isPresent()) {
            throw new RuntimeException("Category already exists");
        }

        ProviderCategory category = new ProviderCategory();
        category.setCategoryName(name);

        ProviderCategory saved = categoryRepo.save(category);

        return new CategoryResponseDTO(
                saved.getCategoryId(),
                saved.getCategoryName()
        );
    }


    // -------------------------------------------
    // GET ALL CATEGORIES
    // -------------------------------------------
    @Override
    public List<CategoryResponseDTO> getAll() {

        return categoryRepo.findAll()
                .stream()
                .map(cat -> new CategoryResponseDTO(cat.getCategoryId(), cat.getCategoryName()))
                .toList();
    }


    // -------------------------------------------
    // GET PROVIDER BY ID
    // -------------------------------------------
    @Override
    public ProviderResponseDTO getProvider(Long providerId) {

        User provider = repo.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        if (!"PROVIDER".equals(provider.getRole())) {
            throw new RuntimeException("User is not a provider");
        }

        return new ProviderResponseDTO(
                provider.getUserId(),
                provider.getName(),
                provider.getEmail(),
                provider.getCategory().getCategoryName()
        );
    }


    // -------------------------------------------
    // REGISTER NORMAL USER
    // -------------------------------------------
    @Override
    public UserResponseDTO registerUser(UserRegisterDTO dto) {
    	

        // Normalize role
        String role = dto.getRole() == null ? "USER" : dto.getRole().trim().toUpperCase();

        // Unique checks
        if (repo.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        if (repo.existsByMobileNo(dto.getMobileNo())) {
            throw new RuntimeException("Mobile number already registered");
        }

        // Build user
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setMobileNo(dto.getMobileNo());
        user.setPassword(encoder.encode(dto.getPassword())); // FIXED
        user.setRole(role);

        // Provider category
        if ("PROVIDER".equals(role)) {

            Long catId = dto.getCategoryId();
            if (catId == null) {
                throw new RuntimeException("Provider must select a category");
            }

            ProviderCategory category = categoryRepo.findById(catId)
                    .orElseThrow(() -> new RuntimeException("Invalid category"));

            user.setCategory(category);

        } else {
            user.setCategory(null);
        }

        // Save
        User saved = repo.save(user);

        // Build response
        return new UserResponseDTO(
                saved.getUserId(),
                saved.getName(),
                saved.getEmail(),
                saved.getRole(),
                saved.getCategory() != null ? saved.getCategory().getCategoryName() : null
        );
    }

    // -------------------------------------------
    // LOGIN USER
    // -------------------------------------------
    @Override
    public UserResponseDTO login(LoginDTO dto) {

        User user = repo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return new UserResponseDTO(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getCategory() != null ? user.getCategory().getCategoryName() : null
        );
    }


    // -------------------------------------------
    @SuppressWarnings("unused")
	// GET PROVIDERS BY CATEGORY
    // -------------------------------------------
    @Override
    public List<ProviderResponseDTO> getProvidersByCategory(Long categoryId) {

        ProviderCategory category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Invalid category ID"));

        List<User> providers = repo.findByRoleAndCategory_CategoryId("PROVIDER", categoryId);

        if (providers.isEmpty()) {
            throw new RuntimeException("No providers found for this category");
        }

        return providers.stream()
                .map(provider -> new ProviderResponseDTO(
                        provider.getUserId(),
                        provider.getName(),
                        provider.getEmail(),
                        provider.getCategory().getCategoryName()
                ))
                .toList();
    }
}
