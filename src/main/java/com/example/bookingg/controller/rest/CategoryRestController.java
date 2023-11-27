package com.example.bookingg.controller.rest;


import com.example.bookingg.repository.CategoryRepository;
import com.example.bookingg.service.response.SelectOptionResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/categories")
@AllArgsConstructor
public class CategoryRestController {
    private final CategoryRepository categoryRepository;

    @GetMapping
    public List<SelectOptionResponse> getSelectOption(){
        return categoryRepository.findAll().stream().map(categories -> new SelectOptionResponse(categories.getId().toString(), categories.getName())).collect(Collectors.toList());
    }
}
