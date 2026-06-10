package com.IT_OnboardingPortalSystem.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.IT_OnboardingPortalSystem.entity.UsersEntity;

@Repository
public interface ManagerRepository extends JpaRepository<UsersEntity, Long> {

}
