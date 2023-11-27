package com.example.bookingg.repository;

//import com.example.booking.domain.Room;
import com.example.bookingg.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;


public interface BookRepository extends JpaRepository<Book, Long> {


@Query(value = "SELECT b FROM Book b" +
        " WHERE (b.price BETWEEN :min AND :max) " +
        "AND  ( b.title LIKE :search OR b.categories.name LIKE :search " +
        "OR b.description LIKE :search " +
        "OR EXISTS (SELECT 1 FROM BookAuthor a " +
        "WHERE a.book = b AND a.author.name LIKE :search))")
Page<Book> searchAllByBook(@Param("search") String search, Pageable pageable , @Param("min") BigDecimal min, @Param("max") BigDecimal max);



}
