package com.IT_OnboardingPortalSystem.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long urId;

    private String urFirstName;
    private String urLastName;
    private LocalDate urDateofBirth;
    private String urGender;
    private String urEmail;
    private String urPassword;
    private Long urPhoneNumber;
    private String urAdress;
    private String urDesignation;
    private String urDepartment;
    private String urCreatedBy;
    private LocalDate urCreationDate;
    private String urDeactivated;
    private String urJoiningDate;
    private String role;
    private String urProfileImage;
    private String urLocation;

    private String urJoiningUpdate;

   
    // GETTER & SETTER
   
	

	public String getUrLocation() {
		return urLocation;
	}

	public void setUrLocation(String urLocation) {
		this.urLocation = urLocation;
	}

	public String getUrJoiningUpdate() {
		return urJoiningUpdate;
	}

	public void setUrJoiningUpdate(String urJoiningUpdate) {
		this.urJoiningUpdate = urJoiningUpdate;
	}

	public String getRole() {
		return role;
	}

    public String getUrProfileImage() {
        return urProfileImage;
    }

    public void setUrProfileImage(String urProfileImage) {
        this.urProfileImage = urProfileImage;
    }

	public void setRole(String role) {
		this.role = role;
	}

	public Long getUrId() {
        return urId;
    }

    public void setUrId(Long urId) {
        this.urId = urId;
    }

    public String getUrFirstName() {
        return urFirstName;
    }

    public void setUrFirstName(String urFirstName) {
        this.urFirstName = urFirstName;
    }

    public String getUrLastName() {
        return urLastName;
    }

    public void setUrLastName(String urLastName) {
        this.urLastName = urLastName;
    }

    public LocalDate getUrDateofBirth() {
        return urDateofBirth;
    }

    public void setUrDateofBirth(LocalDate urDateofBirth) {
        this.urDateofBirth = urDateofBirth;
    }

    public String getUrGender() {
        return urGender;
    }

    public void setUrGender(String urGender) {
        this.urGender = urGender;
    }

    public String getUrEmail() {
        return urEmail;
    }

    public void setUrEmail(String urEmail) {
        this.urEmail = urEmail;
    }

    public String getUrPassword() {
        return urPassword;
    }

    public void setUrPassword(String urPassword) {
        this.urPassword = urPassword;
    }

    public Long getUrPhoneNumber() {
        return urPhoneNumber;
    }

    public void setUrPhoneNumber(Long urPhoneNumber) {
        this.urPhoneNumber = urPhoneNumber;
    }

    public String getUrAdress() {
        return urAdress;
    }

    public void setUrAdress(String urAdress) {
        this.urAdress = urAdress;
    }

    public String getUrDesignation() {
        return urDesignation;
    }

    public void setUrDesignation(String urDesignation) {
        this.urDesignation = urDesignation;
    }

    public String getUrDepartment() {
        return urDepartment;
    }

    public void setUrDepartment(String urDepartment) {
        this.urDepartment = urDepartment;
    }

    public String getUrCreatedBy() {
        return urCreatedBy;
    }

    public void setUrCreatedBy(String urCreatedBy) {
        this.urCreatedBy = urCreatedBy;
    }

    public LocalDate getUrCreationDate() {
        return urCreationDate;
    }

    public void setUrCreationDate(LocalDate urCreationDate) {
        this.urCreationDate = urCreationDate;
    }

    public String getUrDeactivated() {
        return urDeactivated;
    }

    public void setUrDeactivated(String urDeactivated) {
        this.urDeactivated = urDeactivated;
    }

    public String getUrJoiningDate() {
        return urJoiningDate;
    }

    public void setUrJoiningDate(String urJoiningDate) {
        this.urJoiningDate = urJoiningDate;
    }
}