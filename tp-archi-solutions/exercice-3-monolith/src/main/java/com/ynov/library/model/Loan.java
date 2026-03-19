package com.ynov.library.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @Column(nullable = false)
    private LocalDate loanDate = LocalDate.now();

    private LocalDate returnDate;  // null while loan is active

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status = LoanStatus.ACTIVE;

    // No-arg constructor
    public Loan() {}

    // All-arg constructor
    public Loan(Long id, Book book, AppUser user, LocalDate loanDate, LocalDate returnDate, LoanStatus status) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    // Getters
    public Long getId() { return id; }
    public Book getBook() { return book; }
    public AppUser getUser() { return user; }
    public LocalDate getLoanDate() { return loanDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public LoanStatus getStatus() { return status; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setBook(Book book) { this.book = book; }
    public void setUser(AppUser user) { this.user = user; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public void setStatus(LoanStatus status) { this.status = status; }

    // Static Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private Book book;
        private AppUser user;
        private LocalDate loanDate = LocalDate.now();
        private LocalDate returnDate;
        private LoanStatus status = LoanStatus.ACTIVE;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder book(Book book) { this.book = book; return this; }
        public Builder user(AppUser user) { this.user = user; return this; }
        public Builder loanDate(LocalDate loanDate) { this.loanDate = loanDate; return this; }
        public Builder returnDate(LocalDate returnDate) { this.returnDate = returnDate; return this; }
        public Builder status(LoanStatus status) { this.status = status; return this; }

        public Loan build() {
            return new Loan(id, book, user, loanDate, returnDate, status);
        }
    }
}
