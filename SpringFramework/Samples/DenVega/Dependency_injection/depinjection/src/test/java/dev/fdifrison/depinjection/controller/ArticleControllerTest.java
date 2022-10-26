package dev.fdifrison.depinjection.controller;

import dev.fdifrison.depinjection.model.Article;
import dev.fdifrison.depinjection.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@WebMvcTest(ArticleController.class)
// Loads only the part of the controller class that are needed to mock it and perform the tests
class ArticleControllerTest {

    ArticleController controller;

    @MockBean
    ArticleRepository repository;
    /*
     by mocking the repository we avoid building a heavy part of the application but, we also lose the data that we
     have initialize to simulate a db storing data; therefore we need to recreate those data
    */

    List<Article> articles = new ArrayList<>();

    @BeforeEach
    void setUp() {
        controller = new ArticleController(repository);
        articles = List.of(new Article(1, "First Article", "first-article", "This is the first article", LocalDateTime.now()));
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(articles);
        // when repository.findAll() is called, return the list of article we created above
        assertEquals(1, controller.findAll().spliterator().getExactSizeIfKnown());
    }

    @Test
    void findById() {
        when(repository.findById(1)).thenReturn(articles.get(0));
        Article article = controller.findById(1);
        assertNotNull(article);
    }
}