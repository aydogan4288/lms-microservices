package com.smoothstack.borrower.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "book_copies")
public class BookCopies {

	@EmbeddedId
	private BookCopiesId bookCopiesId;

	@Column(name = "no_of_copies")
	private int noOfCopies;

	public BookCopies() {
		super();
	}

	public BookCopies(BookCopiesId bookCopiesId) {
		super();
		this.bookCopiesId = bookCopiesId;
	}
	
	public BookCopies(BookCopiesId bookCopiesId, int noOfCopies) {
		super();
		this.bookCopiesId = bookCopiesId;
		this.noOfCopies = noOfCopies;
	}

	public BookCopiesId getBookCopiesId() {
		return bookCopiesId;
	}

	public void setBookCopiesId(BookCopiesId bookCopiesId) {
		this.bookCopiesId = bookCopiesId;
	}

	public int getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

}
