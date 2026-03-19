package com.ynov.loanservice.service;

import com.ynov.loanservice.client.BookClient;
import com.ynov.loanservice.client.UserClient;
import com.ynov.loanservice.exception.BusinessException;
import com.ynov.loanservice.exception.ResourceNotFoundException;
import com.ynov.loanservice.model.Loan;
import com.ynov.loanservice.repository.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookClient     bookClient;
    private final UserClient     userClient;

    public LoanService(LoanRepository loanRepository, BookClient bookClient, UserClient userClient) {
        this.loanRepository = loanRepository;
        this.bookClient = bookClient;
        this.userClient = userClient;
    }

    public Loan createLoan(Long bookId, Long userId) {
        // Validate via inter-service calls
        if (!bookClient.bookExists(bookId)) {
            throw new ResourceNotFoundException("Book not found: " + bookId);
        }
        if (!userClient.userExists(userId)) {
            throw new ResourceNotFoundException("User not found: " + userId);
        }
        if (loanRepository.existsByBookIdAndStatus(bookId, "ACTIVE")) {
            throw new BusinessException("Book " + bookId + " already has an active loan.");
        }

        // Mark book unavailable
        bookClient.updateAvailability(bookId, false);

        Loan loan = Loan.builder()
                .bookId(bookId)
                .userId(userId)
                .loanDate(LocalDate.now())
                .status("ACTIVE")
                .build();

        return loanRepository.save(loan);
    }

    public Loan returnLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found: " + loanId));

        if (!"ACTIVE".equals(loan.getStatus())) {
            throw new BusinessException("Loan " + loanId + " is not active.");
        }

        loan.setReturnDate(LocalDate.now());
        loan.setStatus("RETURNED");

        // Mark book available again
        bookClient.updateAvailability(loan.getBookId(), true);

        return loanRepository.save(loan);
    }

    @Transactional(readOnly = true)
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Loan> getActiveLoans() {
        return loanRepository.findByStatus("ACTIVE");
    }
}
