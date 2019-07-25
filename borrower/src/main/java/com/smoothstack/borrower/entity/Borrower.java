package com.smoothstack.borrower.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "borrower")
public class Borrower {

	@Id
	@Column(name = "card_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer borrowerId;

	@Column(name = "borrower_name")
	private String borrowerName;

	@Column(name = "borrower_address")
	private String borrowerAddress;

	@Column(name = "borrower_phone")
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
