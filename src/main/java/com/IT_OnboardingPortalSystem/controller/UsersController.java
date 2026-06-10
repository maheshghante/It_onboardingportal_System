package com.IT_OnboardingPortalSystem.controller;

import com.IT_OnboardingPortalSystem.entity.UsersEntity;
import com.IT_OnboardingPortalSystem.service.UsersService;

import java.io.File;
import java.util.List;

import org.aspectj.apache.bcel.classfile.Module.Uses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    // HOME PAGE
    @GetMapping("/")
    public String home() {

        return "redirect:/login";
    }

    // OPEN REGISTER PAGE
    @GetMapping("/register")
    public String openRegisterPage(Model model) {

        model.addAttribute("user", new UsersEntity());

        return "register";
    }

    // REGISTER USER
    @PostMapping("/users/register")
    public String registerUser(
            @ModelAttribute UsersEntity user,
            Model model){

        String result =
                usersService.registerUser(user);

        
        model.addAttribute("message", result);

        return "register";
    }

    // LOGIN PAGE
    @GetMapping("/login")
    public String loginPage(Model model) {

        model.addAttribute("user", new UsersEntity());

        return "login";
    }

    // LOGIN USER
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            HttpSession session) {

        UsersEntity user = usersService.login(email, password);

        if (user != null) {

            System.out.println("Role = " + user.getRole());

            session.setAttribute("loggedInUser", user);

            String role = user.getRole();

            if ("HR".equalsIgnoreCase(role)) {
                return "redirect:/hr_dashboard";
            } 
            else if ("Admin".equalsIgnoreCase(role)) {
                return "redirect:/dashboard";
            } 
            else if ("Employee".equalsIgnoreCase(role)) {
                return "redirect:/employee_dashboard";
            } 
            else if ("Manager".equalsIgnoreCase(role)) {
                return "redirect:/manager_dashboard";
            }
            else if ("Manager".equalsIgnoreCase(role)) {
                return "redirect:/manager_dashboard";
            }
        }

        return "redirect:/login?error";
    }
    // DASHBOARD
    @GetMapping("/dashboard")
    public String dashboard() {

        return "dashboard";
    }

    // OPEN USER FORM
    @GetMapping("/userform")
    public String openUserForm(Model model) {

        model.addAttribute("user", new UsersEntity());

        return "userform";
    }

