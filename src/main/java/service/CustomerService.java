package service;

import java.util.List;

import exception.SystemException;
import pojo.CustomerPojo;
import pojo.TransactionDetailsPojo;

public interface CustomerService {

	CustomerPojo login(String customerContact, String customerPassword)throws SystemException;
	CustomerPojo customerViewAccountDetails(String customerContact)throws SystemException;
	// Read fetch all
	
	CustomerPojo customerViewAccountDetails(int customerId)throws SystemException;
	// read
	TransactionDetailsPojo transferMoneyToOther(int fromAccountId, int toAccountId,double transferAmount)throws SystemException;

	List<TransactionDetailsPojo> viewTransactionhistory()throws SystemException;

	

}
