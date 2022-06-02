package com.bank.markvp.topology;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;

import com.bank.markvp.model.BankBalance;
import com.bank.markvp.model.BankTransaction;
import com.bank.markvp.model.JsonSerde;

public class BankBalanceTopology {

	public static final String BANK_TRANSACTIONS = "bank-transactions";
	public static final String BANK_BALANCES = "bank-balances";
	public static final String REJECTED_TRANSACTIONS = "rejected-transactions";
	public static final String BANK_BALANCES_STORE = "bank-balances-store";

	public static Topology buildTopology() {
		Serde<BankTransaction> bankTransactionSerdes = new JsonSerde<>(BankTransaction.class);
		Serde<BankBalance> bankBalanceSerde = new JsonSerde<>(BankBalance.class);
		StreamsBuilder streamsBuilder = new StreamsBuilder();

		KStream<Long, BankBalance> bankBalancesStream = streamsBuilder
				.stream(BANK_TRANSACTIONS, Consumed.with(Serdes.Long(), bankTransactionSerdes)).groupByKey()
				.aggregate(BankBalance::new, (key, value, aggregate) -> aggregate.process(value),
						Materialized.<Long, BankBalance, KeyValueStore<Bytes, byte[]>>as(BANK_BALANCES_STORE)
								.withKeySerde(Serdes.Long()).withValueSerde(bankBalanceSerde))
				.toStream();

		bankBalancesStream.to(BANK_BALANCES, Produced.with(Serdes.Long(), bankBalanceSerde));

		bankBalancesStream.mapValues((readOnlyKey, value) -> value.getLatestTransaction())
				.filter((key, value) -> value.state == BankTransaction.BankTransactionState.REJECTED)
				.to(REJECTED_TRANSACTIONS, Produced.with(Serdes.Long(), bankTransactionSerdes));

		return streamsBuilder.build();
	}
}
