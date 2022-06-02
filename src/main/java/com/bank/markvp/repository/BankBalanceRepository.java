package com.bank.markvp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.markvp.model.BankBalance;

@Repository
public interface BankBalanceRepository extends JpaRepository<BankBalance, Long> {

	@Query(value = "SELECT * FROM bank_balance bb "
			+ "WHERE bb.amount > 0 ", nativeQuery = true)
	List<BankBalance> testNativeQuery(Long idCustomer);

}
