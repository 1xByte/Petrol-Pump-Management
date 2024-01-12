package com.petrol_pump.Service;


import java.util.List;

import com.petrol_pump.DTO.OrderDto;
import com.petrol_pump.DTO.UserDashboardResponse;
import com.petrol_pump.DTO.UserLogin;
import com.petrol_pump.DTO.UserSignUp;
import com.petrol_pump.Model.CustomerEntity;

public interface UserService {
	
	String addUser(UserSignUp userSignUp);
	
	boolean userLogIn(UserLogin userLogin);
	
	String sendSlip(CustomerEntity order);

	String customerOrder(OrderDto orderDto);
	
	UserDashboardResponse showDashboardd(Integer userId);
}
