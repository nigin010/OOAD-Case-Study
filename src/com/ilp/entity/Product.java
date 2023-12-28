package com.ilp.entity;

import java.util.ArrayList;

public abstract class Product {

	private String productCode;
	private String productName;
	ArrayList<Service> serviceArrayList;

	public Product(String productCode, String productName, ArrayList<Service> serviceArrayList) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.serviceArrayList = serviceArrayList;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public ArrayList<Service> getServiceArrayList() {
		return serviceArrayList;
	}

	public void setServiceArrayList(ArrayList<Service> serviceArrayList) {
		this.serviceArrayList = serviceArrayList;
	}

	@Override
	public String toString() {
		return "Product [productCode=" + productCode + ", productName=" + productName + ", serviceArrayList="
				+ serviceArrayList + "]";
	}
}
