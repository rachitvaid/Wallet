package com.demo.wallet.service;

import java.util.List;

import com.demo.wallet.model.Transactions;

public interface TransactionService {
	
	public Transactions getTransactionDetails(long TransactionId);
	public List<Transactions> getAllTransactionsByUser(String UserId);
	public List<Transactions> getAllTransactions();
	

}
