package com.ynov.library.config;

import com.ynov.library.model.AppUser;
import com.ynov.library.model.Book;
import com.ynov.library.model.Loan;
import com.ynov.library.model.LoanStatus;
import com.ynov.library.repository.BookRepository;
import com.ynov.library.repository.LoanRepository;
import com.ynov.library.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final LoanRepository loanRepository;

    public DataInitializer(BookRepository bookRepository, UserRepository userRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
    }

    @Override
    public void run(String... args) {
        log.info("Initializing demo data...");

        // --- 5 Books ---
        Book b1 = bookRepository.save(Book.builder()
                .title("Clean Code")
                .author("Robert C. Martin")
                .isbn("978-0132350884")
                .publishYear(2008)
                .genre("Software Engineering")
                .available(true)
                .build());

        bookRepository.save(Book.builder()
                .title("Design Patterns")
                .author("Gang of Four")
                .isbn("978-0201633610")
                .publishYear(1994)
                .genre("Software Architecture")
                .available(true)
                .build());

        bookRepository.save(Book.builder()
                .title("The Pragmatic Programmer")
                .author("Andrew Hunt")
                .isbn("978-0135957059")
                .publishYear(2019)
                .genre("Software Engineering")
                .available(true)
                .build());

        bookRepository.save(Book.builder()
                .title("Domain-Driven Design")
                .author("Eric Evans")
                .isbn("978-0321125217")
                .publishYear(2003)
                .genre("Software Architecture")
                .available(true)
                .build());

        Book b5 = bookRepository.save(Book.builder()
                .title("Effective Java")
                .author("Joshua Bloch")
                .isbn("978-0134685991")
                .publishYear(2018)
                .genre("Java")
                .available(false)   // already on loan
                .build());

        // --- 3 Users ---
        AppUser u1 = userRepository.save(AppUser.builder()
                .firstName("Alice")
                .lastName("Dupont")
                .email("alice.dupont@example.com")
                .membershipDate(LocalDate.now().minusYears(1))
                .build());

        AppUser u2 = userRepository.save(AppUser.builder()
                .firstName("Bob")
                .lastName("Martin")
                .email("bob.martin@example.com")
                .membershipDate(LocalDate.now().minusMonths(6))
                .build());

        userRepository.save(AppUser.builder()
                .firstName("Charlie")
                .lastName("Bernard")
                .email("charlie.bernard@example.com")
                .membershipDate(LocalDate.now())
                .build());

        // --- 2 Loans ---
        loanRepository.save(Loan.builder()
                .book(b5)
                .user(u1)
                .loanDate(LocalDate.now().minusDays(10))
                .status(LoanStatus.ACTIVE)
                .build());

        loanRepository.save(Loan.builder()
                .book(b1)
                .user(u2)
                .loanDate(LocalDate.now().minusDays(35))  // overdue
                .status(LoanStatus.ACTIVE)
                .build());

        // b1 is on loan (update availability)
        b1.setAvailable(false);
        bookRepository.save(b1);

        log.info("Demo data initialized: 5 books, 3 users, 2 loans.");
    }
}
