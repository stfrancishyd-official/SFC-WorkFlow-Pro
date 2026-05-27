package com.sfc.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sfc.workflow.config.JwtService;
import com.sfc.workflow.dto.LoginRequestDto;
import com.sfc.workflow.dto.LoginResponseDto;
import com.sfc.workflow.dto.RegisterRequestDto;
import com.sfc.workflow.entity.User;
import com.sfc.workflow.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public LoginResponseDto register(RegisterRequestDto dto) {

        // Check if email already exists

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already Registered");
        }

        // create a new user entity

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        // encrypt the password

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        // Set the role of the user
        user.setRole(dto.getRole());

        // save user details

        User savedUser = userRepository.save(user);

        String token = jwtService.generateToken(savedUser);

        // Return the response

        LoginResponseDto response = new LoginResponseDto();
        response.setId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setRole(savedUser.getRole());
        response.setMessage("user registered Sucessfully");
        response.setToken(token);

        return response;
    }

    public LoginResponseDto login(LoginRequestDto dto) {

        // Find the user by email

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("user not found"));

        // Check the password

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {

            throw new RuntimeException("Invalid Password");
        }

        // Generate JWT Token
        String token = jwtService.generateToken(user);

        // Return response

        LoginResponseDto response = new LoginResponseDto();
        response.setId(user.getId());

        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setMessage("Login successful");
        response.setToken(token);

        return response;
    }

}
