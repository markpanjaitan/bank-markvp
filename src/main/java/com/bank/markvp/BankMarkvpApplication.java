package com.bank.markvp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.bank.markvp.model.BankBalance;
import com.bank.markvp.model.BankTransaction;
import com.bank.markvp.repository.BankBalanceRepository;
import com.bank.markvp.repository.BankTransactionRepository;

@SpringBootApplication
@EnableCaching
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
		
		if(listBalance.size() == 0) {
			
			// if local db is empty then create new sample data	    	
	    	BankTransaction trans1 = new BankTransaction();
	    	trans1.setAmount(new BigDecimal(5000));
	    	trans1.setBalanceId(Long.valueOf(1));
	    	trans1.setConcept("transaksi1");
	    	trans1.setTime(new Date());
	    	
	    	BankTransaction trans2 = new BankTransaction();
	    	trans2.setAmount(new BigDecimal(2500));
	    	trans2.setBalanceId(Long.valueOf(1));
	    	trans2.setConcept("transaksi2");
	    	trans2.setTime(new Date());	    	
	    	
	    	BankTransaction trans3 = new BankTransaction();
	    	trans3.setAmount(new BigDecimal(1000));
	    	trans3.setBalanceId(Long.valueOf(1));
	    	trans3.setConcept("transaksi3");
	    	trans3.setTime(new Date());	
	    	
	    	BankBalance bankBalance1 = new BankBalance();
	    	bankBalance1.setLatestTransaction(trans3);		    	
	    	bankBalance1.setAmount(new BigDecimal(8500));
	    	bankBalance1.setLastUpdate(new Date());
	    	bankBalanceRepository.save(bankBalance1);
	    	
	    	bankTransactionRepository.save(trans1);
	    	bankTransactionRepository.save(trans2);	    
	    	bankTransactionRepository.save(trans3);	    	
	    	
	    	listBalance.clear();
	    	
	    	listBalance = bankBalanceRepository.findAll();
	    	
	        for(BankBalance bb : listBalance) {
	        	System.out.println("Create sample data to local db..");
	            System.out.println("ID : " + bb.getId());
	            System.out.println("Jumlah : " + bb.getAmount());
	            System.out.println("Last Update : " + bb.getLastUpdate());
	        }	    	
		}
        
	}

}
