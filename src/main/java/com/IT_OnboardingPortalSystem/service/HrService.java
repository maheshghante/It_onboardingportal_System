package com.IT_OnboardingPortalSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IT_OnboardingPortalSystem.entity.UsersEntity;
import com.IT_OnboardingPortalSystem.repository.HrRepository;


@Service
public class HrService {

    @Autowired
    private HrRepository hrRepository;

    public List<UsersEntity> getEmployees() {

        return hrRepository.findByUrDesignation("Employee");
    }

}