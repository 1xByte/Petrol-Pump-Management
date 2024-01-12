package com.petrol_pump.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petrol_pump.Model.FuelEntity;

public interface FuelRepo extends JpaRepository<FuelEntity, Integer> {

	FuelEntity findByFuelId(Integer fuelId);

	FuelEntity findByFuelName(String fuelName);

}
