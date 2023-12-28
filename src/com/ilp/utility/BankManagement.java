package com.ilp.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.Customer;
import com.ilp.entity.Product;
import com.ilp.entity.Service;
import com.ilp.service.CustomerService;

public class BankManagement {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Service> serviceList = new ArrayList<Service>();
		ArrayList<Product> productList = new ArrayList<Product>();
		int choice;
		char continueStatus;
		Account account = null;
		Customer customer = null;
		do {
			System.out.println("**********BANK MANAGEMENT MENU**********");
			System.out.println("1. Create A Service");
			System.out.println("2. Create A Product");
			System.out.println("3. Create A Customer");
			System.out.println("4. Manage Accounts");
			System.out.println("5. Display Customer Details");
			System.out.println("6. Exit");
			System.out.println("Enter Your Choice :");
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				serviceList.addAll(CustomerService.createService());
				break;
			case 2:
				if (serviceList.size() != 0) {
					productList.addAll(CustomerService.createProduct(serviceList));
				} else {
					System.out.println("No Service Created! Try Again!");
				}
				break;
			case 3:
				if (serviceList.size() != 0) {
					if (customer == null) {
						account = CustomerService.createAccount(productList);
						customer = CustomerService.createCustomer(account);
					} else {
						account = CustomerService.createAccount(productList);
						ArrayList<Account> tempAccountList = customer.getAccountArrayList();
						tempAccountList.add(account);
						customer.setAccountArrayList(tempAccountList);
					}
				} else {
					System.out.println("Ther are no Services or Products Present! Try Again!");
				}
				break;
			case 4:
				if (customer != null) {
					CustomerService.manageAccounts(customer);
				} else {
					System.out.println("There Are No Customers Created Currently");
				}
				break;
			case 5:
				if (customer != null) {
					CustomerService.display(customer);
				} else {
					System.out.println("There Are No Customers Created Currently");
				}
				break;
			case 6:
				break;
			default:
				System.out.println("Incorrect Option! Try Again!");
				break;
			}
			System.out.println("Do you want to Continue?(y/n):");
			continueStatus = scanner.next().charAt(0);
		} while (continueStatus == 'y' || continueStatus == 'Y');
	}

}
