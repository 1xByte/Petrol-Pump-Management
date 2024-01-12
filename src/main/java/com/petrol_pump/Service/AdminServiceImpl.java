package com.petrol_pump.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petrol_pump.DTO.AdminLogIn;
import com.petrol_pump.DTO.AdminSignUp;
import com.petrol_pump.DTO.FuelDto;
import com.petrol_pump.Model.AdminEntity;
import com.petrol_pump.Model.FuelEntity;
import com.petrol_pump.Repo.AdminRepo;
import com.petrol_pump.Repo.FuelRepo;

import jakarta.servlet.http.HttpSession;
@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private FuelRepo fuelRepo;
	
	@Autowired
	private HttpSession httpSession;

	@Override
	public String adminSignUp(AdminSignUp signUp) {
		
		
		AdminEntity email = adminRepo.findByAdminEmail(signUp.getAdminEmail());
		
		try {
		
		if(signUp.getAdminEmail() == null || signUp.getAdminEmail().isEmpty()) {
			return "Invalid Mail or Pass..";
		}
			
		if(null != email) {
			
			return "Email is Already Resistered, Login ";
		
		}
		
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		AdminEntity entity = AdminEntity.builder()
				   .adminName(signUp.getAdminName())
				   .adminEmail(signUp.getAdminEmail())
				   .adminPass(signUp.getAdminPass())
				   .build();
		
		adminRepo.save(entity);
	
		return "SignUp Successed";}
	

	@Override
	public boolean adminLogin(AdminLogIn logIn) {

		AdminEntity entity = adminRepo.findByAdminEmail(logIn.getAdminEmail());
	try {	
	if(entity == null) {
		return false;
	}
	
	if(!entity.getAdminPass().equals(logIn.getAdminPass())) {
		return false;
	}}catch (Exception e) {
		e.printStackTrace();
	}
	
	
	return true;
	
	}

	
	
	// Add Fuel...
	
	
	@Override
	public String addFuel(FuelDto fuelDto) {
		
		
		Integer id = (Integer)httpSession.getAttribute("adminid");
				
		AdminEntity ent = adminRepo.findByAdminId(id);
		FuelEntity entName = fuelRepo.findByFuelName(fuelDto.getFuelName());
		
		if(entName == null || entName.getFuelName().isEmpty()) {

		FuelEntity fuelEntity = FuelEntity.builder()
					.fuelName(fuelDto.getFuelName())
					.fuelQuantity(fuelDto.getFuelQuantity())
					.fuelPrice(fuelDto.getFuelPrice())
					.entity(ent)
					.build();
		
		fuelRepo.save(fuelEntity);
		
		
		return "Fuel Added..";}
		
		else {
			
			entName.setFuelPrice(fuelDto.getFuelPrice());
			Long qnt= entName.getFuelQuantity() + fuelDto.getFuelQuantity();
			entName.setFuelQuantity(qnt);
		
			fuelRepo.save(entName);
			
			return "Fuel Details Updated..";
		}
	}
	
	
	
	@Override
	public String updateFuel(FuelDto fuelDto, Integer fuelId) {

		FuelEntity fuel = fuelRepo.findByFuelId(fuelId);
		fuel.setFuelQuantity(fuelDto.getFuelQuantity());
		fuel.setFuelPrice(fuelDto.getFuelPrice());
		
		fuelRepo.save(fuel);
		
		
		return "Fuel Updated..";
	}

}
