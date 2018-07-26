package com.cg.mypaymentapp.jpa.repo;

import com.cg.mypaymentapp.jpa.beans.Customer;

public interface WalletRepo {

public boolean save(Customer customer);
	
	public Customer findOne(String mobileNo);
	public Customer updateBalance(Customer customer);
	public void startTransaction();
	public void commitTransaction();
		
}
