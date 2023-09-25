package com.minilibarary.controller;

import com.minilibarary.model.Book;
import com.minilibarary.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/books/")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CacheBook cacheBook;

    @GetMapping("/books/{book}")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    @PostMapping("/addBook/")
    public ResponseEntity<String> addBook(@Valid @RequestBody Book book) {
        return cacheBook.addBook(book);
    }
    @GetMapping("/isBorrowed/{bookId}")
    public boolean isBorrowed(Long bookId) {
        return cacheBook.isBorrowed(bookId);
    }
    @DeleteMapping("/deleteBook/{bookId}")
    public ResponseEntity<String>deleteBook(@PathVariable Long bookId){
        return cacheBook.deleteBook(bookId);
    }

    @GetMapping("/findByAuthor/{title_author}")
    public ResponseEntity<String >findBookByTitleAndAuthor(@PathVariable String title, String author) {
        return cacheBook.findBookByTitleAndAuthor(title, title);
    }
    @GetMapping("/findById/{bookId}")
    public ResponseEntity<String>findById(Long bookId) {
        return cacheBook.findById(bookId);
    }
    @GetMapping("/borrow_book/{bookId}")
    public ResponseEntity<String> borrowBook (Long bookId, Long userId) {
        return cacheBook.borrowBook(bookId, userId);
    }
    @PutMapping("returnBook{bookId}")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId, Long userId, @RequestBody Book book) {
        return cacheBook.returnBook(bookId, userId, book);
    }
}


