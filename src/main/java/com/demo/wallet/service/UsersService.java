package com.demo.wallet.service;

import java.util.List;

import com.demo.wallet.model.Users;

public interface UsersService {
	
	public Users getUserProfile(String UserId);
	public String addNewUser(Users user);
	public Users updateUser(Users user);
	public String deleteUser(String UserId);
	public List<Users> getAllUsers();
	public Users transferBalance(String userid1, String userid2, long amount);
	public long addUserBalance(String UserId, long amount);


}

