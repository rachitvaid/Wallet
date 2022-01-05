package com.demo.wallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.wallet.model.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions,Long> {

	@Query(value = "SELECT u FROM Transactions u where u.userId = ?1")
	List<Transactions> findAllTransactions(String userId);
	
}
