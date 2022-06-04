package com.bank.markvp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bank.markvp.ResourceNotFoundException;

import com.bank.markvp.model.BankBalance;
import com.bank.markvp.service.BankBalanceService;

@RestController
@RequestMapping("/bank-balance")
public class BankBalanceController {

	@Autowired
    private BankBalanceService bankBalanceService;

    @GetMapping(value = "/local/{bankBalanceId}")
    @Cacheable(value = "BankBalance", key = "#bankBalanceId")
    public BankBalance getBankBalanceById(@PathVariable("bankBalanceId") Long bankBalanceId) throws Exception{
        return bankBalanceService.getBankBalanceById(bankBalanceId);
    }
    
    @GetMapping(value = "/store/{bankBalanceId}", produces = "application/json")
    public ResponseEntity<BankBalance> getBankBalanceByIdFromStore(@PathVariable("bankBalanceId") Long bankBalanceId){
    	
        return ResponseEntity.ok(bankBalanceService.getBankBalanceByIdFromStore(bankBalanceId));
    }    
}