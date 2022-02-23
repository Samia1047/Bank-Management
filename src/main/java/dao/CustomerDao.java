package dao;

import java.util.List;

import exception.SystemException;
import pojo.CustomerPojo;

import pojo.TransactionDetailsPojo;

public interface CustomerDao {

	CustomerPojo customerViewAccountDetails(String customerContact)throws SystemException;
	CustomerPojo login(String customerContact, String customerPassword)throws SystemException;

	// Read fetch a customerid to check customerdetails
	
	CustomerPojo customerViewAccountDetails(int customerId)throws SystemException;

	// read
	TransactionDetailsPojo transferMoneyToOther(int fromAccountId, int toAccountId, double transferAmount)throws SystemException;

	List<TransactionDetailsPojo> viewTransactionhistory()throws SystemException;

	

}
