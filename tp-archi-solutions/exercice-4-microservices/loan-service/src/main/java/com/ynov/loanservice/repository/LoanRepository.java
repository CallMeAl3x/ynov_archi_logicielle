package com.ynov.loanservice.repository;

import com.ynov.loanservice.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByStatus(String status);

    List<Loan> findByUserIdAndStatus(Long userId, String status);

    boolean existsByBookIdAndStatus(Long bookId, String status);
}
