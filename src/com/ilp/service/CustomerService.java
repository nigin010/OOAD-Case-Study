	package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.CurrentAccount;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class CustomerService {

	public static ArrayList<Service> createService() {

		ArrayList<Service> serviceList = new ArrayList<Service>();
		Scanner scanner = new Scanner(System.in);
		String serviceCode;
		String serviceName;
		double serviceRate;
		char servicesChoice;
		do {
			System.out.println("**********SERVICES LIST**********");
			System.out.println("SC001\tCash Deposit");
			System.out.println("SC002\tATM Withdrawal");
			System.out.println("SC003\tOnline Banking");
			System.out.println("SC004\tMobile Banking");
			System.out.println("SC005\tCheque Deposit");
			System.out.println("\n");
			System.out.println("Enter The Service Code:");
			serviceCode = scanner.nextLine();
			System.out.println("Enter the Name of the Service:");
			serviceName = scanner.nextLine();
			System.out.println("Enter the Service Rate:");
			serviceRate = scanner.nextDouble();
			Service service = new Service(serviceCode, serviceName, serviceRate);
			serviceList.add(service);
			System.out.println("Do You Want To Create More Services(y/n):");
			servicesChoice = scanner.next().charAt(0);
			scanner.nextLine();
		} while (servicesChoice == 'y' || servicesChoice == 'Y');
		return serviceList;
	}

	public static ArrayList<Product> createProduct(ArrayList<Service> serviceList) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Product> productList = new ArrayList<Product>();
		String productName;
		String productCode;
		char addingServiceChoice;
		char choice;
		do {
			ArrayList<Service> temporaryServiceList = new ArrayList<Service>();
			System.out.println("**********PRODUCTS LIST**********");
			System.out.println("PR001\tSavingsMaxAccount");
			System.out.println("PR002\tCurrentAccount");
			System.out.println("PR003\tLoanAccount");
			System.out.println("\n");
			System.out.println("Enter the Product Code:");
			productCode = scanner.nextLine();
			System.out.println("Enter the Name Of The Product:");
			productName = scanner.nextLine();
			System.out.println("**********SERVICES MENU**********");
			for (Service service : serviceList) {
				System.out.println("Do You Want To Add The Service \"" + service.getServiceName()
						+ "\" To The Product \"" + productName + "\"?(y/n) :");
				addingServiceChoice = scanner.next().charAt(0);
				if (addingServiceChoice == 'y' || addingServiceChoice == 'Y') {
					temporaryServiceList.add(service);
				}
			}
			switch (productName.toLowerCase()) {
			case "savingsmaxaccount":
				SavingsMaxAccount savingsMaxAccount = new SavingsMaxAccount(productCode, productName,
						temporaryServiceList, 1000);
				productList.add(savingsMaxAccount);
				break;
			case "currentaccount":
				CurrentAccount currentAccount = new CurrentAccount(productCode, productName, temporaryServiceList);
				productList.add(currentAccount);
				break;
			case "loanaccount":
				LoanAccount loanAccount = new LoanAccount(productCode, productName, temporaryServiceList, 0.5);
				productList.add(loanAccount);
				break;
			default:
				System.out.println("Invalid!Try Again!");
				break;
			}
			System.out.println("Do You Want To Create More Products?(y/n):");
			choice = scanner.next().charAt(0);
			scanner.nextLine();
		} while (choice == 'Y' || choice == 'y');
		return productList;
	}

	public static Account createAccount(ArrayList<Product> productList) {
		Scanner scanner = new Scanner(System.in);
		int productNumber;
		String productName;
		String accountCode;
		String accountName;
		String customerCode;
		String customerName;
		double balanceAmount;
		System.out.println("**********LIST OF PRODUCTS AVAILABLE**********");
		int counter = 1;
		for (Product product : productList) {
			System.out.print(counter + " " + product.getProductName());
			System.out.println();
			counter++;
		}
		System.out.println("Enter The Number Corresponding To The Product You Need:");
		productNumber = scanner.nextInt();
		scanner.nextLine();
		productName = productList.get(productNumber - 1).getProductName();
		System.out.println("Enter The Account Code:");
		accountCode = scanner.nextLine();
		System.out.println("Enter the Balance Amount in Your Account:");
		balanceAmount = scanner.nextDouble();
		if (productList.get(productNumber - 1) instanceof SavingsMaxAccount) {
			SavingsMaxAccount savingsMaxAccount = (SavingsMaxAccount) productList.get(productNumber - 1);
			while (balanceAmount < savingsMaxAccount.getMinimumBalance()) {
				System.out.println("Your Account Balance Is Not Sufficient! Minimum Balance for Savings Max Account Is "
						+ savingsMaxAccount.getMinimumBalance());
				System.out.println("Re-enter the Balance Amount In Your Account:");
				balanceAmount = scanner.nextDouble();
			}
		}
		Account account = new Account(accountCode, productName, balanceAmount, productList.get(productNumber - 1));
		return account;
	}

	public static Customer createCustomer(Account account) {
		Scanner scanner = new Scanner(System.in);
		String customerCode;
		String customerName;
		ArrayList<Account> accountArrayList = new ArrayList<Account>();
		System.out.println("Enter The Customer Code:");
		customerCode = scanner.nextLine();
		System.out.println("Enter The Name Of The Customer:");
		customerName = scanner.nextLine();
		accountArrayList.add(account);
		Customer customer = new Customer(customerCode, customerName, accountArrayList);
		return customer;
	}

	public static void manageAccounts(Customer customer) {
		Scanner scanner = new Scanner(System.in);
		String customerId;
		String accountNumberChoice;
		int accountManagementChoice;
		char manageAccountChoice;
		ArrayList<Account> temporaryAccountList = new ArrayList<Account>();
		Account temporaryAccount = null;
		System.out.println("Enter The Customer Code:");
		customerId = scanner.nextLine();
		if (customerId.equalsIgnoreCase(customer.getCustomerCode())) {
			System.out.println("**********Accounts List**********");
			System.out.println("Account Number \t\t Account Name");
			temporaryAccountList = customer.getAccountArrayList();
			for (Account account : temporaryAccountList) {
				System.out.println(account.getAccountNo() + "\t" + account.getAccountType());
			}
			System.out.println("Enter The Account Number Of The Account You Want To Choose :");
			accountNumberChoice = scanner.nextLine();
			for (Account account : temporaryAccountList) {
				if (account.getAccountNo().equalsIgnoreCase(accountNumberChoice)) {
					temporaryAccount = account;
					break;
				}
			}
			do {
				System.out.println("**********ACCOUNT MANAGEMENT**********");
				System.out.println("1. Deposit Money");
				System.out.println("2. Withdraw Money");
				System.out.println("3. Display Your Account Balance");
				System.out.println("Enter Your Choice of Action:");
				accountManagementChoice = scanner.nextInt();
				switch (accountManagementChoice) {
				case 1:
					depositMoney(temporaryAccount);
					break;
				case 2:
					withdrawMoney(temporaryAccount);
					break;
				case 3:
					display(temporaryAccount);
					break;
				default:
					System.out.println("Invalid option of Management! Try Again!");
					break;
				}
				System.out.println(
						"Do You Want To Perform Any other Management Related Operation on This Account?(y/n) :");
				manageAccountChoice = scanner.next().charAt(0);
			} while (manageAccountChoice == 'y' || manageAccountChoice == 'Y');
		}
	}

	private static void depositMoney(Account temporaryAccount) {
		Scanner scanner = new Scanner(System.in);
		double depositAmount;
		double newBalance;
		int depositMediumChoice;
		System.out.println("Enter The Amount To Be Deposited Into Your Account :");
		depositAmount = scanner.nextDouble();
		if (temporaryAccount.getProduct() instanceof LoanAccount) {
			System.out.println("Choose Your Medium of Deposit:");
			System.out.println("1. Cash Deposit");
			System.out.println("2. Cheque Deposit");
			System.out.println("Enter Your Choice:");
			depositMediumChoice = scanner.nextInt();
			if (depositMediumChoice == 2) {
				depositAmount = depositAmount - (depositAmount * 0.003);
			}
		}
		newBalance = temporaryAccount.getBalance() + depositAmount;
		temporaryAccount.setBalance(newBalance);
		System.out.println("New Total Balance : " + newBalance);
	}

	private static void withdrawMoney(Account temporaryAccount) {
		Scanner scanner = new Scanner(System.in);
		double withdrawAmount;
		double newBalance;
		Product product = null;
		System.out.println("Enter The Amount To Be Withdrawn From The Account :");
		withdrawAmount = scanner.nextDouble();
		newBalance = temporaryAccount.getBalance() - withdrawAmount;
		product = temporaryAccount.getProduct();
		if (product instanceof SavingsMaxAccount) {
			SavingsMaxAccount savingsMaxAccount = (SavingsMaxAccount) product;
			if (newBalance < savingsMaxAccount.getMinimumBalance()) {
				System.out.println("Cannot Make This Withdrawal Since The Minimum Balance Of Savings Max Account is "
						+ savingsMaxAccount.getMinimumBalance());
			} else {
				temporaryAccount.setBalance(newBalance);
			}
		} else if (product instanceof LoanAccount) {
			System.out.println("You cannot Withdraw any Money from A Loan Account");
		} else {
			if (newBalance < temporaryAccount.getBalance()) {
				System.out.println("Insufficient Balance In Your Account!");
				System.out.println("Current Bank Balance:" + temporaryAccount.getBalance());
				System.out.println("Bank Balance If Withdrawal Takes Place :" + newBalance);
				System.out.println("Please Try Again With a Smaller Amount To Withdraw");
			} else {
				temporaryAccount.setBalance(newBalance);
			}
		}
		System.out.println("New Account Balance : " + temporaryAccount.getBalance());
	}

	private static void display(Account temporaryAccount) {
		System.out.println("Account Number : " + temporaryAccount.getAccountNo());
		System.out.println("Account Name : " + temporaryAccount.getAccountType());
		System.out.println("Account Balance : " + temporaryAccount.getBalance());
	}

	public static void display(Customer customer) {
		ArrayList<Account> accountArrayList = new ArrayList<Account>();
		accountArrayList = customer.getAccountArrayList();
		System.out.println("**********CUSTOMER DATA**********");
		System.out.println("CUSTOMER ID \t CUSTOMER NAME \t ACCOUNT TYPE \t BALANCE");
		for (Account account : accountArrayList) {
			System.out.print(customer.getCustomerCode() + "\t\t " + customer.getCustomerName() + "\t"
					+ account.getAccountType() + "\t" + account.getBalance() + "\t");
			System.out.println("\nSERVICES PROVIDED");
			ArrayList<Service> serviceArrayList = new ArrayList<Service>();
			serviceArrayList = account.getProduct().getServiceArrayList();
			for (Service service : serviceArrayList) {
				System.out.print(service.getServiceCode() + "\t" + service.getServiceName() + "\t" + service.getRate());
			}
			System.out.println();
		}
	}
}
