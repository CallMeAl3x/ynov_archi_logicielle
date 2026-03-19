package com.ynov.bookservice.service;

import com.ynov.bookservice.exception.ResourceNotFoundException;
import com.ynov.bookservice.model.Book;
import com.ynov.bookservice.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found: " + id));
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookData) {
        Book book = getBookById(id);
        book.setTitle(bookData.getTitle());
        book.setAuthor(bookData.getAuthor());
        book.setIsbn(bookData.getIsbn());
        book.setPublishYear(bookData.getPublishYear());
        book.setGenre(bookData.getGenre());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found: " + id);
        }
        bookRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }

    public void updateAvailability(Long id, boolean available) {
        Book book = getBookById(id);
        book.setAvailable(available);
        bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailableTrue();
    }
}
