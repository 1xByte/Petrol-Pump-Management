package com.petrol_pump.DTO;

import java.util.List;

import com.petrol_pump.Model.FuelEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserDashboardResponse {
	
	private List<FuelEntity> fuel;
	
	private Long totalOrder;
		

}
