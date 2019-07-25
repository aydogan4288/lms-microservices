package com.smoothstack.orchestrator.model;

import java.io.Serializable;
import java.util.Optional;

public class BookCopiesId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Book book;

	private Branch branch;

	public BookCopiesId() {
		super();
	}

	public BookCopiesId(Book book, Branch branch) {
		super();
		this.book = book;
		this.branch = branch;
	}

	public BookCopiesId(Integer bookId, Integer branchId) {
		// TODO Auto-generated constructor stub
	}

	public BookCopiesId(Optional<Book> findById, Branch branch2) {
		// TODO Auto-generated constructor stub
	}

	public BookCopiesId(Optional<Book> findById, Optional<Branch> findById2) {
		// TODO Auto-generated constructor stub
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
}