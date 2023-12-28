package com.ilp.entity;

import java.util.ArrayList;

public class LoanAccount extends Product {

	double chequeDeposit;

	public LoanAccount(String productCode, String productName, ArrayList<Service> serviceArrayList,
			double chequeDeposit) {
		super(productCode, productName, serviceArrayList);
		this.chequeDeposit = chequeDeposit;
	}

	public double getChequeDeposit() {
		return chequeDeposit;
	}

	public void setChequeDeposit(double chequeDeposit) {
		this.chequeDeposit = chequeDeposit;
	}

}
