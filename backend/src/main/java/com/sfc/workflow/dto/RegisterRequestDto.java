package com.sfc.workflow.dto;

import com.sfc.workflow.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {

    private String name;
    private String email;
    private String password;
    private Role role;

}
