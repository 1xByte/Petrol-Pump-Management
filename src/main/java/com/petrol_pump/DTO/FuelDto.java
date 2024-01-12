package com.petrol_pump.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FuelDto {

	private String fuelName;
	
	private Long fuelQuantity;
	
	private Long fuelPrice;
}
