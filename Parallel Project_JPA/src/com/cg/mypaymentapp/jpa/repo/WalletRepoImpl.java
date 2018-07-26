package com.cg.mypaymentapp.jpa.repo;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import com.cg.mypaymentapp.jpa.beans.Customer;
import com.cg.mypaymentapp.jpa.exception.InvalidInputException;
import com.cg.mypaymentapp.jpa.util.MJPAUtil;

public class WalletRepoImpl implements WalletRepo{

private EntityManager entityManager;
	

	public WalletRepoImpl() {
	entityManager = MJPAUtil.getEntityManager();}
	
	private Map<String, Customer> data ; 
	public WalletRepoImpl(Map<String, Customer> data) {
		super();
		this.data = data;
	}
	//public WalletRepoImpl()
	//{
	//	data = new HashMap<String, Customer>();
	//}

	public boolean save(Customer customer) {
		
		
		//Customer c = findOne(customer.getMobileNo());
		//if(c== null)
		
		try {
			entityManager.persist(customer);
			}
		catch(Exception e)
		{
			throw new InvalidInputException(e.getMessage());
		}
		return true;
		
		
	}

	public Customer findOne(String mobileNo) {
		//Customer customer = data.get(mobileNo);
		Customer customer = entityManager.find(Customer.class,mobileNo);
		
		if(customer == null)
			throw new InvalidInputException("Customer not found with mobileNo"+mobileNo);
			return customer;
		}
	@Override
	public Customer updateBalance(Customer customer)  {
		
		entityManager.merge(customer);
		return customer;
		
	}
	@Override
	public void startTransaction() {
		entityManager.getTransaction().begin();
		
	}

	@Override
	public void commitTransaction() {
		entityManager.getTransaction().commit();
		
	}
	
	
	
	
}
