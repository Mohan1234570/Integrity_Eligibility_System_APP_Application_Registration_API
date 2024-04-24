package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.entity.RegistrationEntity;

public interface RegisterRepository extends JpaRepository<RegistrationEntity, Integer> {

	@Query(value = "select max(case_number) from registration_entity",nativeQuery=true)
	public Integer getSequence();
}
