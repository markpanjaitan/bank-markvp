package com.bank.markvp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bank.markvp.model.BankBalance;
import com.bank.markvp.model.BankTransaction;
import com.bank.markvp.repository.BankBalanceRepository;
import com.bank.markvp.repository.BankTransactionRepository;

@SpringBootApplication
public class BankMarkvpApplication implements CommandLineRunner {

	@Autowired
	private BankBalanceRepository bankBalanceRepository;
	
	@Autowired
	private BankTransactionRepository bankTransactionRepository;	

    public static void main(String[] args) {
    	
    	SpringApplication.run(BankMarkvpApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		List<BankBalance> listBalance = new ArrayList<BankBalance>();
		listBalance = bankBalanceRepository.findAll();
	
		System.out.println("-----------Test Hibernate Native Query---------");		
		if(listBalance.size() == 0) {
			
			// if local db is empty then create new sample data
	    	BankBalance bankBalance = new BankBalance();
	    	
	    	bankBalance.setAmount(new BigDecimal(10000));
	    	bankBalance.setLastUpdate(new Date());
	    	
	    	BankTransaction latestBankTrans = new BankTransaction();
	    	latestBankTrans.setAmount(new BigDecimal(5000));
	    	latestBankTrans.setBalanceId(Long.valueOf(1));
	    	latestBankTrans.setConcept("test1");
	    	latestBankTrans.setTime(new Date());	    	
	    	bankBalance.setLatestTransaction(latestBankTrans);
	    	bankBalanceRepository.save(bankBalance);			
	    	
	    	bankTransactionRepository.save(latestBankTrans);
	    	
	    	listBalance.clear();
	    	
	    	listBalance = bankBalanceRepository.findAll();
	    	
	        for(BankBalance bb : listBalance) {
	            System.out.println("ID : " + bb.getId());
	            System.out.println("Jumlah : " + bb.getAmount());
	            System.out.println("Last Update : " + bb.getLastUpdate());
	        }	    	
		}else if(listBalance.size() > 0) {
			listBalance = bankBalanceRepository.testNativeQuery(Long.valueOf(1));

	        for(BankBalance bb : listBalance) {
	            System.out.println("ID : " + bb.getId());
	            System.out.println("Jumlah : " + bb.getAmount());
	            System.out.println("Last Update : " + bb.getLastUpdate());
	        }
		}
        
	}

}
