package com.katieoshea.events.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.katieoshea.events.models.User;
import com.katieoshea.events.services.EventServ;
import com.katieoshea.events.services.MessageServ;
import com.katieoshea.events.services.UserServ;
import com.katieoshea.events.validator.UserValidator;

@Controller
public class UserController {
    private UserServ uServ;
    private UserValidator uValid;
    public UserController(UserServ uServ, UserValidator uValid, EventServ eServ, MessageServ mServ) {
        this.uServ = uServ;
        this.uValid = uValid;
    }
	private ArrayList<String> states = new ArrayList<String>(Arrays.asList("AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY"));

    @RequestMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model, @Valid @ModelAttribute("user") User user, BindingResult result) {
    	model.addAttribute("states", states);
    	if(logout != null) {
            model.addAttribute("logout", "Logout Successful!");
        }
        if(error != null) {
        	System.out.println(result.getAllErrors());
        	System.out.println("hihihihi");
            model.addAttribute("logError", "Invalid credentials, please try again.");
        }
        return "index.jsp";
    }
        
    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        uValid.validate(user, result);
        model.addAttribute("states", states);
        if(result.hasErrors()) {
            return "index.jsp";
        }
        else {
            uServ.save(user);
            return "redirect:/login";
        }
    }   

    @RequestMapping(value= {"/", "/home"})
    public String index() {
    	return "redirect:/events";
    }
}