//    // SAVE USER
//    @PostMapping("/saveUser")
//    public String saveUser(@ModelAttribute("user") UsersEntity user,
//                           Model model) {
//
//        // CHECK ONLY HR, ADMIN, MANAGER
//        if (user.getRole().equalsIgnoreCase("HR") ||
//            user.getRole().equalsIgnoreCase("Admin") ||
//            user.getRole().equalsIgnoreCase("Manager")) {
//
//            UsersEntity existingUser =
//                    usersService.getUserByRole(user.getRole());
//
//            if (existingUser != null) {
//
//                model.addAttribute("error",
//                        user.getRole() + " already exists!");
//
//                model.addAttribute("user", new UsersEntity());
//
//                return "userform";
//            }  
//        }
//
//        // EMPLOYEE MULTIPLE ALLOWED
//        usersService.saveUser(user);
//
//        return "redirect:/users";
//    }
    
    
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") UsersEntity user,
                           Model model) {

        // CHECK ONLY HR, ADMIN, MANAGER
        if (user.getRole().equalsIgnoreCase("HR") ||
            user.getRole().equalsIgnoreCase("Admin") ||
            user.getRole().equalsIgnoreCase("Manager")) {

            UsersEntity existingUser =
                    usersService.getUserByRole(user.getRole());

            // CREATE TIME CHECK
            if (existingUser != null &&
                (user.getUrId() == null ||
                 !existingUser.getUrId().equals(user.getUrId()))) {

                model.addAttribute("error",
                        user.getRole() + " already exists!");

                model.addAttribute("user", user);

                return "userform";
            }
        }

        usersService.saveUser(user);

        return "redirect:/users";
    }
    
    
    
    
    // USER LIST
    @GetMapping("/users")
    public String viewUsers(Model model) {

        List<UsersEntity> users = usersService.getAllUsers();

        model.addAttribute("users", users);

        return "usersList";
    }
    
    
    // UPDATE USER
    @GetMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Long id,
                             Model model) {

        UsersEntity user = usersService.getUserById(id);

        model.addAttribute("user", user);

        return "userform";
    }

    // DELETE USER
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {

        usersService.deleteUser(id);

        return "redirect:/users";
    }

    // HR DASHBOARD
    @GetMapping("/hr_dashboard")
    public String hrDashboard(HttpSession session) {

        if(session.getAttribute("loggedInUser") == null) {

            return "redirect:/login";
        }

        return "hr_dashboard";
    }
    
 // MANAGER DASHBOARD
    @GetMapping("/manager_dashboard")
    public String manager_Dashboard(HttpSession session) {

        if(session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }

        return "manager_dashboard";
    }
    // LOGOUT
    @GetMapping("/logout")
    public String logout(HttpSession session){

        session.invalidate();

        return "redirect:/login";
    }

    // OPEN HR PROFILE PAGE
    @GetMapping("/hr/profile")
    public String hrProfile(HttpSession session,
                            Model model) {

        UsersEntity user =
                (UsersEntity) session.getAttribute("loggedInUser");

        if(user == null) {

            return "redirect:/login";
        }

        user = usersService.getUserById(user.getUrId());

        System.out.println(user.getRole()); // CHECK

        model.addAttribute("user", user);

        return "hr_profile";
    }

    
    
    
    
    // UPLOAD PROFILE PHOTO
    @PostMapping("/hr/profile/upload")
    public String uploadProfilePhoto(
            @RequestParam("profileImage") MultipartFile file,
            HttpSession session) {

        try {

            UsersEntity user =
                    (UsersEntity) session.getAttribute("loggedInUser");

            if(user == null) {

                return "redirect:/login";
            }

            if(!file.isEmpty()) {

                // Folder Path
                String uploadDir =
                        System.getProperty("user.dir") +
                        "/src/main/resources/static/profile-images/";

                File folder = new File(uploadDir);

                if(!folder.exists()) {

                    folder.mkdirs();
                }

                // File Name
                String fileName =
                        System.currentTimeMillis() + "_" +
                        file.getOriginalFilename();

                // Save File
                File saveFile = new File(uploadDir + fileName);

                file.transferTo(saveFile);

                // Save Image Name in Database
                user.setUrProfileImage(fileName);

                usersService.save(user);

                // Update Session
                session.setAttribute("loggedInUser", user);

                System.out.println("Photo Uploaded Successfully");
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return "redirect:/hr/profile";
    }

    // DELETE PROFILE PHOTO
    @GetMapping("/hr/profile/delete-photo")
    public String deleteProfilePhoto(HttpSession session) {

        UsersEntity user =
                (UsersEntity) session.getAttribute("loggedInUser");

        if(user == null) {

            return "redirect:/login";
        }

        user.setUrProfileImage(null);

        usersService.save(user);

        session.setAttribute("loggedInUser", user);

        return "redirect:/hr/profile";
    }
    
    //Employee List
    
  
    
    @GetMapping("/hr/employees")
    public String viewEmployees(Model model) {

        List<UsersEntity> employeeList =
                usersService.getEmployees();

        model.addAttribute("employees",
                           employeeList);

        return "employees";
    }
    
    
    
 //Manager Profile
    
 //   @GetMapping("/manager/profile")
  //  public String managerProfile(HttpSession session, Model model) {

 //   	UsersEntity user =
  //  	        (UsersEntity) session.getAttribute("loggedInUser");
    //    if(user == null) {
      //      return "redirect:/login";
      //  }

        //model.addAttribute("user", user);

        //return "manager_profile";
    //}
    
    // Upload Photo Controller
    
    @PostMapping("/manager/profile/upload")
    public String uploadManagerPhoto(
            @RequestParam("profileImage") MultipartFile file,
            HttpSession session) {


    	UsersEntity user =
    	        (UsersEntity) session.getAttribute("loggedInUser");
        if(user == null) {
            return "redirect:/login";
        }

        if(!file.isEmpty()) {

            String fileName = file.getOriginalFilename();

            try {

                Path uploadPath =
                        Paths.get("src/main/resources/static/profile-images");

                Files.createDirectories(uploadPath);

                Files.copy(
                        file.getInputStream(),
                        uploadPath.resolve(fileName),
                        StandardCopyOption.REPLACE_EXISTING);

                user.setUrProfileImage(fileName);

                usersService.save(user);

                session.setAttribute("loggedInUser", user);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "redirect:/manager/profile";
    }
     
    
    //Delete Photo Controller
    
    
    @GetMapping("/manager/profile/delete-photo")
    public String deleteManagerPhoto(HttpSession session) {

        UsersEntity users =
                (UsersEntity) session.getAttribute("loggedInUser");

        if(users != null) {

            users.setUrProfileImage(null);

            usersService.save(users);

            session.setAttribute("loggedInUser", users);
        }

        return "redirect:/manager/profile";
    }
   
  }