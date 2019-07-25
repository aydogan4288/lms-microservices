package com.smoothstack.orchestrator.model;

import java.util.Date;

public class BookLoans {

	private BookLoansId bookLoansId;

	Date dateOut;

	Date dateReturn;

	public BookLoans() {
		super();
	}

	public BookLoans(BookLoansId bookLoansId, Date dateOut, Date dateReturn) {
		super();
		this.bookLoansId = bookLoansId;
		this.dateOut = dateOut;
		this.dateReturn = dateReturn;
	}

	public BookLoansId getBookLoansId() {
		return bookLoansId;
	}

	public void setBookLoansId(BookLoansId bookLoansId) {
		this.bookLoansId = bookLoansId;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public Date getDateReturn() {
		return dateReturn;
	}

	public void setDateReturn(Date dateReturn) {
		this.dateReturn = dateReturn;
	}

}