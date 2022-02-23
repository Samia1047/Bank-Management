package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import exception.SystemException;
import pojo.CustomerPojo;
import pojo.EmployeePojo;

public class EmployeeJdbcDaoImpl  implements EmployeeDao{
	
	public static final Logger LOG = LogManager.getLogger(EmployeeJdbcDaoImpl.class);
	
	@Override
	public EmployeePojo FetchOneEmployee(String employeeContact)throws SystemException {
		LOG.info("Entered FetchOneEmployee() in DAO");
		EmployeePojo employeePojo = null;
		Connection conn = DBUtil.obtainConnection();
		try {
			Statement stmt = conn.createStatement();
			//String query = "SELECT * FROM employee WHERE employee_contact=" + "'" + employeePojo.getEmployeeContact() + "'";
			String query = "SELECT * FROM employee WHERE employee_contact=" +"'"+employeeContact+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				employeePojo = new EmployeePojo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
			}
		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited FetchOneEmployee() in DAO");
		return employeePojo;
	}
	
	
	@Override
    public EmployeePojo login(String employeeContact, String employeePassword)throws SystemException {
		
		LOG.info("Entered login() in DAO");
		
		Connection conn = DBUtil.obtainConnection();
		EmployeePojo employeePojo = null;
		
		try {
			Statement stmt = conn.createStatement();
			employeePojo = FetchOneEmployee(employeeContact);
			String query = "SELECT * FROM employee WHERE employee_contact="+"'"+employeePojo.getEmployeeContact()+"'"+ " AND employee_password=" +"'"+ employeePojo.getEmployeePassword()+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				employeePojo = new EmployeePojo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				
			}
		}  catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited login() in DAO");
	return employeePojo;
	
		
	}
	@Override
	public List<CustomerPojo> fetchAllTransactioninfo()throws SystemException {
		LOG.info("Entered fetchAllTransactioninfo() in DAO");
		List<CustomerPojo> allCustomers = new ArrayList<CustomerPojo>();
		Connection conn = DBUtil.obtainConnection();

		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM customer";
			ResultSet rs = stmt.executeQuery(query);
			// iterate throw the resultset
			while (rs.next()) {
				// copy each record into the customerPojo object
				CustomerPojo customerPojo = new CustomerPojo(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7),rs.getString(8), rs.getDouble(9));
				// add the customerpojo object to the collection
				allCustomers.add(customerPojo);
			}
		} catch (SQLException e) {
			throw new SystemException();
		}
		
		LOG.info("Exited fetchAllTransactioninfo() in DAO");
		return allCustomers;
		

	}
	@Override
	public CustomerPojo createNewCustomer(CustomerPojo customerPojo)throws SystemException {
		LOG.info("Entered createNewCustomer() in DAO");
		Connection conn = DBUtil.obtainConnection();
	

		try {
			Statement stmt = conn.createStatement();
			String query1 = "INSERT INTO customer(customer_password,customer_first_name,customer_last_name,customer_contact,customer_address,account_id,account_type,account_balance) VALUES("
			    +"'"+ customerPojo.getCustomerPassword()+ "','" + customerPojo.getCustomerFirstName() + "','"
					+ customerPojo.getCustomerLastName() + "','"+ customerPojo.getCustomerContact() + "','" 
			    + customerPojo.getCustomerAddress() + "','"+ customerPojo.getAccountID() + "','" 
					+ customerPojo.getAccountType()+"',"+ customerPojo.getAccountBalance() + ")";
					
			
			int rows = stmt.executeUpdate(query1);
					
					
					
		}  catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited createNewCustomer() in DAO");
		return customerPojo; 
		
		

	}
	@Override
	public EmployeePojo createNewEmployee(EmployeePojo employeePojo)throws SystemException {
		LOG.info("Entered createNewEmployee() in DAO");
		Connection conn = DBUtil.obtainConnection();
		try {
			Statement stmt = conn.createStatement();
			String query =  "insert into employee(employee_password,employee_first_name,employee_last_name,employee_contact,employee_address) VALUES("
					+"'"+ employeePojo.getEmployeePassword() + "','" +employeePojo.getEmployeeFirstName()+"',"
						+ employeePojo.getEmployeeLastName() + "','" +employeePojo.getEmployeeContact()+"',"+employeePojo.getEmployeeAddress()+")";
				int rows = stmt.executeUpdate(query);
		}  catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited createNewEmployee() in DAO");
		return employeePojo;
	}

	
	
	
}
