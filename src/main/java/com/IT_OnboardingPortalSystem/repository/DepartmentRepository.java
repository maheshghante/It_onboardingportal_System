package com.IT_OnboardingPortalSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IT_OnboardingPortalSystem.entity.DepartmentEntity;

public interface DepartmentRepository
        extends JpaRepository<DepartmentEntity, Long> {

}