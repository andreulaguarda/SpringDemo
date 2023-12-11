package com.andreulaguarda.SpringDemo.controller;

import com.andreulaguarda.SpringDemo.entity.Book;
import com.andreulaguarda.SpringDemo.repository.BookRepository;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    // atributos
    private BookRepository bookRepository;

    private final Logger log = LoggerFactory.getLogger(BookController.class);

    // constructores

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    // CRUD sobre la entidad Book

    @Operation(summary = "Find All Books", description="Find all books.")
    @GetMapping("/api/books")
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Operation(summary = "Find Book", description="Find one book by its id.")
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {

        Optional<Book> bookOpt = bookRepository.findById(id);

        if (bookOpt.isPresent()) {
            return ResponseEntity.ok(bookOpt.get());
        } else {
            return ResponseEntity.notFound().build();
            }
    }

    @Operation(summary = "Create Book", description="Create a new book.")
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book) {
        // guardar el libro recibido por parametro en la base de datos
        if (book.getId() != null) {
            log.warn("Trying to create a book with id.");
            return ResponseEntity.badRequest().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);

    }

    @Operation(summary = "Update Book", description="Update an existing book.")
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book) {
        if (book.getId() == null) {
            log.warn("Trying to update a non existent book.");
            return ResponseEntity.badRequest().build();
        }
        if(!bookRepository.existsById(book.getId())) {
            log.warn("Trying to update a non existent book.");
            return ResponseEntity.notFound().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Delete Book", description="Delete an existing book.")
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id) {

        if(!bookRepository.existsById(id)) {
            log.warn("Trying to delete a non existent book.");
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    // eliminar todos los libros en la base de datos
    @Hidden
    @DeleteMapping("/api/books")
    public ResponseEntity<Book> deleteAll() {
        log.info("REST Request for delete all books");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }


}



