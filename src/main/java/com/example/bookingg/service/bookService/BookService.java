package com.example.bookingg.service.bookService;

import com.example.bookingg.domain.Author;
import com.example.bookingg.domain.Book;
import com.example.bookingg.domain.BookAuthor;
import com.example.bookingg.domain.Categories;
import com.example.bookingg.domain.Enums.Status;
import com.example.bookingg.exception.ResourceNotFoundException;
import com.example.bookingg.repository.BookAuthorRepository;
import com.example.bookingg.repository.BookRepository;
import com.example.bookingg.service.bookService.request.BookSaveRequest;
import com.example.bookingg.service.bookService.response.BookDetailResponse;
import com.example.bookingg.service.bookService.response.BookListResponse;
import com.example.bookingg.util.AppMessage;
import com.example.bookingg.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    private final BookAuthorRepository bookAuthorRepository;

    public void create(BookSaveRequest request){
        var book = AppUtil.mapper.map(request, Book.class);
        book.setStatus(Status.valueOf(request.getStatus()));
        book.setDeleted(false);
        book = bookRepository.save(book);
        Book finalBook = book;
        bookAuthorRepository.saveAll(request
                .getIdAuthors()
                .stream()
                .map(id -> new BookAuthor(finalBook, new Author(Long.valueOf(id))))
                .collect(Collectors.toList()));
    }

public Page<BookListResponse> getAll(String search , Pageable pageable, BigDecimal min, BigDecimal max) {
    search = "%"+search+"%";
    return bookRepository.searchAllByBook(search, pageable,min,max)
            .map(book -> BookListResponse.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .description(book.getDescription())
                    .price(book.getPrice())
                    .publishDate(String.valueOf(book.getPublishDate()))
                    .status(String.valueOf(book.getStatus()))
                    .categories(book.getCategories().getName())
                    .authors(book.getBookAuthors()
                            .stream()
                            .map(bookAuthor -> bookAuthor.getAuthor().getName())
                            .collect(Collectors.joining(", "))) // Chuyển thành chuỗi
                    .build());
}
    public Book findByIdBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        (String.format(AppMessage.ID_NOT_FOUND, "Product", id)));
    }
    public void delete(Long id){
        Book book= findByIdBook(id);
        book.setDeleted(true);
        bookRepository.save(book);
    }
    public BookDetailResponse findById(Long id){
        var book = bookRepository.findById(id).orElse(new Book());
        var result = AppUtil.mapper.map(book, BookDetailResponse.class);
        result.setStatus((book.getStatus()));
        result.setCategoriesId(book.getCategories().getId());
        result.setAuthorsId(book.getBookAuthors().stream()
                .map(e -> e.getAuthor().getId())
                .collect(Collectors.toList()));
        return result;
    }
    @Transactional
    public void update(BookSaveRequest request, Long id) {
        var book = bookRepository.findById(id).orElse(new Book());
        book.setStatus(Status.valueOf(request.getStatus()));
        book.setCategories(new Categories());
        AppUtil.mapper.map(request, book);
        bookRepository.save(book);
        bookAuthorRepository.deleteAllById(book.getBookAuthors().stream()
                .map(BookAuthor::getId)
                .collect(Collectors.toList()));
        saveCategoryAndActor(request, book);

    }
    public void saveCategoryAndActor(BookSaveRequest request, Book book){
        var bookAuthors = new ArrayList<BookAuthor>();
        for (String idAuthor : request.getIdAuthors()) {
            Author author = new Author(Long.valueOf(idAuthor));
            bookAuthors.add(new BookAuthor(book, author));
        }
        bookAuthorRepository.saveAll(bookAuthors);
    }
}
