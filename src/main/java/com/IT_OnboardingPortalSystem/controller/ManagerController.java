package com.IT_OnboardingPortalSystem.controller;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.IT_OnboardingPortalSystem.entity.UsersEntity;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/manager")
public class ManagerController {
	@GetMapping("/login")
	public String loginPage() {
	    return "manager_login";
	}

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session) {

        // Demo Login
        if(email.equals("manager@gmail.com")
                && password.equals("1234")) {

            session.setAttribute("managerName", "Manager");

            return "redirect:/manager/dashboard";
        }

        return "manager_login";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        String managerName =
                (String) session.getAttribute("managerName");

        if(managerName == null) {
            return "redirect:/manager/login";
        }

        model.addAttribute("managerName", managerName);

        return "manager_dashboard";
    }

    @GetMapping("/profile")
    public String profile() {
        return "manager_profile";
    }

    @GetMapping("/approval-request")
    public String approvalRequest() {
        return "approval_request";
    }
    
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "Manager Controller Working";
    }
    @GetMapping("/manager/profile")
    public String managerProfile(HttpSession session, Model model) {

        UsersEntity user =
                (UsersEntity) session.getAttribute("loggedInUser");

        if(user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);

        return "manager_profile";
    }
    

 }