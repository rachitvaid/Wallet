package com.demo.wallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.demo.wallet.model.Users;
import com.demo.wallet.service.UsersService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping("/users")
	public List<Users> retrieveAllUsers(){
		log.info("Fetching details for all users");
		return usersService.getAllUsers();
	}
	
	@PostMapping("/users")
	public String addNewUser(@RequestBody Users user){
		log.info("Adding a new user");
		return usersService.addNewUser(user);
	}
	
	@PutMapping("/users")
	public Users updateUser(@RequestBody Users user) {
		log.info("Updating user data");
		return usersService.updateUser(user);
	}
	
	@DeleteMapping("/users")
	public String deleteUser(@RequestParam String userid) {
		log.info("Deleting user");
		return usersService.deleteUser(userid);
	}
	
	@GetMapping("/users/{userid}")
	public Users retrieveUserProfile(@PathVariable String userid){
		log.info("Fetching User details");
		return usersService.getUserProfile(userid);
	}
	
	@PutMapping("/users/{userid}")
	public Users transferBalance(@PathVariable String userid, @RequestParam long amount, @RequestParam String user) {
		log.info("Transfering money from " + userid + "to " + user);
		return usersService.transferBalance(userid, user, amount);
	}
	
	@PostMapping("/users/{userid}")
	public long addBalance(@PathVariable String userid,@RequestParam long amount) {
		log.info("Adding money to wallet for user" + userid);
		return usersService.addUserBalance(userid, amount);
	}
}
