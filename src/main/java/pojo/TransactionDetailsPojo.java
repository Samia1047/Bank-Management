package pojo;

import java.util.Date;

public class TransactionDetailsPojo {
	private int transactionId;
	private int fromAccountId;
	private int toAccountId;
	private double transferAmount;
	private String transferDate;
	
	public TransactionDetailsPojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionDetailsPojo(int transactionId, int fromAccountId, int toAccountId, double transferAmount,
			String transferDate) {
		super();
		this.transactionId = transactionId;
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.transferAmount = transferAmount;
		this.transferDate = transferDate;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(int fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public int getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(int toAccountId) {
		this.toAccountId = toAccountId;
	}

	public double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}

	public String getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(String transferDate) {
		this.transferDate = transferDate;
	}

	@Override
	public String toString() {
		return "TransactionDetailsPojo [transactionId=" + transactionId + ", fromAccountId=" + fromAccountId
				+ ", toAccountId=" + toAccountId + ", transferAmount=" + transferAmount + ", transferDate="
				+ transferDate + "]";
	}

	
	
	
}