package com.petrol_pump.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.petrol_pump.DTO.OrderDto;
import com.petrol_pump.DTO.UserDashboardResponse;
import com.petrol_pump.DTO.UserLogin;
import com.petrol_pump.DTO.UserSignUp;
import com.petrol_pump.Model.CustomerEntity;
import com.petrol_pump.Model.FuelEntity;
import com.petrol_pump.Model.UserEntity;
import com.petrol_pump.Repo.CustomerRepo;
import com.petrol_pump.Repo.FuelRepo;
import com.petrol_pump.Repo.UserRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private FuelRepo fuelRepo;
	
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public String addUser(UserSignUp userSignUp) {
		
		if(userSignUp.getUserEmail() == null || userSignUp.getUserEmail().isEmpty()) {
				return "Invalid Mail or Pass..";
			}
		
		if(userSignUp.getUserName() == null || userSignUp.getUserName().isEmpty()) {
			return "Invalid Mail or Pass..";
		}
		
		UserEntity entity = UserEntity.builder()
				  .userName(userSignUp.getUserName())
				  .userEmail(userSignUp.getUserEmail())
				  .userPass(userSignUp.getUserPass()).build();
		
		userRepo.save(entity);
		
		return "User Added Successfully";}
	

	@Override
	public boolean userLogIn(UserLogin userLogin) {
		
		UserEntity entity = userRepo.findByUserEmail(userLogin.getUserEmail());
		
		try {	
			if(entity == null) {
				return false;
			}
			
			if(!entity.getUserPass().equals(userLogin.getUserPass())) {
				return false;
			}}catch (Exception e) {
				e.printStackTrace();
			}
			
			httpSession.setAttribute("userid", entity.getUserId());
			
			return true;
			
			}
		
	


	@Override
	public String sendSlip(CustomerEntity order) {
		return null;
	}

	
	
	@Override
	public String customerOrder(OrderDto orderDto) {
		
	     Integer userId =(Integer)  httpSession.getAttribute("userid");
		
		UserEntity byUserId = userRepo.findByUserId(userId);
		
		FuelEntity fuel = fuelRepo.findByFuelName(orderDto.getFuelName());
		
		CustomerEntity customerEntity = CustomerEntity.builder()
					  .fuelQuantity(orderDto.getFuelQuantity())
					  .fuelName(orderDto.getFuelName())
					  .userEntity(byUserId)
					  .fuelEntity(fuel)
					  .build();
		
		customerRepo.save(customerEntity);
		
		
		if(fuel.getFuelQuantity() < orderDto.getFuelQuantity()) {
			
			return "Invalid Amount";		}
		
		Long v=	fuel.getFuelQuantity() - orderDto.getFuelQuantity();
		
		fuel.setFuelQuantity(v);
		fuelRepo.save(fuel);
		
		return "Ordered Successfully";
	}


	@Override
	public UserDashboardResponse showDashboardd(Integer userId) {
		
		List<FuelEntity> all = fuelRepo.findAll();
		long count = customerRepo.count();

		UserDashboardResponse response = UserDashboardResponse.builder()
								.fuel(all)
								.totalOrder(count).build();
		
		
		
		return response;
	}
	


	
}
