package com.katieoshea.auth.controllers;

import java.security.Principal;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.katieoshea.auth.models.User;
import com.katieoshea.auth.services.UserService;
import com.katieoshea.auth.validator.UserValidator;

@Controller
public class UserController {
    private UserService uServ;
    private UserValidator uValid;
        public UserController(UserService uServ, UserValidator uValid) {
        this.uServ = uServ;
        this.uValid = uValid;
    }
    
    @RequestMapping("/register")
    public String registerForm(@Valid @ModelAttribute("user") User user) {
        return "registrationPage";
    }
    
    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        uValid.validate(user, result);
        if (result.hasErrors()) {
            return "registrationPage";
        }
        uServ.saveWithUserRole(user);
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid credentials, please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successful!");
        }
        return "loginPage";
    }   
    @PostMapping("/login")
    public String loginAction(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if(result.hasErrors()) {
            return "loginPage";
        }
        else {
            return "redirect:/";
        }
    }

    
    @RequestMapping(value = {"/", "/home"})
    public String home(Principal principal, Model model) {
////////////////////////////////////
    	// NOTE: In older versions of Spring, there is a more verbose way to get the current user. You will see this a lot when searching information about Spring Security.
//    	import org.springframework.security.core.context.SecurityContextHolder;
//    	import org.springframework.security.core.userdetails.User;
    	// By default, we get type object, so we must cast it into a UserDetails object.
//    	User userPrincipal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    	String name = user.getUsername();
////////////////////////////////////
        String username = principal.getName();
        model.addAttribute("currentUser", uServ.findByUsername(username));
        return "homePage";
    }    

}
