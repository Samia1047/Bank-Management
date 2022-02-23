package service;

import java.util.List;

import dao.CustomerDao;
import dao.CustomerJdbcDaoImpl;
import exception.SystemException;
import pojo.CustomerPojo;
import pojo.TransactionDetailsPojo;

public class CustomerServiceImpl implements CustomerService {

	CustomerDao customerDao;

	public CustomerServiceImpl() {
		customerDao = new CustomerJdbcDaoImpl();
	}

	@Override
	public CustomerPojo login(String customerContact, String customerPassword)throws SystemException {

		return customerDao.login(customerContact,customerPassword);
	}
	@Override
	public CustomerPojo customerViewAccountDetails(String customerContact)throws SystemException {
		return customerDao.customerViewAccountDetails(customerContact);
	}
	
	@Override
	public CustomerPojo customerViewAccountDetails(int customerId)throws SystemException {

		return customerDao.customerViewAccountDetails(customerId);
	}

    @Override
	public TransactionDetailsPojo transferMoneyToOther(int fromAccountId, int toAccountId, double transferAmount)throws SystemException {
		
		return customerDao.transferMoneyToOther(fromAccountId,toAccountId,transferAmount);
	}

	@Override
	public List<TransactionDetailsPojo> viewTransactionhistory()throws SystemException {

		return customerDao.viewTransactionhistory();
	}

	

}
