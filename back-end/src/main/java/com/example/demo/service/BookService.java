package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book saveBook(@Valid Book book) {
        return bookRepository.save(book);

    }

    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(Book bookToUpdate) {
        if (bookToUpdate.getBookId() == 0) {

            throw new IllegalArgumentException("Book id cannot be 0");
        } else if (!findBookById(bookToUpdate.getBookId()).isPresent()) {

            throw new IllegalArgumentException("Book was not found by parameters provided");
        }
        return bookRepository.save(bookToUpdate);
    }

}
