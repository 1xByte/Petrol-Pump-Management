package com.petrol_pump.Service;

import com.petrol_pump.DTO.AdminLogIn;
import com.petrol_pump.DTO.AdminSignUp;
import com.petrol_pump.DTO.FuelDto;

public interface AdminService {
	
	String adminSignUp(AdminSignUp signUp);
	
	boolean adminLogin(AdminLogIn logIn);
	
	String addFuel(FuelDto fuelDto);

	String updateFuel(FuelDto fuelDto, Integer fuelId);
	
	

}
