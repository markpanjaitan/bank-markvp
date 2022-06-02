package com.bank.markvp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.markvp.model.BankTransaction;

public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long>{

}
