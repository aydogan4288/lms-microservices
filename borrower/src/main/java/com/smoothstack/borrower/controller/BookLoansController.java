package com.smoothstack.borrower.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.borrower.dao.BookCopiesDao;
import com.smoothstack.borrower.dao.BookDao;
import com.smoothstack.borrower.dao.BookLoansDao;
import com.smoothstack.borrower.dao.BorrowerDao;
import com.smoothstack.borrower.dao.BranchDao;
import com.smoothstack.borrower.entity.BookCopies;
import com.smoothstack.borrower.entity.BookLoans;
import com.smoothstack.borrower.entity.BookLoansId;
import com.smoothstack.borrower.service.BookCopiesService;

@RestController
@RequestMapping("/lms")
public class BookLoansController {

	@Autowired
	BookCopiesService bookCopiesService;

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

	@GetMapping(value = "/book-loans", produces = { "application/json"})
	public List<BookLoans> getAllBookLoans() {
		return bookLoansDao.findAll();
	}

	@GetMapping(value = "/book-loans/{id}", produces = { "application/json"})
	public ResponseEntity<BookLoans> getBookLoan(@Valid @PathVariable("id") BookLoansId bookLoansId) {
		Optional<BookLoans> bookLoans = bookLoansDao.findById(bookLoansId);
		try {
			return new ResponseEntity<BookLoans>(bookLoans.get(), HttpStatus.OK);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<BookLoans>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/book-loans", method = RequestMethod.POST, produces = { "application/json"}, consumes = { "application/json"})
	public ResponseEntity<BookLoans> creatBookLoans(@Valid @RequestBody BookLoans bookLoans) {
		try {
			if (bookLoans.getBookLoansId() == null) {
				return new ResponseEntity<BookLoans>(HttpStatus.BAD_REQUEST);
			}
			bookLoansDao.save(bookLoans);
			return new ResponseEntity<BookLoans>(bookLoans, HttpStatus.CREATED);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<BookLoans>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/borrower/{borrowerId}/branch/{branchId}/book/{bookId}", method = RequestMethod.POST, produces = {
			"application/json"}, consumes = { "application/json"})
	public ResponseEntity<BookLoans> creatBookLoansById(@PathVariable("borrowerId") Integer borrowerId,
			@PathVariable("branchId") Integer branchId, @PathVariable("bookId") Integer bookId) {

		if ((bookId == null) || (branchId == null) || (borrowerId == null)) {
			return new ResponseEntity<BookLoans>(HttpStatus.BAD_REQUEST);
		}

		// Getting the number oc copy at the specific branch
		Optional<Integer> noOfCopies = bookCopiesDao.currentNoOfCopies(bookId, branchId);
		// Checking if the borrower has already the copy
		Optional<BookCopies> existInBookLoans = bookCopiesService.ckeckBorrowerInBookLoans(borrowerId, branchId,
				bookId);

		if (existInBookLoans.isPresent() || (noOfCopies.get() < 1)) {
			return new ResponseEntity<BookLoans>(HttpStatus.NOT_FOUND);
		}

		// Creating new Timestamps to pass as argument
		Calendar calendar = Calendar.getInstance();
		java.sql.Date dateOut = new java.sql.Date(calendar.getTime().getTime());
		calendar.add(Calendar.DATE, +7);
		java.sql.Date dueDate = new java.sql.Date(calendar.getTime().getTime());

		// Creating the loan
		BookLoansId bookLoansId = new BookLoansId(branchDao.findById(branchId).get(), bookDao.findById(bookId).get(),
				borrowerDao.findById(borrowerId).get());
		BookLoans bookLoans = new BookLoans(bookLoansId, dateOut, dueDate);
		bookLoansDao.save(bookLoans);

		// Decreasing the copy num in book_copies table
		Integer updatedCopies = noOfCopies.get() - 1;
		bookCopiesService.updateNoOfCopies(updatedCopies, bookId, branchId);

		System.out.println("Borrower Id : " + borrowerId + " Branch Id: " + branchId + " book Id: " + bookId);
		return new ResponseEntity<BookLoans>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/return-borrower/{borrowerId}/branch/{branchId}/book/{bookId}", method = RequestMethod.PUT, produces = {
			"application/json"}, consumes = { "application/json"})
	public ResponseEntity<BookLoans> returnBookLoansById(@PathVariable("borrowerId") Integer borrowerId,
			@PathVariable("branchId") Integer branchId, @PathVariable("bookId") Integer bookId) {

		if ((bookId == null) || (branchId == null) || (borrowerId == null)) {
			return new ResponseEntity<BookLoans>(HttpStatus.BAD_REQUEST);
		}
		Optional<BookCopies> bookCopies = bookCopiesService.getBookCopiesById(bookId, branchId);
		// Getting the number oc copy at the specific branch
		Optional<Integer> noOfCopies = bookCopiesDao.currentNoOfCopies(bookId, branchId);
		// Checking if the borrower has already the copy
		Optional<BookCopies> existInBookLoans = bookCopiesService.ckeckBorrowerInBookLoans(bookId, branchId,
				borrowerId);

		if (!existInBookLoans.isPresent()) {
			return new ResponseEntity<BookLoans>(HttpStatus.NOT_FOUND);
		}

		// Increasing the copy num in book_copies table
		Integer updatedCopies = noOfCopies.get() + 1;
		bookCopiesService.updateNoOfCopies(updatedCopies, bookId, branchId);
		// Remove the Book Loan
		bookCopiesService.removeFromBookLoans(bookId, branchId, borrowerId);

		System.out.println("Borrower Id : " + borrowerId + " Branch Id: " + branchId + " book Id: " + bookId);
		return new ResponseEntity<BookLoans>(HttpStatus.ACCEPTED);
	}

//	@RequestMapping(value = "/book-loans/{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<BookLoans> deleteBookLoans(@PathVariable("id") BookLoansId bookLoansId) {
//		try {
//			bookLoansDao.deleteById(bookLoansId);
//			return new ResponseEntity<BookLoans>(HttpStatus.NO_CONTENT);
//		} catch (EmptyResultDataAccessException e) {
//			return new ResponseEntity<BookLoans>(HttpStatus.BAD_REQUEST);
//		}
//	}
//
//	@RequestMapping(value = "/book-loans{id}", method = RequestMethod.PUT)
//	public BookLoans updateBookLoans(@Valid @RequestBody BookLoans bookLoans) {
//		return bookLoansDao.save(bookLoans);
//	}
}

