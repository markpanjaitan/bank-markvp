package com.bank.markvp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
@Builder(toBuilder = true)
@Entity
@Table(name = "bank_transaction")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class BankTransaction implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "balance_id")
	private Long balanceId;

	@Column(name = "concept")
	private String concept;

	@Column(name = "amount")
	private BigDecimal amount;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	public Date time;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "latestTransaction")
	@JsonIgnore
	private BankBalance bankBalance;

	@Builder.Default
	@JsonIgnore
	public BankTransactionState state = BankTransactionState.CREATED;

	/*
	@Override
	public int compareTo(BankTransaction o) {
		var r = o.time.compareTo(this.time);
		if (r == 0)
			return o.id.compareTo(this.id);
		return r;
	}
	*/

	public static enum BankTransactionState {
		CREATED, APPROVED, REJECTED
	}
}
