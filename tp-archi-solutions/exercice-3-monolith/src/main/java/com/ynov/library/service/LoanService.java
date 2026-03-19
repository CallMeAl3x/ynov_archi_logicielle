package com.ynov.library.service;

import com.ynov.library.dto.CreateLoanDTO;
import com.ynov.library.dto.LoanDTO;
import com.ynov.library.exception.BusinessException;
import com.ynov.library.exception.ResourceNotFoundException;
import com.ynov.library.model.AppUser;
import com.ynov.library.model.Book;
import com.ynov.library.model.Loan;
import com.ynov.library.model.LoanStatus;
import com.ynov.library.repository.BookRepository;
import com.ynov.library.repository.LoanRepository;
import com.ynov.library.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public LoanDTO createLoan(CreateLoanDTO dto) {
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book", dto.getBookId()));

        AppUser user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", dto.getUserId()));

        if (!book.isAvailable()) {
            throw new BusinessException("Book '" + book.getTitle() + "' is not available for loan.");
        }

        if (loanRepository.existsByBookIdAndStatus(book.getId(), LoanStatus.ACTIVE)) {
            throw new BusinessException("Book '" + book.getTitle() + "' already has an active loan.");
        }

        // Mark book as unavailable
        book.setAvailable(false);
        bookRepository.save(book);

        Loan loan = Loan.builder()
                .book(book)
                .user(user)
                .loanDate(LocalDate.now())
                .status(LoanStatus.ACTIVE)
                .build();

        return toDTO(loanRepository.save(loan));
    }

    public LoanDTO returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", loanId));

        if (loan.getStatus() != LoanStatus.ACTIVE) {
            throw new BusinessException("Loan " + loanId + " is not active (status: " + loan.getStatus() + ").");
        }

        loan.setReturnDate(LocalDate.now());
        loan.setStatus(LoanStatus.RETURNED);

        // Mark book as available again
        Book book = loan.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        return toDTO(loanRepository.save(loan));
    }

    @Transactional(readOnly = true)
    public List<LoanDTO> getAllLoans() {
        return loanRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LoanDTO> getActiveLoans() {
        return loanRepository.findByStatus(LoanStatus.ACTIVE).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LoanDTO> getOverdueLoans() {
        return loanRepository.findByStatus(LoanStatus.ACTIVE).stream()
                .filter(loan -> ChronoUnit.DAYS.between(loan.getLoanDate(), LocalDate.now()) > 30)
                .map(loan -> {
                    loan.setStatus(LoanStatus.OVERDUE);
                    return toDTO(loanRepository.save(loan));
                })
                .collect(Collectors.toList());
    }

    private LoanDTO toDTO(Loan loan) {
        return LoanDTO.builder()
                .id(loan.getId())
                .bookId(loan.getBook().getId())
                .bookTitle(loan.getBook().getTitle())
                .userId(loan.getUser().getId())
                .userName(loan.getUser().getFirstName() + " " + loan.getUser().getLastName())
                .loanDate(loan.getLoanDate())
                .returnDate(loan.getReturnDate())
                .status(loan.getStatus())
                .build();
    }
}
