package com.appointment.project.appointmentbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.project.appointmentbackend.dto.CategoryResponseDTO;
import com.appointment.project.appointmentbackend.service.UserService;
@RestController
@RequestMapping("/api/provider")
@CrossOrigin
public class ProviderController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/categories")
    public List<CategoryResponseDTO> getCategories() {
        return userService.getAll();
    }

    @PostMapping("/add")
    public CategoryResponseDTO addCategory(@RequestBody String name) {
        return userService.registerCategory(name);
    }
}
