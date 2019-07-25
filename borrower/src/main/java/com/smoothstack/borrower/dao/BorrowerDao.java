package com.smoothstack.borrower.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.borrower.entity.Borrower;

@Repository
public interface BorrowerDao extends JpaRepository<Borrower, Integer>{
}