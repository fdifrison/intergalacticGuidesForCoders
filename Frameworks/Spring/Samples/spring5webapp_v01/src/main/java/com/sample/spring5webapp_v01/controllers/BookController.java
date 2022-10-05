package com.sample.spring5webapp_v01.controllers;

import com.sample.spring5webapp_v01.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books") // uri where the view is hosted
    public String getBooks(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "books/list"; //search for a template named "list" in the directory templates.books
    }
}
