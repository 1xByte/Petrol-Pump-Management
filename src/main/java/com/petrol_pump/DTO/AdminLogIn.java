package com.petrol_pump.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminLogIn {

	@Email
	private String adminEmail;
	
	@NotNull
	private String adminPass;
}
