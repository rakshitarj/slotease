package com.appointment.project.appointmentbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.appointment.project.appointmentbackend.dto.UserRegisterDTO;
import com.appointment.project.appointmentbackend.dto.UserResponseDTO;
import com.appointment.project.appointmentbackend.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody UserRegisterDTO dto) {
        return userService.registerUser(dto);
    }
}
