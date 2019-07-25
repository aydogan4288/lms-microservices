package com.smoothstack.borrower.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.borrower.entity.Book;

@Repository
public interface BookDao extends JpaRepository<Book, Integer> {

	
	
}