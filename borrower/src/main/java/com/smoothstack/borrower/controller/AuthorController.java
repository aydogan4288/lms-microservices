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

import com.smoothstack.borrower.dao.AuthorDao;
import com.smoothstack.borrower.entity.Author;

@RestController
@RequestMapping("/lms")
public class AuthorController {
	
	@Autowired
	private AuthorDao authorDao;
	
	@GetMapping("/author")
	public List<Author> getAllAuthor() {
		return authorDao.findAll();
	}

	@GetMapping("/author/{id}")
	public Optional<Author> getAuthor(@Valid @PathVariable("id") int id) {
		Optional<Author> author = authorDao.findById(id);
		return author;
	}

	@PostMapping("/author")
	public Author createAuthor(@Valid @RequestBody Author author) {
		return authorDao.save(author);
	}

	@RequestMapping(value = "/author/{id}", method = RequestMethod.DELETE)
	public void deleteAuthor(@PathVariable("id") int id) {
		Author author = authorDao.getOne(id);
		authorDao.delete(author);
	}

	@RequestMapping(value = "/author/{id}", method = RequestMethod.PUT)
	public Author updateAuthor(@Valid @RequestBody Author author) {
		return authorDao.save(author);
	}
	
}
