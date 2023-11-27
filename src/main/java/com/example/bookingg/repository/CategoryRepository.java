package com.example.bookingg.repository;

import com.example.bookingg.domain.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories, Long> {
}
