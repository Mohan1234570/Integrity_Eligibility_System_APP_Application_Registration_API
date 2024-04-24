package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokit.entity.UserEntity;

@Repository
public interface CaseWorkerRepository extends JpaRepository<UserEntity, Integer>{
	public UserEntity findByUserId(Integer userId);
}
