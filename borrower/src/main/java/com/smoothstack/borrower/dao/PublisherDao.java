package com.smoothstack.borrower.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.borrower.entity.Publisher;

@Repository
public interface PublisherDao extends JpaRepository<Publisher, Integer> {
	
	
}
