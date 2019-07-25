package com.smoothstack.borrower.entity;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

public class BookLoansId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "branchId")
	private Branch branchID;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "bookId")
	private Book book;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "cardNo")
	private Borrower borrower;

	public BookLoansId() {
		super();
	}

	public BookLoansId(@NotNull Branch branchID, @NotNull Book book, @NotNull Borrower borrower) {
		super();
		this.branchID = branchID;
		this.book = book;
		this.borrower = borrower;
	}

	public Branch getBranchID() {
		return branchID;
	}

	public void setBranchID(Branch branchID) {
		this.branchID = branchID;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
