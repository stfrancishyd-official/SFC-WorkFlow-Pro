package com.sfc.workflow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sfc.workflow.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

        Optional<User> findByEmail(String email);
}
