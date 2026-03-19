package com.ynov.library.dto;

import com.ynov.library.model.LoanStatus;

import java.time.LocalDate;

public class LoanDTO {
    private Long       id;
    private Long       bookId;
    private String     bookTitle;
    private Long       userId;
    private String     userName;
    private LocalDate  loanDate;
    private LocalDate  returnDate;
    private LoanStatus status;

    // No-arg constructor
    public LoanDTO() {}

    // All-arg constructor
    public LoanDTO(Long id, Long bookId, String bookTitle, Long userId, String userName,
                   LocalDate loanDate, LocalDate returnDate, LoanStatus status) {
        this.id = id;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.userId = userId;
        this.userName = userName;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    // Getters
    public Long getId() { return id; }
    public Long getBookId() { return bookId; }
    public String getBookTitle() { return bookTitle; }
    public Long getUserId() { return userId; }
    public String getUserName() { return userName; }
    public LocalDate getLoanDate() { return loanDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public LoanStatus getStatus() { return status; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public void setStatus(LoanStatus status) { this.status = status; }

    // Static Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private Long bookId;
        private String bookTitle;
        private Long userId;
        private String userName;
        private LocalDate loanDate;
        private LocalDate returnDate;
        private LoanStatus status;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder bookId(Long bookId) { this.bookId = bookId; return this; }
        public Builder bookTitle(String bookTitle) { this.bookTitle = bookTitle; return this; }
        public Builder userId(Long userId) { this.userId = userId; return this; }
        public Builder userName(String userName) { this.userName = userName; return this; }
        public Builder loanDate(LocalDate loanDate) { this.loanDate = loanDate; return this; }
        public Builder returnDate(LocalDate returnDate) { this.returnDate = returnDate; return this; }
        public Builder status(LoanStatus status) { this.status = status; return this; }

        public LoanDTO build() {
            return new LoanDTO(id, bookId, bookTitle, userId, userName, loanDate, returnDate, status);
        }
    }
}
