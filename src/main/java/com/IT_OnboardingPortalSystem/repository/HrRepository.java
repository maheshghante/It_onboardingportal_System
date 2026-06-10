package com.IT_OnboardingPortalSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IT_OnboardingPortalSystem.entity.UsersEntity;

public interface HrRepository extends JpaRepository<UsersEntity, Long>{

    List<UsersEntity> findByUrDesignation(String urDesignation);

}