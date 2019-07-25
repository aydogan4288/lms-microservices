package com.smoothstack.borrower.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.borrower.dao.BookDao;
import com.smoothstack.borrower.entity.Book;

@RestController
@RequestMapping("/lms")
public class BookController {
	
	@Autowired
	BookDao bookDao;
	
	@GetMapping("/book")
	public List<Book> getAllBooks() {
		return bookDao.findAll();
	}
	
	@GetMapping("/book/{id}")
	public Optional<Book> getBook(@Valid @PathVariable("id") int id) {
		Optional<Book> book = bookDao.findById(id);
		return book;
	}
	
	@PostMapping("/book")
	public Book createBook(@Valid @RequestBody Book book) {
		return bookDao.save(book);
	}
	
	@RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
	public void deleteBook(@PathVariable("id") int id) {
		Book book = bookDao.getOne(id);
		bookDao.delete(book);
	}
	
	@RequestMapping(value = "/book{id}", method = RequestMethod.PUT)
	public Book updateBook(@Valid @RequestBody Book book ) {
		return bookDao.save(book);
	}
	
	
	
}
