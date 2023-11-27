package com.example.bookingg.domain;


import com.example.bookingg.domain.Enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate publishDate;

    private String description;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Boolean deleted;
    @ManyToOne
    private Categories categories;

    @OneToMany(mappedBy = "book")
    private List<BookAuthor> bookAuthors;
}
