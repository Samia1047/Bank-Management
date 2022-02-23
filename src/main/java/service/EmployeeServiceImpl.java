package service;

import java.util.List;

import dao.CustomerDao;
import dao.EmployeeDao;
import dao.EmployeeJdbcDaoImpl;
import exception.SystemException;
import pojo.CustomerPojo;
import pojo.EmployeePojo;

public class EmployeeServiceImpl implements EmployeeService {
	
	EmployeeDao employeeDao; 
	
	
	public EmployeeServiceImpl() {
		employeeDao = new EmployeeJdbcDaoImpl();
	}
	

	@Override
	public EmployeePojo FetchOneEmployee(String employeeContact)throws SystemException {
		
		return employeeDao.FetchOneEmployee(employeeContact);
	}

	@Override
	public EmployeePojo login(String employeeContact, String employeePassword)throws SystemException {
		
		return employeeDao.login(employeeContact, employeePassword);
	}

	@Override
	public List<CustomerPojo> fetchAllTransactioninfo()throws SystemException {
		
		return employeeDao.fetchAllTransactioninfo();
		
	}

	@Override
	public CustomerPojo createNewCustomer(CustomerPojo customerPojo)throws SystemException {
		
		return employeeDao.createNewCustomer(customerPojo);
	}

	@Override
	public EmployeePojo createNewEmployee(EmployeePojo employeePojo)throws SystemException {
		
		return employeeDao.createNewEmployee(employeePojo);
	}

	
	
	
	
}

