package com.katieoshea.dojosninjas.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.katieoshea.dojosninjas.models.Ninja;
import com.katieoshea.dojosninjas.models.Dojo;
import com.katieoshea.dojosninjas.services.NinjaService;
import com.katieoshea.dojosninjas.services.DojoService;

@Controller
public class NinjaController {
    private NinjaService ninjaServ;
    private DojoService dojoServ;
	public NinjaController(NinjaService ninjaServ, DojoService dojoServ){
        this.ninjaServ=ninjaServ;
        this.dojoServ=dojoServ;
    }

	@RequestMapping("/ninjas/new")
	public String ninjaMake(Model model) {
		System.out.println("hello");
        List<Dojo> dojos = dojoServ.all();
        model.addAttribute("locations", dojos);
		model.addAttribute("newNinja", new Ninja());
        return "newNinja";
    }
    @PostMapping("/ninjas/new")
	public String ninjaCreate(@Validated @ModelAttribute("newNinja") Ninja ninja, BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<Dojo> dojos = dojoServ.all();
			model.addAttribute("locations", dojos);
			model.addAttribute("newNinja", new Ninja());
			return "newNinja"; 
		}
		else {
			ninjaServ.create(ninja);
			return "redirect:/ninjas/new";
        }
    }
}