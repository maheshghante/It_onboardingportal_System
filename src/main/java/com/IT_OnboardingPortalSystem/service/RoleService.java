package com.IT_OnboardingPortalSystem.service;

import java.util.List;

import com.IT_OnboardingPortalSystem.entity.RoleEntity;

public interface RoleService {

    RoleEntity saveRole(RoleEntity role);

    List<RoleEntity> getAllRoles();

    RoleEntity getRoleById(Long id);

    void deleteRole(Long id);
}