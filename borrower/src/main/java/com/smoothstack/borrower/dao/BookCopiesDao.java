package com.smoothstack.borrower.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smoothstack.borrower.entity.BookCopies;
import com.smoothstack.borrower.entity.BookCopiesId;

@Repository
public interface BookCopiesDao extends JpaRepository<BookCopies, BookCopiesId> {
	
	@Query(value="SELECT * FROM book_copies WHERE book_id = ?1 AND branch_id = ?2", nativeQuery = true)
	public Optional<BookCopies> findByBranchAndBook(Integer bookId, Integer branchId);
	
	@Query(value="SELECT no_of_copies FROM book_copies WHERE book_id = ?1 AND branch_id = ?2", nativeQuery = true)
	public Optional<Integer> currentNoOfCopies(Integer bookId, Integer branchId);
	
	@Query(value="SELECT * FROM book_loans WHERE book_id = ?1 AND branch_id = ?2 AND card_no = ?3", nativeQuery = true)
	public Optional<BookCopies> existInBookLoans(Integer borrowerId, Integer branchId, Integer bookId);
	
	@Query(value = "SELECT * FROM book_copies WHERE branch_id = ?1 AND no_of_copies > 0", nativeQuery = true)
	public Optional<List<BookCopies>> getPresentBooksFromBranch(Integer branchId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE book_copies bc set bc.no_of_copies = ? where bc.book_id = ? and bc.branch_id = ?", nativeQuery = true)
	public Integer updateNoOfCopies(Integer noOfCopies, Integer bookId, Integer branchId);
	
	@Modifying
	@Transactional
	@Query(value = "delete from book_loans where book_id = ? and branch_id = ? and card_no = ?", 
	  nativeQuery = true)
	public void removeFromBookLoans(Integer bookId, Integer branchId, Integer borrowerId);
}
