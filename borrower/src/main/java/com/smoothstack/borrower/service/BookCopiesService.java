package com.smoothstack.borrower.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smoothstack.borrower.dao.BookCopiesDao;
import com.smoothstack.borrower.dao.BookDao;
import com.smoothstack.borrower.dao.BookLoansDao;
import com.smoothstack.borrower.dao.BorrowerDao;
import com.smoothstack.borrower.dao.BranchDao;
import com.smoothstack.borrower.entity.BookCopies;
import com.smoothstack.borrower.entity.BookLoans;
import com.smoothstack.borrower.entity.BookLoansId;

@Service
public class BookCopiesService {

	@Autowired
	BookCopiesDao bookCopiesDao;

	@Autowired
	BranchDao branchDao;

	@Autowired
	BookLoansDao bookLoansDao;

	@Autowired
	BookDao bookDao;

	@Autowired
	BorrowerDao borrowerDao;

	public Optional<BookCopies> getBookCopiesById(Integer bookId, Integer branchId) {
		return bookCopiesDao.findByBranchAndBook(bookId, branchId);
	}

	public Optional<BookCopies> ckeckBorrowerInBookLoans(Integer borrowerId, Integer branchId, Integer bookId) {
		return bookCopiesDao.existInBookLoans(borrowerId, branchId, bookId);
	}


	public Optional<Integer> getNoOfcopies(Integer bookId, Integer branchId) {
		return bookCopiesDao.currentNoOfCopies(bookId, branchId);
	}


	public void addToBookLoans(Date dateOut, Date dueDate, Integer bookId, Integer branchId, Integer borrowerId) {
		BookLoansId bookLoansId = new BookLoansId(branchDao.findById(branchId).get(), bookDao.findById(bookId).get(),
				borrowerDao.findById(borrowerId).get());
		BookLoans bookLoans = new BookLoans(bookLoansId, dateOut, dueDate);
		bookLoansDao.save(bookLoans);
		System.out.println("New BookLoans Created in Service Layer");
	}

	public void removeFromBookLoans(Integer bookId, Integer branchId, Integer borrowerId) {
		bookCopiesDao.removeFromBookLoans(bookId, branchId, borrowerId);
		System.out.println("Delete BookLoan Method from service layer");
	}

	public Integer updateNoOfCopies(Integer noOfCopies, Integer bookId, Integer branchId) {
		System.out.println("Update Number of Copies from service layer");
		return bookCopiesDao.updateNoOfCopies(noOfCopies, bookId, branchId);

	}

	public ResponseEntity<List<BookCopies>> getAllPresentBooksFromBranch(Integer branchId) {
		Optional<List<BookCopies>> bookList = bookCopiesDao.getPresentBooksFromBranch(branchId);

		if (bookList.isPresent()) {
			return new ResponseEntity<List<BookCopies>>(bookList.get(), HttpStatus.OK);
		}

		return new ResponseEntity<List<BookCopies>>(HttpStatus.NOT_FOUND);
	}
}

