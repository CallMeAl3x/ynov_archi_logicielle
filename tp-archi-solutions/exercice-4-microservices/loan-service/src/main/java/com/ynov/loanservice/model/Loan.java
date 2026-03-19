package com.ynov.loanservice.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long bookId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private LocalDate loanDate = LocalDate.now();

    private LocalDate returnDate;

    @Column(nullable = false)
    private String status = "ACTIVE";  // ACTIVE | RETURNED

    public Loan() {}

    public Loan(Long id, Long bookId, Long userId, LocalDate loanDate, LocalDate returnDate, String status) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public LocalDate getLoanDate() { return loanDate; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private Long bookId;
        private Long userId;
        private LocalDate loanDate = LocalDate.now();
        private LocalDate returnDate;
        private String status = "ACTIVE";

        public Builder id(Long id) { this.id = id; return this; }
        public Builder bookId(Long bookId) { this.bookId = bookId; return this; }
        public Builder userId(Long userId) { this.userId = userId; return this; }
        public Builder loanDate(LocalDate loanDate) { this.loanDate = loanDate; return this; }
        public Builder returnDate(LocalDate returnDate) { this.returnDate = returnDate; return this; }
        public Builder status(String status) { this.status = status; return this; }

        public Loan build() {
            return new Loan(id, bookId, userId, loanDate, returnDate, status);
        }
    }
}
