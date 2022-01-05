package com.demo.wallet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.wallet.model.Transactions;
import com.demo.wallet.model.Users;
import com.demo.wallet.repository.TransactionsRepository;
import com.demo.wallet.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	TransactionsRepository transRepo;

	@Override
	public Users getUserProfile(String UserId) {
		Optional<Users> existingUser = usersRepository.findById(UserId);
		if(existingUser.isPresent()) {
			log.info(UserId+ "Present");
			return existingUser.get();
		}
		log.error("user id "+UserId+" not present");
		return null;
	}

	@Override
	public String addNewUser(Users user) {
		Optional<Users> existingUser = usersRepository.findById(user.getUserid());
		if(!existingUser.isEmpty()) {
			log.info("User " +user.getUserid() +" already exists");
			return "User already exists";
		}
		usersRepository.save(user);
		log.info("New user "+ user.getUserid() + " added");
		return "User "+user.getUserid()+" Added";
	}

	@Override
	public Users updateUser(Users user) {
		String updateUserId= user.getUserid();
		Optional<Users> existingUser = usersRepository.findById(updateUserId);
		if(existingUser.isEmpty()) {
			log.error("User with user id "+updateUserId+ " not found.");
			return null;
		}
		long initialBal = existingUser.get().getBalance();
		usersRepository.save(user);
		log.info("User "+updateUserId+" updated");
		long transactionAmount = user.getBalance() - initialBal;
		if(!(transactionAmount == 0)) {
		Transactions t = new Transactions();
		t.setUserId(updateUserId);
		t.setAmount(transactionAmount);
		t.setType("Account Update");
		t.setBalance(user.getBalance());
		transRepo.save(t);
		log.info("Transaction recorded");
		}
		return user;
	}

	@Override
	public String deleteUser(String UserId) {
		log.info("Deleting user with user id " + UserId);
		usersRepository.deleteById(UserId);
		return "User Deleted";
	}

	@Override
	public long addUserBalance(String UserId, long amount) {
		Users user = usersRepository.findById(UserId).get();
		long newBalance= user.getBalance()+ amount;
		user.setBalance(newBalance);
		usersRepository.save(user);
		log.info("Balance updated for user "+ UserId);
		Transactions t1 = new Transactions();
		t1.setUserId(UserId);
		t1.setAmount(amount);
		t1.setType("Wallet Deposit");
		t1.setBalance(newBalance);
		transRepo.save(t1);
		log.info("Wallet Deposit transaction recorded");
		return newBalance;
	}

	@Override
	public List<Users> getAllUsers() {
		log.info("Fetching all users");
		List<Users> allUsers= usersRepository.findAll();
		if (allUsers.isEmpty()) {
			log.info("No Users found");
		}
		return allUsers;
	}


	@Override
	public Users transferBalance(String userid1, String userid2, long amount) {
		Users user1 = usersRepository.findById(userid1).get();
		Optional<Users> user2 = usersRepository.findById(userid2);
		if(user2.isEmpty()) {
			log.error(userid2 + "does not exist");
			log.info("Balance Transfer cancelled");
			return user1;
		}
		long newUser1Bal = user1.getBalance() - amount;
		user1.setBalance(newUser1Bal);
		long newUser2Bal = user2.get().getBalance() + amount;
		user2.get().setBalance(newUser2Bal);
		
		usersRepository.save(user1);
		usersRepository.save(user2.get());
		
		log.info(amount+" transfered from user "+userid1+ " to user "+ userid2 );
		
		Transactions t1 = new Transactions();
		t1.setUserId(userid1);
		t1.setAmount(amount);
		t1.setType("TRANSFER - Withdrawn");
		t1.setBalance(newUser1Bal);
		
		Transactions t2 = new Transactions();
		t2.setUserId(userid2);
		t2.setAmount(amount);
		t2.setType("TRANSFER - Deposited");
		t2.setBalance(newUser2Bal);
		
		transRepo.save(t1);
		transRepo.save(t2);
		log.info("Transfer transaction saved");
		return user1;
	}


	

}
