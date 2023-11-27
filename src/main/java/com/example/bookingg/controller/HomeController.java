package com.example.bookingg.controller;

import com.example.bookingg.domain.Enums.Status;
import com.example.bookingg.service.authorService.AuthorService;
import com.example.bookingg.service.categoryService.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping(value="/home")
public class HomeController {
    private final CategoryService categoryService;
    private final AuthorService authorService;

    @GetMapping
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/index");
        view.addObject("categories", categoryService.findAll());
        view.addObject("authors", authorService.findAll());
        view.addObject("status", Status.values());
        return view;
    }
}
