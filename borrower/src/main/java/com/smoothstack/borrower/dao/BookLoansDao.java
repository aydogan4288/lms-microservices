package com.smoothstack.borrower.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.borrower.entity.BookLoans;
import com.smoothstack.borrower.entity.BookLoansId;

@Repository
public interface BookLoansDao extends JpaRepository<BookLoans, BookLoansId> {
	
	
}
