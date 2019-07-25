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

import com.smoothstack.borrower.dao.BranchDao;
import com.smoothstack.borrower.entity.Branch;

@RestController
@RequestMapping("/lms")
public class BranchController {
	
	@Autowired
	BranchDao branchDao;
	
	@GetMapping("/branches")
	public List<Branch> getAllranches() {
		return branchDao.findAll();
	}
	
	@GetMapping("/branch/{id}")
	public Optional<Branch> getBranch(@Valid @PathVariable("id") int id) {
		Optional<Branch> branch = branchDao.findById(id);
		return branch;
	}
	
	@PostMapping("/branch")
	public Branch createBranch(@Valid @RequestBody Branch branch) {
		return branchDao.save(branch);
	}
	
	@RequestMapping(value = "/branch/{id}", method = RequestMethod.DELETE)
	public void deleteBranch(@PathVariable("id") int id) {
		Branch branch = branchDao.getOne(id);
		branchDao.delete(branch);
	}
	
	@RequestMapping(value = "/branch{id}", method = RequestMethod.PUT)
	public Branch updateBranch(@Valid @RequestBody Branch branch ) {
		return branchDao.save(branch);
	}
}

