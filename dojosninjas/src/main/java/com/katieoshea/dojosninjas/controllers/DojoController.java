package com.katieoshea.dojosninjas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.katieoshea.dojosninjas.models.Dojo;
import com.katieoshea.dojosninjas.services.DojoService;
import com.katieoshea.dojosninjas.models.Ninja;
import com.katieoshea.dojosninjas.services.NinjaService;

@Controller
public class DojoController {
	private DojoService dojoServ;
	private NinjaService ninjaServ;
	public DojoController(DojoService dojoServ, NinjaService ninjaServ){
		this.dojoServ=dojoServ;        
		this.ninjaServ=ninjaServ;
    }
        
	@RequestMapping("/")
	public String index(Model model) {
		List<Dojo> dojos = dojoServ.all();
		model.addAttribute("locations", dojos);
		return "index";
    }

	@RequestMapping("/dojos/new")
	public String dojoMake(Model model) {
		model.addAttribute("newDojo", new Dojo());
        return "newDojo";
    }
	@PostMapping("/dojos/new")
	public String dojoCreate(@Validated @ModelAttribute("newDojo") Dojo dojo, BindingResult result, Model model) {
		if(result.hasErrors()) {
            model.addAttribute("newDojo", new Dojo());
			return "newDojo"; 
		}
		dojoServ.create(dojo);
		return "redirect:/dojos/new";
    }

	@RequestMapping("/dojos/{id}")
	public String show(Model model, @PathVariable("id") Long id) {
		Dojo dojo = dojoServ.findDojo(id);
		List<Ninja> allNinjas = ninjaServ.all();
		ArrayList<Ninja> ninjas = new ArrayList<Ninja>();
        for(Ninja ninja: allNinjas) {
        	if(ninja.getDojo() == dojo) {
        		ninjas.add(ninja);
        	}
		}
        if(ninjas.size() == 0) {
        	model.addAttribute("noone", "No ninjas here yet");
        }
        model.addAttribute("ninjas", ninjas);
		model.addAttribute("dojo", dojo);
		return "dojopage";
    }
}
