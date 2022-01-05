package com.demo.wallet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.wallet.model.Transactions;
import com.demo.wallet.repository.TransactionsRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	TransactionsRepository transactionsRepository;

	@Override
	public Transactions getTransactionDetails(long TransactionId) {
		return transactionsRepository.findById(TransactionId).get();
	}

	@Override
	public List<Transactions> getAllTransactionsByUser(String UserId) {
		return transactionsRepository.findAllTransactions(UserId);
	}

	@Override
	public List<Transactions> getAllTransactions() {
		return transactionsRepository.findAll();
	}


}
