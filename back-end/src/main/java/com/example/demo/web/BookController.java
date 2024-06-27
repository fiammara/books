package com.example.demo.web;


import com.example.demo.service.BookService;
import com.example.demo.model.Book;
import io.micrometer.common.lang.NonNull;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/api/v1/book")
public class BookController {


    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> findAllBooks() {
        return bookService.findAllBooks();
    }
    @PostMapping()
    public ResponseEntity<Book> saveBook(@Valid @RequestBody Book book) {

        Book bookToSave = bookService.saveBook(book);

        return new ResponseEntity<>(bookToSave, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Optional<Book> book = bookService.findBookById(id);
        if (!book.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        bookService.deleteBookById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")

    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Book> updateBookById(@NonNull @PathVariable Long id, @Valid @RequestBody Book book) {

        if (!id.equals(book.getBookId())) {

            return ResponseEntity.badRequest().build();
        }

        bookService.updateBook(book);

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }
}
