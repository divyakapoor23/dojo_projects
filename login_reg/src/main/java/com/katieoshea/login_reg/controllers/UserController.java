package com.katieoshea.login_reg.controllers;

import java.security.Principal;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.katieoshea.login_reg.models.User;
import com.katieoshea.login_reg.services.UserService;
import com.katieoshea.login_reg.validator.UserValidator;

@Controller
public class UserController {
    private UserService uServ;
    private UserValidator uValid;
        public UserController(UserService uServ, UserValidator uValid) {
        this.uServ = uServ;
        this.uValid = uValid;
    }
      
    @RequestMapping("/login")
    public String logreg(Model model, @RequestParam(value="logout", required=false) String logout, @RequestParam(value="error", required=false) String error, @Valid @ModelAttribute("user") User user) {
        System.out.println(logout);
        System.out.println(error);
    	if(logout != null) {
            model.addAttribute("logout", "Logout Successful!");
        }
        if(error != null) {
            model.addAttribute("error", "Invalid Credentials, Please try again.");
        }

        return "logregPage";
    }
    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        uValid.validate(user, result);
        if (result.hasErrors()) {
            return "logregPage";
        }
        if(uServ.findByEmail(user.getEmail()) != null){
            model.addAttribute("emailMessage", "A user with this email already exists!");
            return "logregPage";
        }
        uServ.init(user);
    	System.out.println("good register");
        return "redirect:/login";
    }
    
//    @RequestMapping("/login")
//    public String login(@RequestParam(value="logErrors", required=false) String logErrors, Model model) {
//    	if(result.hasErrors()) {
//    		ArrayList<String> errs = new ArrayList<String>();     		
//    		if(uServ.findByEmail(user.getEmail()) == null) {
//    			errs.add("User does not exist! Please register");
//    		}
//    		else {
//	    		User check = uServ.findByEmail(user.getEmail());
//	    		if(check.getPassword() != user.getPassword()) {
//	    			errs.add("Incorrect Password");
//	    			System.out.println("bad");
//	    		}
//    		}
//    		model.addAttribute("logErrors", errs);
//            return "logregPage";
//        }
//        else {
//        	System.out.println("good login");
//    		return "redirect:/dashboard";
//        }
//    }

    @RequestMapping(value= {"/home", "/"})
    public String dash(Principal principal, Model model) {
    	System.out.println("good dashboard");
        String email = principal.getName();
        model.addAttribute("currentUser", uServ.findByEmail(email));
        return "dashboard";
    }
    

}
