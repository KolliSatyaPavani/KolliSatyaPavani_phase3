package com.cg.mypaymentapp.jpa.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Transactions")
public class Transactions implements Serializable{
private static final long serialVersionUID = 1L;
    @Id
	String mobileNumber;
	String transactionType;
	BigDecimal amount;
	String TransactionDate;
	//long miilis=System.currentTimeMillis();
	public Transactions() {
		super();
	}	
public String getTransactionDate() {
		return TransactionDate;
	}
public void setTransactionDate(String transactionDate) {
		TransactionDate = transactionDate;
	}
public Transactions(String mobileNumber, String transactionType, BigDecimal amount) {
		super();
		this.mobileNumber = mobileNumber;
		this.transactionType = transactionType;
		this.amount = amount;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Transactions [mobileNumber=" + mobileNumber + ", transactionType=" + transactionType + ", amount="
				+ amount + ", TransactionDate=" + TransactionDate + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transactions other = (Transactions) obj;
		if (mobileNumber == null) {
			if (other.mobileNumber != null)
				return false;
		} else if (!mobileNumber.equals(other.mobileNumber))
			return false;
		return true;
	}
	public Transactions(String mobileNumber, String transactionType, BigDecimal amount, String transactionDate) {
		super();
		this.mobileNumber = mobileNumber;
		this.transactionType = transactionType;
		this.amount = amount;
		TransactionDate = transactionDate;
	}
	
	
	
	
	
	
}
