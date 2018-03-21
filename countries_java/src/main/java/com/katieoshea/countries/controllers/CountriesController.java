package com.katieoshea.countries.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.katieoshea.countries.services.ApiService;

@Controller
public class CountriesController {
    private ApiService apiServ;
    public CountriesController(ApiService apiServ) {
        this.apiServ = apiServ;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }
    @RequestMapping("/slovene")
    public String slovene(Model model) {
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("Country");
        columns.add("Language");
        columns.add("Percentage");
        model.addAttribute("columns", columns);
    	model.addAttribute("query", "Slovene");
    	model.addAttribute("desc", "What query would you run to get all the countries that speak Slovene? Your query should return the name of the country, language and language percentage. Your query should arrange the result by language percentage in descending order.");
        model.addAttribute("table", apiServ.slovene());
        return "query";
    }
    @RequestMapping("/total")
    public String cities(Model model) {
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("Country");
        columns.add("Number Cities");
        model.addAttribute("columns", columns);
        model.addAttribute("query", "String Total Cities");
        model.addAttribute("desc", "What query would you run to display the total number of cities for each country? Your query should return the name of the country and the total number of cities. Your query should arrange the result by the number of cities in descending order.");
        model.addAttribute("table", apiServ.totalcities());
        return "query";
    }
    @RequestMapping("/mexico")
    public String mexico(Model model) {
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("City");
        columns.add("Population");
        model.addAttribute("columns", columns);
        model.addAttribute("query", "Mexico");
        model.addAttribute("desc", "What query would you run to get all the cities in Mexico with a population of greater than 500,000? Your query should arrange the result by population in descending order.");
        model.addAttribute("table", apiServ.mexico());
        return "query";
    }
    @RequestMapping("/mainlangs")
    public String mainlangs(Model model) {
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("Country");
        columns.add("Language");
        columns.add("Percentage");
        model.addAttribute("columns", columns);
        model.addAttribute("query", "Main Languages");
        model.addAttribute("desc", "What query would you run to get all languages in each country with a percentage greater than 89%? Your query should arrange the result by percentage in descending order.");
        model.addAttribute("table", apiServ.mainlang());
        return "query";
    }
    @RequestMapping("/surfpop")
    public String surfpop(Model model) {
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("Country");
        columns.add("Surface Area");
        columns.add("Population");
        model.addAttribute("columns", columns);
        model.addAttribute("query", "Surface Area & Population");
        model.addAttribute("desc", "What query would you run to get all the countries with Surface Area below 501 and Population greater than 100,000?");
        model.addAttribute("table", apiServ.surfacepop());
        return "query";
    }
    @RequestMapping("/monarch")
    public String monarch(Model model) {
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("Monarchy");
        model.addAttribute("columns", columns);
        model.addAttribute("query", "Some Constitutional Monarchies");
        model.addAttribute("desc", "What query would you run to get countries with only Constitutional Monarchy with a surface area of more than 200 and a life expectancy greater than 75 years?");
        model.addAttribute("table", apiServ.firstworldmonarchy());
        return "query";
    }
    @RequestMapping("/argentina")
    public String argentina(Model model) {
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("Country");
        columns.add("City");
        columns.add("District");
        columns.add("Population");
        model.addAttribute("columns", columns);
        model.addAttribute("query", "Argentina");
        model.addAttribute("desc", "What query would you run to get all the cities of Argentina inside the Buenos Aires district and have the population greater than 500, 000? The query should return the Country Name, City Name, District, and Population.");
        model.addAttribute("table", apiServ.argentina());
        return "query";
    }
    @RequestMapping("/regions")
    public String regions(Model model) {
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("Regions");
        columns.add("Number of Countries");
        model.addAttribute("columns", columns);
        model.addAttribute("query", "Regions");
        model.addAttribute("desc", "What query would you run to summarize the number of countries in each region? The query should display the name of the region and the number of countries. Also, the query should arrange the result by the number of countries in descending order.");
        model.addAttribute("table", apiServ.regions());
        return "query";
    }
    
}
