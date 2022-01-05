package com.demo.wallet.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.wallet.model.Credentials;
import com.demo.wallet.model.LoginCreds;
import com.demo.wallet.repository.CredentialsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CredentialsServiceImpl implements CredentialsService {
	
	
	@Autowired
	CredentialsRepository credRepo;
	
	@Override
	public String loginUser(LoginCreds credentials) {
		Optional<Credentials> userCreds= credRepo.findById(credentials.getUsername());
		if (userCreds.isEmpty()) {
			log.info("User does not exist");
			return "User "+credentials.getUsername()+" not found";
		}
		if(userCreds.get().getPassword().equals(credentials.getPassword())) {
			log.info("Password match");
			return "Login Successful.";
		}
		else {
			log.error("Password incorrect");
			return "Login Unsuccessful";
		}
	}

	@Override
	public String logupUser(Credentials credentials) {
		Optional<Credentials> existingUser= credRepo.findById(credentials.getUsername());
		if(existingUser.isPresent()) {
			log.info("User already exists");
			return "Username exists, Please login";
		}
		credRepo.save(credentials);
		return "Logup Successful, User created";
	}

}
