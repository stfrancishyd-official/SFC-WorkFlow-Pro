package com.sfc.workflow.dto;

import com.sfc.workflow.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {

    private Long id;
    private String email;
    private Role role;
    private String message;
    private String token;
}
