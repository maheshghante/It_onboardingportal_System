package com.IT_OnboardingPortalSystem.controller;

import com.IT_OnboardingPortalSystem.entity.RoleEntity;
import com.IT_OnboardingPortalSystem.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RoleController {
	 @Autowired
	    private RoleService roleService;

	    // OPEN ROLE FORM
	    @GetMapping("/role")
	    public String role(Model model) {

	        model.addAttribute("role", new RoleEntity());

	        return "role";
	    }

	    // SAVE ROLE
	    @PostMapping("/saveRole")
	    public String saveRole(@ModelAttribute RoleEntity role) {

	        roleService.saveRole(role);

	        return "redirect:/roles";
	    }

	    // ROLE LIST
	    @GetMapping("/roles")
	    public String getAllRoles(Model model) {

	        model.addAttribute("roles", roleService.getAllRoles());

	        return "role-list";
	    }

	    // UPDATE ROLE
	    @GetMapping("/role/update/{id}")
	    public String updateRole(@PathVariable Long id, Model model) {

	        RoleEntity role = roleService.getRoleById(id);

	        model.addAttribute("role", role);

	        return "role";
	    }

	    // DELETE ROLE
	    @GetMapping("/role/delete/{id}")
	    public String deleteRole(@PathVariable Long id) {

	        roleService.deleteRole(id);

	        return "redirect:/roles";
	    }
	
}