package com.IT_OnboardingPortalSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.IT_OnboardingPortalSystem.entity.UsersEntity;
import com.IT_OnboardingPortalSystem.repository.HrRepository;
import com.IT_OnboardingPortalSystem.service.UsersService;

@Controller

public class HrController {

    @Autowired
    private UsersService userService;

    @Autowired
    private HrRepository hrRepository;


    // SHOW ALL EMPLOYEES

    @GetMapping("/employees")
    public String showEmployees(Model model) {

        List<UsersEntity> employeeList = userService.getEmployees();

        model.addAttribute("employees", employeeList);

        return "employees";
    }


    // SAVE UPDATED EMPLOYEE

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") UsersEntity employee) {

    	
    	 employee.setRole("Employee");
    	 
        hrRepository.save(employee);

        return "redirect:/employees";
    }


    // DELETE EMPLOYEE

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable Long id) {

        hrRepository.deleteById(id);

        return "redirect:/employees";
    }


    // OPEN UPDATE PAGE

    @GetMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable Long id, Model model) {

        UsersEntity employee = hrRepository.findById(id).orElse(null);

        if(employee != null) {

            model.addAttribute("employee", employee);

            return "update_employee";
        }

        return "redirect:/employees";
    }
    @GetMapping("/profile")
    public String profilePage() {
        return "profile";
    }
  

   
   }