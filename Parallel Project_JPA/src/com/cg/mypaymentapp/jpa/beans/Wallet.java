package com.cg.mypaymentapp.jpa.beans;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Customer_wallet")
public class Wallet implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="wallet_balance")
private BigDecimal balance;

public Wallet(BigDecimal amount) {
	this.balance=amount;
}

public BigDecimal getBalance() {
	return balance;
}

public void setBalance(BigDecimal balance) {
	this.balance = balance;
}

@Override
	public String toString() {
	return ", balance="+balance;
}

public Wallet() {
	super();
	// TODO Auto-generated constructor stub
}



}
