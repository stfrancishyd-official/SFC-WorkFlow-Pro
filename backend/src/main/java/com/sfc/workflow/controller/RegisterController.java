package com.sfc.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sfc.workflow.dto.LoginResponseDto;
import com.sfc.workflow.dto.RegisterRequestDto;
import com.sfc.workflow.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
public class RegisterController {

    @Autowired
    private UserService userService;

        @PostMapping("/register")
        public LoginResponseDto register(@RequestBody RegisterRequestDto dto) {
            return userService.register(dto);
        }
        
}
