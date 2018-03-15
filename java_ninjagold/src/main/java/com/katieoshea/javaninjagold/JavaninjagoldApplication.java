package com.katieoshea.javaninjagold;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Random;

@SpringBootApplication
public class JavaninjagoldApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaninjagoldApplication.class, args);
	}
	@Controller
	public class NinjaGold {
		@RequestMapping("/")
		public String index(HttpSession session) {
			return "index";
		}
		@RequestMapping("/reset")
		public String reset(HttpSession session) {
			session.setAttribute("golds", 0);
			session.setAttribute("choices", "");
			return "redirect:/";
		}
	}
		
	@Controller
	public class ProcessMoney {
		@RequestMapping(path="/money", method=RequestMethod.POST)
		public String survey(HttpSession session, @RequestParam(value="building") String building) {
			if(session.getAttribute("golds") == null || session.getAttribute("choices")==null) {
				ArrayList<String> log = new ArrayList<String>();
				session.setAttribute("golds", 0);
				session.setAttribute("choices", "");
			}

			int total = (int) session.getAttribute("golds");
			int gold = 0;
			
			String place = (String) building;
			Random random = new Random();
			switch(place){
				case "farm":
					gold = random.nextInt(10)+10;
					break;
				case "cave":
					gold = random.nextInt(5)+5;
					break;
				case "house":
					gold = random.nextInt(3)+2;
					break;
				case "casino":
					gold = random.nextInt(100)-50;
					break;
				case "spa":
					gold = random.nextInt(15)-20;
					break;
				
			}
			
			total += gold;
			session.setAttribute("golds", total);
			
			String earned;
			String color;
			String topcolor;

			if(gold > 0) {
				earned = "Earned ";
				color = "green";
				session.setAttribute("earned", true);
			}
			else {
				earned = "Lost ";
				color = "red" ;
				session.setAttribute("earned", false);
			}
			session.setAttribute("color", color);
			if(total > 0) {
				topcolor = "green";
			}
			else {
				topcolor = "red" ;
			}
			session.setAttribute("topcolor", topcolor);
			
			String added = Integer.toString(gold);
			String choice = earned + added + " golds from the " + place + "(" + new java.util.Date()+ ")";

			ArrayList<String> log = (ArrayList<String>) session.getAttribute("choices");
			log.add(0, choice);
			
			session.setAttribute("choices", log);

	    	return "redirect:/";
		}
	}
}
