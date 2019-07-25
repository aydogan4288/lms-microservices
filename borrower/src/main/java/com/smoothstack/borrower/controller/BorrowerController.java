package com.smoothstack.borrower.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.borrower.dao.BorrowerDao;
import com.smoothstack.borrower.entity.BookCopies;
import com.smoothstack.borrower.entity.Borrower;
import com.smoothstack.borrower.service.BookCopiesService;

@RestController
@RequestMapping("/lms")
public class BorrowerController {

	@Autowired
	BorrowerDao borrowerDao;

	@Autowired
	BookLoansController bookLoansController;

	@Autowired
	BookCopiesService bookCopiesService;

	@GetMapping(value = "/borrowers", produces = { "application/json", "application/xml" })
	public List<Borrower> getAllBorrowers() {
		return borrowerDao.findAll();
	}

	@RequestMapping(value = "/borrower", method = RequestMethod.POST, produces = { "application/json",
			"application/xml" }, consumes = { "application/json", "application/xml" })
	public ResponseEntity<Borrower> createBorrower(@Valid @RequestBody Borrower borrower) {
		if(borrower.getBorrowerName() == null) {
			return new ResponseEntity<Borrower>(HttpStatus.BAD_REQUEST);
		}
		borrowerDao.save(borrower);
		return new ResponseEntity<Borrower>(HttpStatus.CREATED);
	}

	@GetMapping(value = "/borrower/{id}", produces = {"application/json", "application/xml"})
	public ResponseEntity<Borrower> getBorrowerById(@PathVariable("id") Integer id) {
		Optional<Borrower> borrower = borrowerDao.findById(id);
		if(borrower.isPresent()) {
			return new ResponseEntity<Borrower>(HttpStatus.OK);
		}
		return new ResponseEntity<Borrower>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/branch/{branchId}/books", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<BookCopies>> getAllAvailableBooksAtBranch(@Valid @PathVariable Integer branchId) {
		return bookCopiesService.getAllPresentBooksFromBranch(branchId);
	}

}

