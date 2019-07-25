package com.smoothstack.orchestrator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.smoothstack.orchestrator.model.BookLoans;

@Service
public class BorrowerService {

	@Autowired
	RestTemplate restTemplate;

	public BookLoans getBookLoans() {
		return restTemplate.getForEntity("http://borrower-service/lms/book-loans", BookLoans.class).getBody();
	}

	public ResponseEntity<BookLoans> addBookLoans(@RequestBody BookLoans bookLoans, Integer borrowerId,
			Integer branchId, Integer bookId) {
		return restTemplate.postForEntity("http://borrower-service/borrower/" + borrowerId + "/branch/" + branchId + "/book/" + bookId,
				bookLoans, BookLoans.class);
	}

	public ResponseEntity<BookLoans> returnBookLoans(@RequestBody BookLoans bookLoans, @PathVariable("borrowerId") Integer borrowerId,
			@PathVariable("branchId") Integer branchId, @PathVariable("bookId") Integer bookId) {

		return restTemplate.postForEntity("http://borrower-service/return-borrower/" + borrowerId + "/branch/" + branchId + "/book/" + bookId,
				bookLoans, BookLoans.class);
	}
}
