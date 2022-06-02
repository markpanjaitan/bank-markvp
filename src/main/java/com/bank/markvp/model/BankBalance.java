package com.bank.markvp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bank_balance")
public class BankBalance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "amount")
	private BigDecimal amount = BigDecimal.ZERO;

	@Column(name = "last_update")
	private Date lastUpdate;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @Column(name = "latest_transaction")
	@JoinColumn(name = "latest_transaction")
	private BankTransaction latestTransaction;

	public BankBalance process(BankTransaction bankTransaction) {

		this.id = bankTransaction.getBalanceId();
		this.latestTransaction = bankTransaction;

		if (this.amount.add(bankTransaction.getAmount()).compareTo(BigDecimal.ZERO) >= 0) {
			this.latestTransaction.setState(BankTransaction.BankTransactionState.APPROVED);
			this.amount = this.amount.add(bankTransaction.getAmount());
		} else {
			this.latestTransaction.setState(BankTransaction.BankTransactionState.REJECTED);
		}
		this.lastUpdate = bankTransaction.getTime();

		return this;
	}

}