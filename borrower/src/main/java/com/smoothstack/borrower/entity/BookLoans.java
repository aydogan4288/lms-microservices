package com.smoothstack.borrower.entity;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "book_loans")
public class BookLoans {

	@EmbeddedId
	private BookLoansId bookLoansId;

	@Temporal(TemporalType.TIMESTAMP)
	Date dateOut;

	@Temporal(TemporalType.TIMESTAMP)
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