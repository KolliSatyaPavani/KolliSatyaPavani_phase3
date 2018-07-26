
package com.cg.mypaymentapp.jpa.service;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

import com.cg.mypaymentapp.jpa.beans.Customer;
import com.cg.mypaymentapp.jpa.beans.Wallet;
import com.cg.mypaymentapp.jpa.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.jpa.exception.InvalidInputException;
import com.cg.mypaymentapp.jpa.repo.WalletRepo;
import com.cg.mypaymentapp.jpa.repo.WalletRepoImpl;
;


public class WalletServiceImpl implements WalletService{

private WalletRepo repo=new WalletRepoImpl();
WalletRepo repo1 = new WalletRepoImpl();
	
	public WalletServiceImpl(Map<String, Customer> data){
		repo= new WalletRepoImpl(data);
	}

	public WalletServiceImpl(WalletRepo repo) 
	{
		this.repo = repo;
	}

	public WalletServiceImpl() {
		repo1 = new WalletRepoImpl();
		
	}

	public Customer createAccount(String name, String mobileNo, BigDecimal amount) {
		if(name==null | mobileNo==null | amount==null| amount==new BigDecimal(0))
			throw new NullPointerException();
		
		Customer customer1 = new Customer();
		customer1.setName(name);
		customer1.setMobileNo(mobileNo);
		Wallet wallet = new Wallet();
		wallet.setBalance(amount);
		customer1.setWallet(wallet);
		repo.startTransaction();
		boolean b =repo.save(customer1);
		repo.commitTransaction();
		if(b)
		return customer1;
		else
			throw new InvalidInputException("Account is already created");
			
			

	}
	public Customer showBalance(String mobileNo) {
		repo.startTransaction();
		Customer customer=repo.findOne(mobileNo);
		repo.commitTransaction();
		if(customer!=null)
			return customer;
		else
			throw new InvalidInputException("Invalid mobile no ");
	}

	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) {
		if(sourceMobileNo.equals(targetMobileNo))
		{
			throw new InvalidInputException("Source and target mobile numbers are equal");
		}
		
		repo.startTransaction();
		Customer customer2=repo.findOne(sourceMobileNo);
		//repo.commitTransaction();
		//repo.startTransaction();
		Customer customer3=repo.findOne(targetMobileNo);
		repo.commitTransaction();
		
        if(customer2!=null||customer3!= null) {
		BigDecimal total2;
		BigDecimal total3;
		BigDecimal x;
		total2 = customer2.getWallet().getBalance();
		total3 = customer3.getWallet().getBalance();
		if((total2.compareTo(amount)>0))
		{
			total2=total2.subtract(amount);
		total3=total3.add(amount);
		
		customer2.setWallet(new Wallet(total2));
		customer3.setWallet(new Wallet(total3));
		repo.startTransaction();
		Customer cust=		repo.updateBalance(customer2);
		//repo.commitTransaction();
		//repo.startTransaction();
		Customer cust1=	repo.updateBalance(customer3);
		repo.commitTransaction();
		}
		else
			System.out.println("Insufficient balance to transfer");
        }
        else
        	throw new InvalidInputException("Invalid mobile no ");
		return customer2;
		
	}

	public Customer depositAmount(String mobileNo, BigDecimal amount) {
		repo.startTransaction();
		Customer customer=repo.findOne(mobileNo);
		repo.commitTransaction();
		BigDecimal total;
		if(customer!=null) {
		total = customer.getWallet().getBalance();
		total=total.add(amount);
		System.out.println(total);
		customer.setWallet(new Wallet(total));
		repo.startTransaction();
		Customer cust = repo.updateBalance(customer);
		repo.commitTransaction();
		}
		else
			throw new InvalidInputException("Invalid mobile no ");
		return customer;
	}

	public Customer withdrawAmount(String mobileNo, BigDecimal amount) {
		repo.startTransaction();
		Customer customer=repo.findOne(mobileNo);
		repo.commitTransaction();
		BigDecimal total;
		if(customer!=null) {
		total = customer.getWallet().getBalance();
		if((total.compareTo(amount)>0)) {
		total=total.subtract(amount);
		System.out.println(total);
		customer.setWallet(new Wallet(total));
		repo.startTransaction();
		Customer cust = repo.updateBalance(customer);
		repo.commitTransaction();
		}
		else
			System.out.println("Insufficient balance to withdraw");
		}
		else
			throw new InvalidInputException("Invalid mobile no ");
		return customer;
	}
	public boolean isValid() throws InvalidInputException
	{
		Customer customer=new Customer();
		if( customer.getMobileNo() == null ||  isPhoneNumbervalid( customer.getMobileNo() ) )
			throw new InvalidInputException( "Phone Number is invalid" );
		if(customer.getName()==null || isNameValid(customer.getName()))
			throw new InvalidInputException( "Name is invalid");
		return true;
	}
		public boolean isPhoneNumbervalid( String phoneNumber )
		{
			if(phoneNumber.matches("[1-9][0-9]{9}")) 
			{
				return true;
			}		
			else 
				return false;
		}
		public boolean isNameValid(String name)
		{
			if(name.matches("^[a-zA-Z]"))
			{
				return true;
			}
			else
				return false;
		}
		public boolean isAmountValid(BigDecimal amount)
		{
			int val=amount.compareTo(new BigDecimal("0"));
			if(val==0|| val<0)
				return false;
			return true;
		}
		}	
		


