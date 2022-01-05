package com.demo.wallet.service;

import com.demo.wallet.model.Credentials;
import com.demo.wallet.model.LoginCreds;

public interface CredentialsService {
	public String loginUser(LoginCreds credentials);
	public String logupUser(Credentials credentials);
	

}
