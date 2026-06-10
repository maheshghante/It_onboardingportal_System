package com.IT_OnboardingPortalSystem.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IT_OnboardingPortalSystem.entity.DepartmentEntity;
import com.IT_OnboardingPortalSystem.repository.DepartmentRepository;
import com.IT_OnboardingPortalSystem.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void saveDepartment(DepartmentEntity department) {

        departmentRepository.save(department);
    }

    @Override
    public List<DepartmentEntity> getAllDepartments() {

        return departmentRepository.findAll();
    }

    @Override
    public void deleteDepartment(Long id) {

        departmentRepository.deleteById(id);
    }

    @Override
    public DepartmentEntity getDepartmentById(Long id) {

        return departmentRepository.findById(id).orElse(null);
    }
}
