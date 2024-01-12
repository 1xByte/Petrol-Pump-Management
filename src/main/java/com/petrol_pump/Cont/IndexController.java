package com.petrol_pump.Cont;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.petrol_pump.DTO.AdminSignUp;

@Controller
public class IndexController {
	
	@GetMapping("/")
	public String showHomePage(Model model) {
		
		//model.addAttribute("adminSignup", new AdminSignUp() );
		
		return "index";
	}

}
