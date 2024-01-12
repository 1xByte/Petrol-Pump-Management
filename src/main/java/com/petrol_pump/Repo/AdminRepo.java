package com.petrol_pump.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petrol_pump.Model.AdminEntity;

public interface AdminRepo extends JpaRepository<AdminEntity, Integer> {

	AdminEntity findByAdminEmail(String adminEmail);

	AdminEntity findByAdminId(Integer id);

}
