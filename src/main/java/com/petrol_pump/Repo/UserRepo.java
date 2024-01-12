package com.petrol_pump.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petrol_pump.Model.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	UserEntity findByUserEmail(String userEmail);

	UserEntity findByUserId(Integer userId);

}
