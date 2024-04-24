package com.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.binding.RegisterForm;
import com.ashokit.service.RegisterService;


@RestController
public class RegisterRestController {
	@Autowired
	private RegisterService service;

	@PostMapping(value = "/register")
	public ResponseEntity<String> createUser(@RequestBody RegisterForm form){

		String status = service.saveRegister(form);

		return new ResponseEntity<>(status,HttpStatus.CREATED); 

	}

}
