package com.minilibarary.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;

    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "isbn")
    private int isbn;
    @Column(name = "publication_year")
    private int publicationYear;

    @Column(name = "book_borrowed")
    private boolean borrow;


    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long id) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public boolean isBorrow() {
        return borrow;
    }

    public void setBorrow(boolean borrow) {
        this.borrow = borrow;
    }
}


