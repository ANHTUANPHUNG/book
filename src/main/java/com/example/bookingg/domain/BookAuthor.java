package com.example.bookingg.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "book_authors")
@Entity
@Data
@NoArgsConstructor
public class BookAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Author author;

    public BookAuthor(Book book, Author author) {
        this.book = book;
        this.author = author;
    }
}
