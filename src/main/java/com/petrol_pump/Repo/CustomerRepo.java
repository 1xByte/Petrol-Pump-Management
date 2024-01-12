package com.petrol_pump.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petrol_pump.Model.CustomerEntity;

public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer>{

}
