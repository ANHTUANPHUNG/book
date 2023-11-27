package com.example.bookingg.service.bookService.request;

import com.example.bookingg.service.request.SelectOptionRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BookSaveRequest {
    private String title;
    private String description;

    private String price;
    private String publishDate;

    private List<String> idAuthors;
    private String status;
    private SelectOptionRequest categories;
}
