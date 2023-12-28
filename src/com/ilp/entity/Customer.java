package com.ilp.entity;

import java.util.ArrayList;

public class Customer {
	private String customerCode;
	private String customerName;
	ArrayList<Account> accountArrayList;

	public Customer(String customerCode, String customerName, ArrayList<Account> accountArrayList) {
		super();
		this.customerCode = customerCode;
		this.customerName = customerName;
		this.accountArrayList = accountArrayList;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public ArrayList<Account> getAccountArrayList() {
		return accountArrayList;
	}

	public void setAccountArrayList(ArrayList<Account> accountArrayList) {
		this.accountArrayList = accountArrayList;
	}

	@Override
	public String toString() {
		return "Customer [customerCode=" + customerCode + ", customerName=" + customerName + ", accountArrayList="
				+ accountArrayList + "]";
	}
}
