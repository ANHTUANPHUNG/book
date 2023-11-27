package com.example.bookingg.service.categoryService;


import com.example.bookingg.repository.CategoryRepository;
import com.example.bookingg.service.response.SelectOptionResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;
    public List<SelectOptionResponse> findAll(){
        return categoryRepository.findAll().stream()
                .map(category -> new SelectOptionResponse(category.getId().toString(), category.getName()))
                .collect(Collectors.toList());
    }

}
