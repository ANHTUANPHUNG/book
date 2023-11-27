package com.example.bookingg.service.bookService.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class BookListResponse {
    private Long id;

    private String title;
    private String description;
    private BigDecimal price;
    private String publishDate;

    private String status;

    private String categories;
    private String authors;

}
