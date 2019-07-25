package com.smoothstack.orchestrator.model;

public class BookCopies {

	private BookCopiesId bookCopiesId;

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
