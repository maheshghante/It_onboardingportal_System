package com.IT_OnboardingPortalSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IT_OnboardingPortalSystem.entity.UsersEntity;

@Repository
public interface UsersRepository
        extends JpaRepository<UsersEntity, Long> {

    // LOGIN
    UsersEntity findByUrEmail(String urEmail);

    // CHECK DESIGNATION
    boolean existsByUrDesignation(String urDesignation);

    // GET EMPLOYEES
    List<UsersEntity> findByUrDesignation(String urDesignation);

    // GET USER BY ROLE
    UsersEntity findByRole(String role);
    
    List<UsersEntity> findAllByRole(String role);
}

