package com.petrol_pump.Cont;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.petrol_pump.DTO.AdminSignUp;
import com.petrol_pump.DTO.OrderDto;
import com.petrol_pump.DTO.UserDashboardResponse;
import com.petrol_pump.DTO.UserLogin;
import com.petrol_pump.DTO.UserSignUp;
import com.petrol_pump.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession httpSession;
	
	@GetMapping("/userSignup")
	public String showAdminSignUp(@ModelAttribute("signUp") UserSignUp signUp,Model model) {
		
		model.addAttribute("userSignup", new UserSignUp() );
		
		return "UserSignUp";
	}
	
	
	@PostMapping("/userSignup")
	public String UserSignUp(@ModelAttribute("signUp") UserSignUp signUp 
															,Model model) {
		
		String addUser = userService.addUser(signUp);
		model.addAttribute("signUp", addUser);
		
		return "UserSignUp";
	}
	
	
	@GetMapping("/userLogin")
	public String showAdminLogin(@ModelAttribute("login") UserLogin login,Model model) {
		
		model.addAttribute("adminLogin", new UserLogin() );
		
		return "UserLogIn";
	}

	@PostMapping("/userLogin")
	public String UserLogin(@ModelAttribute("login") UserLogin login 
															,Model model) {
	
		boolean userLogIn = userService.userLogIn(login);
		
		
		if(userLogIn) {
			
			return "redirect:/UserDashboard";
		}
		
		model.addAttribute("loginn", "Invalid Email or Pass");
		
		return "UserLogIn";
		
	}
	
	
	// Customer Order....
	
	
	@GetMapping("/order")
	public String showCustomerOrder(@ModelAttribute("orderDto") OrderDto orderDto,Model model) {
		
		model.addAttribute("orderDto",new OrderDto());
		
		return "customerFuel";
	}
	
	
	@PostMapping("/order")
	public String CustomerOrder(@ModelAttribute("orderDto") OrderDto orderDto ,Model model) {
		
		if(orderDto.getFuelQuantity() == null || orderDto.getFuelQuantity() == 0) {
			
			model.addAttribute("msg", "Invalid");
		}
		
		else {
			String order = userService.customerOrder(orderDto);
			model.addAttribute("order", order);
		}
		
		return "customerFuel";
	}
	
	

	@GetMapping("/UserDashboard")
	public String showUserDashboard(@ModelAttribute("dashboardResponse") UserDashboardResponse dashboardResponse ,Model model) {
		
		Integer id=	(Integer)httpSession.getAttribute("userid");
		
		UserDashboardResponse dashboard = userService.showDashboardd(id);
		
		Long order = dashboard.getTotalOrder();
		
		model.addAttribute("order", order);
		
		return "UserDashboard";
	}
	
	
	
	
	@GetMapping("/logout")
	public String UserLogout(Model model) {
		
		httpSession.invalidate();
		
		return "index";
	}
	
	
	
	
	
}
