package com.bank.markvp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.markvp.model.BankTransaction;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long>{
	
	@Query(value = "SELECT * FROM bank_transaction bt "
			+ "WHERE bt.balance_id = :bankBalanceId ORDER BY bt.time DESC ", nativeQuery = true)
	List<BankTransaction> findAllByBalanceId(Long bankBalanceId);

}
