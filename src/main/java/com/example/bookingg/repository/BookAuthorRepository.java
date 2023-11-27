package com.example.bookingg.repository;

import com.example.bookingg.domain.BookAuthor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long> {
    void deleteBookAuthorByBook_Id(Long book_id);

}
