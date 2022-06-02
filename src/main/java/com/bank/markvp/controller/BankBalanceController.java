package com.bank.markvp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.markvp.model.BankBalance;
import com.bank.markvp.service.BankBalanceService;

@RestController
@RequestMapping("/bank-balance")
public class BankBalanceController {

    private final BankBalanceService bankBalanceService;

    @Autowired
    public BankBalanceController(BankBalanceService bankBalanceService) {
        this.bankBalanceService = bankBalanceService;
    }

    @GetMapping(value = "/{bankBalanceId}", produces = "application/json")
    public ResponseEntity<BankBalance> getBankBalanceById(@PathVariable("bankBalanceId") Long bankBalanceId){
        return ResponseEntity.ok(bankBalanceService.getBankBalanceById(bankBalanceId));
    }
}