package com.demo.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.demo.wallet.model.Credentials;
import com.demo.wallet.model.LoginCreds;
import com.demo.wallet.service.CredentialsService;
import com.demo.wallet.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LoginController {
	
	@Autowired
	private CredentialsService credService;
	

	
	@PostMapping("/login")
	public String userLogin(@RequestBody LoginCreds loginCreds) {
		return credService.loginUser(loginCreds);
	}
	
	@PostMapping("/logup")
	public String userLogup(@RequestBody Credentials creds) {
		return credService.logupUser(creds);
	}

}
