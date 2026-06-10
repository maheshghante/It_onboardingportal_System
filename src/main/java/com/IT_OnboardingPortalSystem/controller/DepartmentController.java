package com.IT_OnboardingPortalSystem.controller;

import com.IT_OnboardingPortalSystem.entity.DepartmentEntity;
import com.IT_OnboardingPortalSystem.service.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // DISPLAY LIST
    @GetMapping("/departmentList")
    public String departmentList(Model model) {

        model.addAttribute("list",
                departmentService.getAllDepartments());

        return "departmentList";
    }
    
    @GetMapping("/department")
    public String openDepartmentPage(Model model) {

        model.addAttribute("department", new DepartmentEntity());

        return "department";
    }

    // DELETE
    @GetMapping("/department/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return "redirect:/departmentList";
    }
    

    // OPEN UPDATE PAGE
    @GetMapping("/department/edit/{id}")
    public String editDepartment(@PathVariable Long id,
                                 Model model) {

        DepartmentEntity department =
                departmentService.getDepartmentById(id);

        model.addAttribute("department", department);

        return "updateDepartment";
    }

    // UPDATE SAVE
    @PostMapping("/department/update")
    public String updateDepartment(
            @ModelAttribute DepartmentEntity department) {

        departmentService.saveDepartment(department);

        return "redirect:/departmentList";
    }
}