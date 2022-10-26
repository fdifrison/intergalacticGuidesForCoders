package dev.fdifrison.depinjection.controller;

import dev.fdifrison.depinjection.model.Article;
import dev.fdifrison.depinjection.repository.ArticleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleRepository articles;

    /*
     Here dependency injection by constructor is fundamental for testability. Imagine the repository is an actual
     connection to a db. To test it we need to mock it or otherwise our unit test will require the dependency of a db
     connection... unacceptable
    */
    public ArticleController(ArticleRepository articles) {
        this.articles = articles;
    }

    @GetMapping
    public Iterable<Article> findAll() {
        return  articles.findAll();
    }

    @GetMapping("/{id}")
    public Article findById(@PathVariable Integer id) {
        return articles.findById(id);
    }
}
