package com.minilibarary.repository;

import com.minilibarary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByAuthorAndTitle(String title, String author);

    Optional<Book>findById(Long bookId);

}
