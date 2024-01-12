package com.petrol_pump.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	
	private String userName;
	
	private String userEmail;
	
	private String userPass;
	
	@OneToOne
	private FuelEntity fuelEntity;
	
	@OneToMany(mappedBy = "userEntity")
	private List<CustomerEntity> customerEntity;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public FuelEntity getFuelEntity() {
		return fuelEntity;
	}

	public void setFuelEntity(FuelEntity fuelEntity) {
		this.fuelEntity = fuelEntity;
	}

	public List<CustomerEntity> getCustomerEntity() {
		return customerEntity;
	}

	public void setCustomerEntity(List<CustomerEntity> customerEntity) {
		this.customerEntity = customerEntity;
	}
	
	
}
