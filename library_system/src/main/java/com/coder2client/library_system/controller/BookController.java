package com.coder2client.library_system.controller;

import com.coder2client.library_system.dto.BookDTO;
import com.coder2client.library_system.entity.Book;
import com.coder2client.library_system.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("addBook")
    //url: http://localhost:8081/api/v1/books/addBook
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {

        logger.info("Adding a book .....");

        BookDTO savedBookDTO = bookService.addBook(bookDTO);

        return new ResponseEntity<>(savedBookDTO, HttpStatus.CREATED);
    }

    @GetMapping("allBooks")
    //url http://localhost:8081/api/v1/books/allBooks
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> allBooks = bookService.getAllBooks();

        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    @GetMapping("{id}")
    // url http://localhost:8081/api/v1/books/2
    public ResponseEntity<BookDTO> getBook(@PathVariable("id") Long bookId) {

        BookDTO bookById = bookService.getBookById(bookId);

        return new ResponseEntity<>(bookById, HttpStatus.OK);
    }

    @PatchMapping("updateBook/{id}")
    //url http://localhost:8081/api/v1/books/updateBook/1
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        bookDTO.setId(id);
        BookDTO bookUpdated = bookService.updateBook(bookDTO);
        return new ResponseEntity<>(bookUpdated, HttpStatus.OK);
    }

    @DeleteMapping("deleteBook/{id}")
    //url http://localhost:8081/api/v1/books/deleteBook/1
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteteBook(id);

        return new ResponseEntity<>("Book successfully deleted", HttpStatus.OK);
    }

    @GetMapping("search-title")
    //url http://localhost:8081/api/v1/books/search-title?title=Head First Java
    public ResponseEntity<List<BookDTO>> searchBookByTitle(@RequestParam String title) {
        List<BookDTO> books = bookService.findBookByTitle(title);

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("search-title-author")
    //url http://localhost:8081/api/v1/books/search?title=java&author=josh
    public ResponseEntity<List<BookDTO>> searchByTitleAndAuthor(@RequestParam String title, @RequestParam String author) {
        List<BookDTO> bookByTitleAndAuthors = bookService.findBookByTitleAndAuthor(title, author);

        return new ResponseEntity<>(bookByTitleAndAuthors, HttpStatus.OK);
    }

    @GetMapping("search")
    //url http://localhost:8081/api/v1/books/search?title=java&author=josh
    public ResponseEntity<List<BookDTO>> searchBooks(@RequestParam(required = false) String title,
                                                     @RequestParam(required = false) String author,
                                                     @RequestParam(required = false) String isbn,
                                                     @RequestParam(required = false) String barcodeNumber) {
        List<BookDTO> booksByCriteria = bookService.findBooksByCriteria(title, author, isbn, barcodeNumber);

        return new ResponseEntity<>(booksByCriteria, HttpStatus.OK);
    }
}