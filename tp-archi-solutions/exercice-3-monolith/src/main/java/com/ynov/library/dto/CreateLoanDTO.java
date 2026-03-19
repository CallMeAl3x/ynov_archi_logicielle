package com.ynov.library.dto;

import jakarta.validation.constraints.NotNull;

public class CreateLoanDTO {

    @NotNull(message = "Book ID is required")
    private Long bookId;

    @NotNull(message = "User ID is required")
    private Long userId;

    // No-arg constructor
    public CreateLoanDTO() {}

    // All-arg constructor
    public CreateLoanDTO(Long bookId, Long userId) {
        this.bookId = bookId;
        this.userId = userId;
    }

    // Getters
    public Long getBookId() { return bookId; }
    public Long getUserId() { return userId; }

    // Setters
    public void setBookId(Long bookId) { this.bookId = bookId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
