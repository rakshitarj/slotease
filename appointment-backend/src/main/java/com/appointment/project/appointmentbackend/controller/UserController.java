package com.appointment.project.appointmentbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.project.appointmentbackend.dto.LoginDTO;
import com.appointment.project.appointmentbackend.dto.ProviderResponseDTO;
import com.appointment.project.appointmentbackend.dto.UserRegisterDTO;
import com.appointment.project.appointmentbackend.dto.UserResponseDTO;
import com.appointment.project.appointmentbackend.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
	  @Autowired
	    private UserService userService;

	    @PostMapping("/register")
	    public UserResponseDTO registerUser(@RequestBody UserRegisterDTO dto) {
	        return userService.registerUser(dto);
	    }

	    @PostMapping("/login")
	    public UserResponseDTO login(@RequestBody LoginDTO dto) {
	        return userService.login(dto);
	    }

	    @GetMapping("/providers/{categoryId}")
	    public List<ProviderResponseDTO> getProvidersByCategory(@PathVariable Long categoryId) {
	        return userService.getProvidersByCategory(categoryId);
	    }
	}
	


