package com.br.internet.qualiti.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "balance")
	private Double balance;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="customer_id")
	private Customer customer;

	public Account() {
		
	}

	public Account(String number, Double balance, Customer customer) {
		this.number = number;
		this.balance = balance;
		this.customer = customer;
	
	}
	public Account(int id, String number, Double balance, Customer customer) {
		super();
		this.id = id;
		this.number = number;
		this.balance = balance;
		this.customer = customer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public Double getBalance() {
		return balance;
	}

	public Customer getCustomer() {
		return customer;
	}
	
	public void credit(Double value){
		this.balance += value;
	}
	
	public void debit(Double value){
		this.balance -= value;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
}
