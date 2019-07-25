package com.smoothstack.orchestrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.smoothstack.orchestrator.model.BookLoans;
import com.smoothstack.orchestrator.service.BorrowerService;

@RestController
@RequestMapping("/lms")
public class BorrowerController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	BorrowerService borrowerService;

	@RequestMapping(value = "/book-loans", consumes = { "application/json", "application/xml" } )
	public BookLoans getAllBookLoans() {
		return borrowerService.getBookLoans();
	}

	@RequestMapping(value = "/borrower/{borrowerId}/branch/{branchId}/book/{bookId}", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	public ResponseEntity<BookLoans> addBookLoansById(@RequestBody BookLoans bookLoans,
			@PathVariable("borrowerId") Integer borrowerId, @PathVariable("branchId") Integer branchId,
			@PathVariable("bookId") Integer bookId) {
		return borrowerService.addBookLoans(bookLoans, borrowerId, branchId, bookId);
	}

	@RequestMapping(value = "/return-borrower/{borrowerId}/branch/{branchId}/book/{bookId}", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	public ResponseEntity<BookLoans> returnBookLoansById(@RequestBody BookLoans bookLoans,
			@PathVariable("borrowerId") Integer borrowerId, @PathVariable("branchId") Integer branchId,
			@PathVariable("bookId") Integer bookId) {
		return borrowerService.returnBookLoans(bookLoans, borrowerId, branchId, bookId);
	}
}
