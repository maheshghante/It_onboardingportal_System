package com.IT_OnboardingPortalSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.IT_OnboardingPortalSystem.entity.UsersEntity;
import com.IT_OnboardingPortalSystem.repository.UsersRepository;

@Service
public class UsersService {

    @Autowired
    private UsersRepository userRepository;

    // REGISTER USER
    public String registerUser(UsersEntity user) {

        String role = user.getRole();

        // ONLY ONE HR / ADMIN / MANAGER
        if(role != null &&
          (role.equalsIgnoreCase("HR") ||
           role.equalsIgnoreCase("Admin") ||
           role.equalsIgnoreCase("Manager"))) {

            UsersEntity existingUser =
                    userRepository.findByRole(role);

            if(existingUser != null) {

                return role + " already exists!";
            }
        }

        // SAVE USER
        userRepository.save(user);

        return "User Saved Successfully";
    }

    // SAVE USER
    public UsersEntity saveUser(UsersEntity user) {

        return userRepository.save(user);
    }

    // SAVE / UPDATE USER
    public UsersEntity save(UsersEntity user) {

        return userRepository.save(user);
    }

    // GET USER BY ROLE
    public UsersEntity getUserByRole(String role) {

        return userRepository.findByRole(role);
    }

    // LOGIN USER
    public UsersEntity login(String email,
                             String password) {

        UsersEntity user =
                userRepository.findByUrEmail(email);

        if(user != null &&
           user.getUrPassword().equals(password)) {

            return user;
        }

        return null;
    }

    // GET ALL USERS
    public List<UsersEntity> getAllUsers() {

        return userRepository.findAll(
                Sort.by(Sort.Direction.ASC, "urId"));
    }

    // GET USER BY ID
    public UsersEntity getUserById(Long id) {

        return userRepository.findById(id)
                             .orElse(null);
    }

    // DELETE USER
    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }

    // GET EMPLOYEES
    public List<UsersEntity> getEmployees() {

        return userRepository.findAllByRole("Employee");
    }
}