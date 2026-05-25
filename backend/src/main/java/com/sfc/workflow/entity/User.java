package com.sfc.workflow.entity;

import java.time.LocalDateTime;

import com.sfc.workflow.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @Email(message = "Invalid email format")
        @NotBlank(message = "Email required")
        @Column(unique = true, nullable = false)
        private String email;

        private String password;

        @Enumerated(EnumType.STRING)
        private Role role;

        private boolean isActive = true;
        private LocalDateTime createdAt = LocalDateTime.now();
}
