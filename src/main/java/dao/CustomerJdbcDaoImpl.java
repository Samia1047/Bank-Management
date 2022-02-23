package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.SystemException;
import pojo.CustomerPojo;
import pojo.TransactionDetailsPojo;

public class CustomerJdbcDaoImpl implements CustomerDao{

	public static final Logger LOG = LogManager.getLogger(CustomerJdbcDaoImpl.class);

	@Override
	public CustomerPojo customerViewAccountDetails(String customerContact)throws SystemException{
		LOG.info("Entered customerViewAccountDetails() in DAO");
		CustomerPojo customerPojo = null;
		Connection conn = DBUtil.obtainConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = " SELECT * FROM CUSTOMER WHERE customer_contact=" + "'" + customerContact + "'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				customerPojo = new CustomerPojo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8),rs.getDouble(9));
			}
		} catch (SQLException e) {

			throw new SystemException();
		}
		LOG.info("Exited customerViewAccountDetails() in DAO");
		return customerPojo;
	}
	@Override
	public CustomerPojo login(String customerContact, String customerPassword)throws SystemException {
		LOG.info("Entered login() in DAO");
		CustomerPojo customerPojo = null;

		Connection conn = DBUtil.obtainConnection();
		try {
			Statement stmt = conn.createStatement();
			customerPojo = customerViewAccountDetails(customerContact);
			String query = "SELECT * FROM customer WHERE customer_contact="+"'"+customerContact+"'" +" AND customer_password="+"'"+customerPassword+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				customerPojo = new CustomerPojo(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7),rs.getString(8), rs.getDouble(9));

			}
		} catch (SQLException | SystemException e) {
			throw new SystemException();
		
		}
		LOG.info("Exited login() in DAO");
		return customerPojo;

	}
	@Override
	public CustomerPojo customerViewAccountDetails(int customerID)throws SystemException {
		LOG.info("Entered customerViewAccountDetails() in DAO");
		CustomerPojo customerPojo = null;
		Connection conn = DBUtil.obtainConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM customer where customer_id="+ customerID;
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				customerPojo = new CustomerPojo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8),rs.getDouble(9));
			}
		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited customerViewAccountDetails() in DAO");
		return customerPojo;
	}
	@Override
	public TransactionDetailsPojo transferMoneyToOther(int fromAccountId, int toAccountId, double transferAmount) throws SystemException{
		LOG.info("Entered transferMoneyToOther() in DAO");
		
		TransactionDetailsPojo transactionPojo = null;
		Connection conn = DBUtil.obtainConnection();
		try {
			
			Statement stmt = conn.createStatement();
			
			String queryUpto = "UPDATE customer SET account_balance=account_balance+"+transferAmount+" WHERE customer_id="+toAccountId;
			
			String queryUpfrom = "UPDATE customer SET account_balance=account_balance-"+transferAmount+"WHERE customer_id="+fromAccountId;
			
			String queryInsertToTransaction = "INSERT INTO transaction_details(from_account_id,to_account_id,transfer_amount) values("+fromAccountId+","+toAccountId+","+transferAmount+")" ;
			
			conn.setAutoCommit(false);
			int rowsTo = stmt.executeUpdate(queryUpto);
			int rowsfrom = stmt.executeUpdate(queryUpfrom);
			int rows = stmt.executeUpdate(queryInsertToTransaction);
			
			conn.commit();
			
			System.out.println("Money transfered...");
			String queryTransact = "SELECT * FROM transaction_details WHERE to_account_id="+toAccountId+" AND from_account_id="+fromAccountId;

			ResultSet rsTransact = stmt.executeQuery(queryTransact);
			
			if(rsTransact.next()) {
				transactionPojo = new TransactionDetailsPojo(rsTransact.getInt(1),rsTransact.getInt(2),rsTransact.getInt(3),rsTransact.getDouble(4),rsTransact.getString(5));
			}

			
		}
		catch (SQLException e) {
			try {
				conn.rollback();
				System.out.println("Transaction failed...");
			} catch (SQLException e1) {
				throw new SystemException();
			}
			throw new SystemException();
			
		}
		LOG.info("Exited transferMoneyToOther() in DAO");
		return transactionPojo;
	}
	@Override
	public List<TransactionDetailsPojo> viewTransactionhistory()throws SystemException {

		LOG.info("Entered viewTransactionhistory() in DAO");
		//List<TransactionDetailsPojo> allTransaction = new ArrayList<TransactionDetailsPojo>();
		List<TransactionDetailsPojo> allTransaction = new ArrayList<>();

		Connection conn = DBUtil.obtainConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM transaction_details1";
			ResultSet rs = stmt.executeQuery(query);
			// iterate throw the resultset
			while (rs.next()) {
				// copy each record into the customerPojo object
				TransactionDetailsPojo transactionPojo = new TransactionDetailsPojo(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDouble(4),rs.getString(5));
				allTransaction.add(transactionPojo);
			}

		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited viewTransactionhistory() in DAO");
		return allTransaction;
	}
	

}
