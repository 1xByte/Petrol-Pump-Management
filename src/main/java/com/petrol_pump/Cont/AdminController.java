package com.petrol_pump.Cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.petrol_pump.DTO.AdminLogIn;
import com.petrol_pump.DTO.AdminSignUp;
import com.petrol_pump.DTO.FuelDto;
import com.petrol_pump.Service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/adminSignup")
	public String showAdminSignUp(@ModelAttribute("adminSignUp") AdminSignUp adminSignUp ,Model model) {
		
		model.addAttribute("adminSignup", new AdminSignUp() );
		
		return "AdminSignUp";
	}
	
	@PostMapping("/adminSignup")
	public String AdminSignUp(@ModelAttribute("adminSignUp") AdminSignUp adminSignUp 
															,Model model) {
		
		String signUp = adminService.adminSignUp(adminSignUp);
		model.addAttribute("signUp", signUp);
		
		return "AdminSignUp";
	}
	
	
	@GetMapping("/adminLogin")
	public String showAdminLogin(@ModelAttribute("adminLogIn") AdminLogIn adminLogIn ,Model model) {
		
		model.addAttribute("adminLogin", new AdminLogIn() );
		
		return "AdminLogIn";
	}
	

	@PostMapping("/adminLogin")
	public String AdminLogin(@ModelAttribute("adminLogIn") AdminLogIn adminLogIn 
															,Model model) {
		
		boolean login = adminService.adminLogin(adminLogIn);
		
		if(login) {
			return "redirect:/adminFuel";
		}
		
		model.addAttribute("login", "Invalid Email or Pass");
		
		return "AdminLogIn";
	}
	
	
	
	
	@GetMapping("/adminFuel")
	public String showAdminFuelPage(Model model) {
		
		Integer petrol= 23;
		Integer disel= 25;
		Integer gas= 34;
		
		model.addAttribute("gas", gas);
		model.addAttribute("petrol", petrol);
		model.addAttribute("disel", disel);
		
		
		return "adminFuel";
	}
	
	
	
	//Adding Fuel...
	

	@GetMapping("/showFuel")
	public String showFuelPage(@ModelAttribute("fuelDto") FuelDto fuelDto,Model model) {
		
		model.addAttribute("fuelDto", new FuelDto() );
		
		return "addFuel";
	}
	
	
	@PostMapping("/submitFuel")
	public String AddFuel(@ModelAttribute("fuelDto") FuelDto fuelDto 
															,Model model) {
		
		String login = adminService.addFuel(fuelDto);
		model.addAttribute("login", login);
		
		return "addFuel";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
