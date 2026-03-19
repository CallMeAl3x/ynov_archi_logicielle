package com.ynov.library.service;

import com.ynov.library.dto.BookDTO;
import com.ynov.library.dto.CreateBookDTO;
import com.ynov.library.exception.BusinessException;
import com.ynov.library.exception.ResourceNotFoundException;
import com.ynov.library.model.Book;
import com.ynov.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", id));
        return toDTO(book);
    }

    public BookDTO createBook(CreateBookDTO dto) {
        bookRepository.findByIsbn(dto.getIsbn()).ifPresent(existing -> {
            throw new BusinessException("A book with ISBN '" + dto.getIsbn() + "' already exists.");
        });
        Book book = Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .isbn(dto.getIsbn())
                .publishYear(dto.getPublishYear())
                .genre(dto.getGenre())
                .available(true)
                .build();
        return toDTO(bookRepository.save(book));
    }

    public BookDTO updateBook(Long id, CreateBookDTO dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", id));
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setPublishYear(dto.getPublishYear());
        book.setGenre(dto.getGenre());
        return toDTO(bookRepository.save(book));
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book", id);
        }
        bookRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<BookDTO> getAvailableBooks() {
        return bookRepository.findByAvailableTrue().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookDTO> searchByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private BookDTO toDTO(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .publishYear(book.getPublishYear())
                .genre(book.getGenre())
                .available(book.isAvailable())
                .build();
    }
}
