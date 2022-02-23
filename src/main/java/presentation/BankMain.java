package presentation;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.SystemException;
import pojo.CustomerPojo;
import pojo.EmployeePojo;
import pojo.TransactionDetailsPojo;
import service.CustomerService;
import service.CustomerServiceImpl;
import service.EmployeeService;
import service.EmployeeServiceImpl;


public class BankMain {

	public static final Logger LOG = LogManager.getLogger(BankMain.class);

		public static void main(String[] args) {
			EmployeeService employeeService = new EmployeeServiceImpl();
			CustomerService customerService = new CustomerServiceImpl();

			Scanner scan = new Scanner(System.in);

			char ch = 'y';

			while (ch == 'y') {

				System.out.println("*************************************");
				System.out.println("\tWelcome to the Banking Operation");
				System.out.println("*************************************");
				System.out.println("1. Login As an Employee");
				System.out.println("2. Login As a Customer");
				System.out.println("3. Exit the Banking Operation");
				System.out.println("*************************************");
				System.out.println("Please enter the menu option: ");

				int option = scan.nextInt();
				scan.nextLine();

				List<Integer> mainListOptions = new ArrayList<>();
				mainListOptions.add(1);
				mainListOptions.add(2);
				mainListOptions.add(3);

				// make sure valid option is selected
				if (!mainListOptions.contains(option)) {
					System.out.println("Please enter a valid menu option.");
				}

				System.out.println("*************************************");

				switch (option) {

				case 1:

					EmployeePojo findingEmployee = null;
					EmployeePojo fetchedEmployee = null;

					while (findingEmployee == null) {
						System.out.println("Enter Employee Email: ");
						String employeeContact = scan.next();

						try {
							findingEmployee = employeeService.FetchOneEmployee(employeeContact);
						} catch (SystemException e) {
							LOG.error(e);
							System.out.println(e.getMessage());
						}

						if (findingEmployee == null) {
							System.out.println("Please enter the valid email...");
						}

					}

					while (fetchedEmployee == null) {
						System.out.println("Enter Employee Password: ");
						String employeePassword = scan.next();

						try {
							fetchedEmployee = employeeService.login(findingEmployee.getEmployeeContact(),employeePassword);
						} catch (SystemException e) {
							LOG.error(e);
							System.out.println(e.getMessage());
						}

						if (fetchedEmployee == null) {
							System.out.println("Please enter the valid password...");
						} else {
							System.out.println("Login Successful!");

						}

					}

					System.out.println("*************************************");
					System.out.println("Employee ID: " + fetchedEmployee.getEmployeeId());
					System.out.println(
							"Employee Name: " + fetchedEmployee.getEmployeeFirstName() + " " + fetchedEmployee.getEmployeeLastName());
					System.out.println("Employee Email Id: " + fetchedEmployee.getEmployeeContact());
					System.out.println("Employee Adress:"+fetchedEmployee.getEmployeeAddress());

					boolean employeeMenu = true;

					while (employeeMenu) {
						System.out.println("*************************************");
						System.out.println("Employee Menu");
						System.out.println("*************************************");
						System.out.println("1. List all The Customers");
						System.out.println("2. Create a Customer");
						System.out.println("3. Logout and Return to Main Menu");
						System.out.println("*************************************");

						List<Integer> employeeMenuOptions = new ArrayList<>();
						employeeMenuOptions.add(1);
						employeeMenuOptions.add(2);
						employeeMenuOptions.add(3);

						int option2 = scan.nextInt();

						// make sure valid option is selected
						if (!employeeMenuOptions.contains(option2)) {
							System.out.println("Please enter a valid menu option...");
						}

						if (option2 == 1) {

							List<CustomerPojo> allCustomers;
							allCustomers = null;
							try {
								allCustomers = employeeService.fetchAllTransactioninfo();
							} catch (SystemException e) {
								LOG.error(e);
								System.out.println(e.getMessage());
							}
							Iterator<CustomerPojo> itr = allCustomers.iterator();
							System.out.println("************************************************************************************************************************************");
							System.out.println("ID\tPASSWORD\tFIRST NAME\tLAST NAME\tCONTACT INFO\tADDRESS\tACCOUNT ID\tACCOUNT TYPE\tACCOUNT BALANCE");
							
							System.out.println("************************************************************************************************************************************");
							while(itr.hasNext()) {
								CustomerPojo customer = itr.next();
								System.out.println(customer.getCustomerId()+"\t"+customer.getCustomerPassword()+"\t"+customer.getCustomerFirstName()+"\t"+customer.getCustomerLastName()+"\t"+customer.getCustomerContact()+"\t"+customer.getCustomerAddress()+"\t"+customer.getAccountID()+"\t"+customer.getAccountType()+"\t"+customer.getAccountBalance());
							
								
							}
							System.out.println("************************************************************************************************************************************");
						}

							 if (option2 == 2) {
							System.out.println("********************************************");
							scan.nextLine();

							CustomerPojo newCustomer = new CustomerPojo();

							System.out.println("Enter Customer Password :"); 
							newCustomer.setCustomerPassword(scan.nextLine());
							System.out.println("Enter Customer First Name :");
							newCustomer.setCustomerFirstName(scan.nextLine());
							System.out.println("Enter Customer Last Name");
							newCustomer.setCustomerLastName(scan.nextLine());
							System.out.println("Enter Customer Contact");
							newCustomer.setCustomerContact(scan.nextLine());
							System.out.println("Enter Customer Address");
							newCustomer.setCustomerAddress(scan.nextLine());
							System.out.println("Enter Customer Account ID");
							newCustomer.setAccountID(scan.nextInt());
							scan.nextLine();
							System.out.println("Enter Customer Account Type");
							newCustomer.setAccountType(scan.nextLine());
							
							System.out.println("Enter Customer Account Balance");
							newCustomer.setAccountBalance(scan.nextDouble());

							CustomerPojo addedCustomer;
							addedCustomer = null;
							try {
								addedCustomer = employeeService.createNewCustomer(newCustomer);
							} catch (SystemException e) {
								LOG.error(e);
								System.out.println(e.getMessage());
							}
							System.out.println("Customer Added Successfully!!\n Your new Account Id is :" + addedCustomer.getAccountID());
							
							
							System.out.println("***************************************");
							System.out.println("Customer ID: " +  addedCustomer.getCustomerId());
							System.out.println("Password: " +  addedCustomer.getCustomerPassword());
							System.out.println("First Name : " +  addedCustomer.getCustomerFirstName());
							System.out.println("Last Name : " +  addedCustomer.getCustomerLastName());
							System.out.println("Contact : " +  addedCustomer.getCustomerContact());
							System.out.println("Address : " +  addedCustomer.getCustomerAddress());
							System.out.println("Account ID : " +  addedCustomer.getAccountID());
							System.out.println("Account Type : " +  addedCustomer.getAccountType());
							System.out.println("Account Balance : " +  addedCustomer.getAccountBalance());
							System.out.println("***************************************");
							
//							System.out.println("New Customer Name is: " + addedCustomer.getCustomerFirstName() + " "
//									+ addedCustomer.getCustomerLastName());
//							//System.out.println("New Customer Phone Number is: " + addedCustomer.getPhoneNumber());
//							System.out.println("New Customer Email is: " + addedCustomer.getCustomerContact());
//							System.out.println("New Customer Balance is: " + addedCustomer.getAccountBalance());
//							System.out.println("Your Employee ID is: " + fetchedEmployee.getEmployeeId());

							System.out.println();
						}

						if (option2 == 3) {
							employeeMenu = false;
						}
						
					break;
					}
					
				case 2:

					CustomerPojo findingCustomer = null;
					CustomerPojo fetchedCustomer = null;

					while (findingCustomer == null) {
						System.out.println("Enter Customer Email Id: ");
						String customerEmail = scan.next();

						try {
							findingCustomer = customerService.customerViewAccountDetails(customerEmail);
						} catch (SystemException e) {
							LOG.error(e);
							System.out.println(e.getMessage());
						}

						if (findingCustomer == null) {
							System.out.println("Please enter a valid email Id: ");
						}

					}

					while (fetchedCustomer == null) {
						System.out.println("Enter Customer Password: ");
						String customerPassword = scan.next();

						try {
							fetchedCustomer = customerService.login(findingCustomer.getCustomerContact(), customerPassword);
						} catch (SystemException e) {
							LOG.error(e);
							System.out.println(e.getMessage());
						}

						if (fetchedCustomer == null) {
							System.out.println("Please enter a valid password");
						} else {
							System.out.println("Login Successful!");

						}

						System.out.println("***************************************");
						System.out.println("Customer ID: " +  fetchedCustomer.getCustomerId());
						System.out.println("Password: " +  fetchedCustomer.getCustomerPassword());
						System.out.println("First Name : " +  fetchedCustomer.getCustomerFirstName());
						System.out.println("Last Name : " +  fetchedCustomer.getCustomerLastName());
						System.out.println("Contact : " +  fetchedCustomer.getCustomerContact());
						System.out.println("Address : " +  fetchedCustomer.getCustomerAddress());
						System.out.println("Account ID : " +  fetchedCustomer.getAccountID());
						System.out.println("Account Type : " +  fetchedCustomer.getAccountType());
						System.out.println("Account Balance : " +  fetchedCustomer.getAccountBalance());
						System.out.println("***************************************");
						
					boolean customerMenu = true;

					while (customerMenu) {
						System.out.println("*************************************");
						System.out.println("Customer Menu");
						System.out.println("*************************************");
						System.out.println("1. List all Transaction History");
						System.out.println("2. Create a Transfer to Another Account");
						System.out.println("3. Logout and Return to Main Menu");
						System.out.println("*************************************");

						List<Integer> customerListOptions = new ArrayList<>();
						customerListOptions.add(1);
						customerListOptions.add(2);
						customerListOptions.add(3);

						int option2 = scan.nextInt();

						// make sure valid option is selected
						if (!customerListOptions.contains(option2)) {
							System.out.println("Please enter a valid menu option!");
						}
						
						if (option2 == 1) {

							List<TransactionDetailsPojo> allTransactions;
							allTransactions=null;

							try {
								allTransactions = customerService.viewTransactionhistory();
							} catch (SystemException e) {
								LOG.error(e);
								System.out.println(e.getMessage());
							}

							// iterate through all customers
							Iterator<TransactionDetailsPojo> itr = allTransactions.iterator();

							System.out.println("*************************************************************************************************************");
							System.out.println("Transaction List:");
							System.out.println("ID\tFROM ACCOUNT ID\t\tTO ACCOUNT ID\tAMOUNT TRANSFERRED\t\tCREATED ON");

							while (itr.hasNext()) {
								TransactionDetailsPojo transaction = itr.next();
								System.out.println(transaction.getTransactionId() + "\t\t" + transaction.getFromAccountId() + "\t\t\t" + transaction.getToAccountId() + "\t\t" + transaction.getTransferAmount()
										+ "\t\t\t" + transaction.getTransferDate());
							}
							System.out.println("*************************************************************************************************************");
						}
						
						if (option2 == 2) {
							
							System.out.println("********************************************");
							scan.nextLine();

							TransactionDetailsPojo newTransaction = new TransactionDetailsPojo();
							TransactionDetailsPojo addedTransaction = new TransactionDetailsPojo();

							System.out.println("Enter the Customer ID you want to send to: ");
							newTransaction.setToAccountId(scan.nextInt());
							System.out.println("Enter the amount you would like to transfer: ");
							//newTransaction.setAmountToTransfer(scan.nextInt());
							newTransaction.setTransferAmount(scan.nextDouble());
							newTransaction.setFromAccountId(fetchedCustomer.getCustomerId());
							
							scan.nextLine();

							try {
								addedTransaction = customerService.transferMoneyToOther(newTransaction.getFromAccountId(), newTransaction.getToAccountId(),  newTransaction.getTransferAmount());
							} catch (SystemException e) {
								LOG.error(e);
								System.out.println(e.getMessage());
							}
					       // updatedTransaction = customerService.transferMoneyToOther(newTransaction.getFromAccountId(),newTransaction.getToAccountId(),newTransaction.getTransferAmount());
							
							System.out.println("New Transaction ID is: " + addedTransaction.getTransactionId());
							System.out.println("Your New Balance is: " + (fetchedCustomer.getAccountBalance() - newTransaction.getTransferAmount()));
							System.out.println("The transaction was created on: " + addedTransaction.getTransferDate());

							System.out.println();
						}
						
						if (option2 == 3)
							customerMenu = false;
						}
					
					}
					
					break;
					
				case 3:
					System.out.println("***********************************************");
					System.out.println("Exiting System...");
					System.out.println("Thank you for visiting the Bank!");
					System.out.println("***********************************************");

					scan.close();
					System.exit(0);
				}

				System.out.println("Do You Want to Continue? (y/n): ");
				ch = scan.next().charAt(0);
				scan.nextLine();

			}
			
			System.out.println("***********************************************");
			System.out.println("Exiting System...");
			System.out.println("Thank you for visiting the Banking Operation!");
			System.out.println("***********************************************");
			
			scan.close();
			System.exit(0);

		}
		
	}




