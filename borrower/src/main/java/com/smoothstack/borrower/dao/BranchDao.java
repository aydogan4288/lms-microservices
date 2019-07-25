package com.smoothstack.borrower.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.borrower.entity.Branch;

@Repository
public interface BranchDao extends JpaRepository<Branch, Integer> {

}