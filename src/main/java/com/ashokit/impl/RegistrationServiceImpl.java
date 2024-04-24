package com.ashokit.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ashokit.binding.RegisterForm;
import com.ashokit.entity.RegistrationEntity;
import com.ashokit.entity.UserEntity;
import com.ashokit.repo.CaseWorkerRepository;
import com.ashokit.repo.RegisterRepository;
import com.ashokit.service.RegisterService;

@Service
public class RegistrationServiceImpl implements RegisterService {

	@Autowired
	private RegisterRepository repo;
	
	@Autowired
	private CaseWorkerRepository userRepo;


	private String SAVE_REGISTERPLAN_URL = "http://65.2.166.5:8080/ssa/citizen";



	@Override
	public String saveRegister(RegisterForm form) {
		Integer sequence = repo.getSequence();
		if(sequence==null) {
			sequence=1234;
		}else {
			sequence++;
		}
		WebClient webClient = WebClient.create();

		RegistrationEntity status = webClient.post().uri(SAVE_REGISTERPLAN_URL).header("Accept", "application/json")
				.bodyValue(form).retrieve().bodyToMono(RegistrationEntity.class).block();


		if (status.getState().equalsIgnoreCase("Rhode Island")) {
			RegistrationEntity entity = new RegistrationEntity();
			entity.setCity(status.getCity());
			entity.setDob(status.getDob());
			entity.setFullname(status.getFullname());
			entity.setGender(status.getGender());
			entity.setHouseNum(status.getHouseNum());
			entity.setSsn(status.getSsn());
			entity.setState(status.getState());
			//BeanUtils.copyProperties(form, status);
			UserEntity userEntity = userRepo.findByUserId(form.getUserId());
			System.out.println("userEntity ####:"+userEntity.getUserId());
			entity.setUserId(userEntity.getUserId());
			entity.setMobile(form.getMobile());
			entity.setEmail(form.getEmail());
			entity.setCaseNumber(sequence);
			repo.save(entity);

			return "citizen allowed";
		}

		return "citizen not allowed";
	}
}