package com.example.bookingg.service.bookService.response;

import com.example.bookingg.domain.Enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
@NoArgsConstructor
public class BookDetailResponse {
    private Long id;

    private String title;
    private String description;

    private BigDecimal price;
    private LocalDate publishDate;
    private Long categoriesId;
    private Status status;
    private List<Long> authorsId;

}
