package pojo;

public class CustomerPojo {

	private int customerId; 
	private String customerPassword;
	private String customerFirstName;
	private String customerLastName;
	private String customerContact;
	private String customerAddress;
	private int accountID;
	private String accountType;
	private double accountBalance;

	public CustomerPojo() {
		super();

	}

	public CustomerPojo(int customerId, String customerPassword, String customerFirstName, String customerLastName,
			String customerContact, String customerAddress, int accountID, String accountType, double accountBalance) {
		super();
		this.customerId = customerId;
		this.customerPassword = customerPassword;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerContact = customerContact;
		this.customerAddress = customerAddress;
		this.accountID = accountID;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Override
	public String toString() {
		return "CustomerPojo [customerId=" + customerId + ", customerPassword=" + customerPassword
				+ ", customerFirstName=" + customerFirstName + ", customerLastName=" + customerLastName
				+ ", customerContact=" + customerContact + ", customerAddress=" + customerAddress + ", accountID="
				+ accountID + ", accountType=" + accountType + ", accountBalance=" + accountBalance + "]";
	}

}
