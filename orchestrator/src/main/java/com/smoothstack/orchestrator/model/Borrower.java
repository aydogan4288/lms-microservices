package com.smoothstack.orchestrator.model;

public class Borrower {

	private Integer borrowerId;

	private String borrowerName;

	private String borrowerAddress;

	private int borrowerPhone;

	public Borrower() {
		super();
	}

	public Borrower(Integer borrowerId, String borrowerName, String borrowerAddress, int borrowerPhone) {
		super();
		this.borrowerId = borrowerId;
		this.borrowerName = borrowerName;
		this.borrowerAddress = borrowerAddress;
		this.borrowerPhone = borrowerPhone;
	}

	public Integer getBorrowerId() {
		return borrowerId;
	}

	public void setBorrowerId(Integer borrowerId) {
		this.borrowerId = borrowerId;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public String getBorrowerAddress() {
		return borrowerAddress;
	}

	public void setBorrowerAddress(String borrowerAddress) {
		this.borrowerAddress = borrowerAddress;
	}

	public int getBorrowerPhone() {
		return borrowerPhone;
	}

	public void setBorrowerPhone(int borrowerPhone) {
		this.borrowerPhone = borrowerPhone;
	}

}
