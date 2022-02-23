package dao;

import java.util.List;

import exception.SystemException;
import pojo.CustomerPojo;
import pojo.EmployeePojo;

public interface EmployeeDao {
	
	EmployeePojo FetchOneEmployee(String employeeContact)throws SystemException;
	
	EmployeePojo login(String employeeContact, String employeePassword)throws SystemException;
	//read
	List<CustomerPojo> fetchAllTransactioninfo()throws SystemException;
	// create 
	CustomerPojo createNewCustomer(CustomerPojo customerPojo)throws SystemException;
	EmployeePojo createNewEmployee(EmployeePojo employeePojo)throws SystemException;
	
	
	
	

	
}
