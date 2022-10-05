package com.sample.spring5webapp_v01.controllers;

import com.sample.spring5webapp_v01.repositories.AuthorRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AuthorController {
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @RequestMapping("/authors")
    public String author(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "authors/list";
    }


}
