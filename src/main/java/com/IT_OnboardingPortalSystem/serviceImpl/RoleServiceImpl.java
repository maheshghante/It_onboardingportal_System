package com.IT_OnboardingPortalSystem.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IT_OnboardingPortalSystem.entity.RoleEntity;
import com.IT_OnboardingPortalSystem.repository.RoleRepository;
import com.IT_OnboardingPortalSystem.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleEntity saveRole(RoleEntity role) {

        return roleRepository.save(role);
    }

    @Override
    public List<RoleEntity> getAllRoles() {

        return roleRepository.findAll();
    }

    @Override
    public RoleEntity getRoleById(Long id) {

        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteRole(Long id) {

        roleRepository.deleteById(id);
    }
}