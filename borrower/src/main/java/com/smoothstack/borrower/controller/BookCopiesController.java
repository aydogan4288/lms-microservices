package com.smoothstack.borrower.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.borrower.dao.BookCopiesDao;
import com.smoothstack.borrower.entity.BookCopies;

@RestController
@RequestMapping("lms")
public class BookCopiesController {

	@Autowired
	BookCopiesDao bookCopiesDao;

	@GetMapping(value = "/book-copies", produces = { "application/json", "application/xml" })
	public List<BookCopies> getAllBookCopies() {
		return bookCopiesDao.findAll();
	}

//	@GetMapping("/book-copies/{id}")
//	public Optional<BookCopies> getBookCopies(@Valid @PathVariable("id") int id) {
//		Optional<BookCopies> bookCopies = bookCopiesDao.findById(id);
//		return bookCopies;
//	}

	@PostMapping(value = "/book-copies", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public BookCopies createBook(@Valid @RequestBody BookCopies bookCopies) {
		return bookCopiesDao.save(bookCopies);
	}

//	@RequestMapping(value = "/book-copies/{id}", method = RequestMethod.DELETE)
//	public void deleteBookCopies(@PathVariable("id") int id) {
//		BookCopies bookCopies = bookCopiesDao.getOne(id);
//		bookCopiesDao.delete(bookCopies);
//	}
	
	@RequestMapping(value = "/book-copies/{id}", method = RequestMethod.PUT, produces = { "application/json",
			"application/xml" }, consumes = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.ACCEPTED)
	public BookCopies updateBookCopies(@Valid @RequestBody BookCopies bookCopies) {
		return bookCopiesDao.save(bookCopies);
	}
}

