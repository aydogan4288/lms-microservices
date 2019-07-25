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

import com.smoothstack.borrower.dao.PublisherDao;
import com.smoothstack.borrower.entity.Publisher;

@RestController
@RequestMapping("/lms")
public class PublisherController {

	@Autowired
	private PublisherDao publisherDao;

	@GetMapping("/publisher")
	public List<Publisher> getAllPublisher() {
		return publisherDao.findAll();
	}
	
	@GetMapping("/publisher/{id}")
	public Optional<Publisher> getPublisher(@Valid @PathVariable("id") int id) {
		Optional<Publisher> publisher = publisherDao.findById(id);
		return publisher;
	}

	// Create a new Publisher
	@PostMapping("/publisher")
	public Publisher createPublisher(@Valid @RequestBody Publisher publisher) {
		return publisherDao.save(publisher);
	}

	@RequestMapping(value = "/publisher/{id}", method = RequestMethod.DELETE)
	public void deletePublisher(@PathVariable("id") int id) {
		Publisher publisher = publisherDao.getOne(id);
		publisherDao.delete(publisher);
	}
	
	@RequestMapping(value="/publisher/{id}", method= RequestMethod.PUT)
	public Publisher updatePublisher(@Valid @RequestBody Publisher publisher) {
		return publisherDao.save(publisher);
	}
}

