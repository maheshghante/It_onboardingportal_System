package com.IT_OnboardingPortalSystem.service;

import java.util.List;

import com.IT_OnboardingPortalSystem.entity.DepartmentEntity;

public interface DepartmentService {

    void saveDepartment(DepartmentEntity department);

    List<DepartmentEntity> getAllDepartments();

    void deleteDepartment(Long id);

    DepartmentEntity getDepartmentById(Long id);
}