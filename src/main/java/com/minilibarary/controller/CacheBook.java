package com.minilibarary.controller;

import com.minilibarary.model.Book;
import com.minilibarary.model.User;
import com.minilibarary.repository.BookRepository;
import com.minilibarary.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
    @Service
    public class CacheBook {
        @Autowired
        private BookRepository bookRepository;

        @Autowired
        private UserRepository userRepository;

        @Cacheable(cacheNames = "books", key = "#book")
        public List<Book> getAllBooks() {
            return bookRepository.findAll();
        }

        @CacheEvict(cacheNames = "addBook", key = "#book")
        public ResponseEntity<String> addBook(@Valid @RequestBody Book book) {
            bookRepository.save(book);
            return ResponseEntity.ok("saved");
        }
        @Cacheable(cacheNames = "isBorrowed", key = "#bookId")
        public boolean isBorrowed(Long bookId) {
            Optional<Book> book = bookRepository.findById(bookId);
            if(bookId == null ) {
                return true;
            }
            else {

            }return false;
        }

        @Cacheable(cacheNames = "isBorrowed", key = "#bookId")
        public ResponseEntity<String> borrowBook (Long bookId, Long userId) {
            Optional<Book> book = bookRepository.findById(bookId);
            if (book != null && !book.isEmpty()){
                bookRepository.deleteById(bookId);
            }
            return ResponseEntity.ok("you have borrowed the book");
        }
        @CacheEvict(cacheNames="deleteBook", key= "#id")
        public ResponseEntity<String>deleteBook(@PathVariable Long id){
            bookRepository.deleteById(id);
            return ResponseEntity.ok("book deleted");
        }
        @Cacheable(cacheNames = "find_books", key = "id")
        public ResponseEntity<String> findById(@PathVariable Long id) {
            bookRepository.findById(id);
            return ResponseEntity.ok("Here is the book");
        }
        @Cacheable(cacheNames = "findByTitleAuthor", key = "#title, author")
        public ResponseEntity<String>findBookByTitleAndAuthor(@PathVariable String title, String author) {
            bookRepository.findBookByAuthorAndTitle(title, author);
            return ResponseEntity.ok("Here is your boook");
        }
        @CachePut(cacheNames = "return_book", key = "#bookId")
        public ResponseEntity<String> returnBook(@PathVariable Long bookId, Long userId, @RequestBody Book book) {
            Optional<User> user = userRepository.findByUserId(userId);
            if(bookRepository.findById(bookId) == null) {
                bookRepository.save(book);
            }
            return ResponseEntity.ok("you have returned this book");
        }
}
