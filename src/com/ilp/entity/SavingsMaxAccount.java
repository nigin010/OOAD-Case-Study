package com.ilp.entity;

import java.util.ArrayList;

public class SavingsMaxAccount extends Product {

	double minimumBalance;

	public SavingsMaxAccount(String productCode, String productName, ArrayList<Service> serviceArrayList,
			double minimumBalance) {
		super(productCode, productName, serviceArrayList);
		this.minimumBalance = minimumBalance;
	}

	public double getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

}
