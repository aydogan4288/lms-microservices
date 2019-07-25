package com.smoothstack.borrower.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.borrower.entity.Author;

@Repository
public interface AuthorDao extends JpaRepository<Author, Integer> {

}

