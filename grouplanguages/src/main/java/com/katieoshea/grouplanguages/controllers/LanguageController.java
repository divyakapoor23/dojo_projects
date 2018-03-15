package com.katieoshea.grouplanguages.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.katieoshea.grouplanguages.models.Language;
import com.katieoshea.grouplanguages.services.LanguageService;

@Controller
public class LanguageController {
	private LanguageService lS; 
	
	public LanguageController(LanguageService lS) {
		this.lS = lS;
	}

	@RequestMapping("/")
	public String dashboard(Model model) {
		model.addAttribute("langs", lS.showAll());
		model.addAttribute("Language", new Language());
		return "allLangs";
	}

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("langs") Language language, BindingResult result, Model model) {
        if (result.hasErrors()) {
			// model.addAttribute("langs", lS.showAll());
			// model.addAttribute("Language", new Language());
            // return "allLangs";
            return "redirect:/";
		}
		else {
			lS.addLang(language);
            return "redirect:/";
		}
	}
	@RequestMapping("/languages/{index}")
	public String show(Model model, @PathVariable("index") int index) {
		Language lang = lS.showLang(index);
		model.addAttribute("lang", lang);
		model.addAttribute("langs", lS.showAll());
		return "showLang";
	}
	@RequestMapping("/delete/{index}")
	public String delete(@PathVariable("index") int index) {
		lS.delete(index);
		return "redirect:/";
	}
	@RequestMapping("/edit/{index}")
	public String edit(Model model, @PathVariable("index") int index) {
		Language lang = lS.showLang(index);
		model.addAttribute("lang", lang);
		model.addAttribute("langs", lS.showAll());
		return "editLang";
	}
	@PostMapping("/edit/{index}")
	public String edit(@Valid @ModelAttribute("category") Language lang, @PathVariable("index") int index, BindingResult result) {
        if(result.hasErrors()) {
            return "categories";
		}
		lS.updateLang(index, lang);
		return "redirect:/languages/{index}";
	}	

}
