package com.bank.markvp.service;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.markvp.model.BankBalance;
import com.bank.markvp.topology.BankBalanceTopology;

@Service
public class BankBalanceService {

    private final KafkaStreams kafkaStreams;

    @Autowired
    public BankBalanceService(KafkaStreams kafkaStreams) {
        this.kafkaStreams = kafkaStreams;
    }

    public BankBalance getBankBalanceById(Long bankBalanceId) {
        return getStore().get(bankBalanceId);
    }

    private ReadOnlyKeyValueStore<Long, BankBalance> getStore() {
    	// get data from db
        return kafkaStreams.store(
                StoreQueryParameters.fromNameAndType(
                        BankBalanceTopology.BANK_BALANCES_STORE,
                        QueryableStoreTypes.keyValueStore()
                )
        );
    }
}