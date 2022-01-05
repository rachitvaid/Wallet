package com.demo.wallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.demo.wallet.model.Transactions;
import com.demo.wallet.service.TransactionService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class TransactionsController {
	
	@Autowired
	private TransactionService transactionService;

	@GetMapping("/transactions")
	public List<Transactions> retrieveAllTransactions(){
		log.info("Retrieving all transactions");
		return transactionService.getAllTransactions();
	}
	
	@GetMapping("/transactions/{userid}")
	public List<Transactions> retrieveAllTransactionsByUser(@PathVariable String userid){
		log.info("Retrieving all transactions for user "+ userid);
		return transactionService.getAllTransactionsByUser(userid);
	}
	
	@GetMapping("/transactions/details/")
	public Transactions retrieveTransactionDetails(@RequestParam long id) {
		log.info("Fetching transaction details for transaction id "+ id);
		return transactionService.getTransactionDetails(id);
	}
}
