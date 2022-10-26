package dev.fdifrison.depinjection.repository;

import dev.fdifrison.depinjection.model.Article;
import dev.fdifrison.depinjection.service.SlugService;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ArticleRepository {

    List<Article> articles;


    public ArticleRepository(SlugService slugService) {
        articles = List.of(
                new Article(1, "First Article", slugService.slugify("First Article"), "This is the first article", LocalDateTime.now()),
                new Article(1, "Second Article", slugService.slugify("Second Article"), "This is the second article", LocalDateTime.now()),
                new Article(1, "Third Article", slugService.slugify("Third Article"), "This is the third article", LocalDateTime.now())
        );
    }

    /*
    Pro-way to create the articles and slugify the title using a Map
        public ArticleRepository() {
            this.slugService = new SimpleSlugService();
            Map<Integer, String> articles = Map.of(
                    1, "Hello, World!",
                    2, "Spring Initializr",
                    3, "Spring Dependency Injection"
            );
            for (Map.Entry<Integer, String> entry : articles.entrySet()) {
                this.articles.add(new Article(
                        entry.getKey(),
                        entry.getValue(),
                        slugService.slugify(entry.getValue()),
                        "TEST CONTENT",
                        LocalDateTime.now()
                ));
            }
        }
    */
    @GetMapping
    public List<Article> findAll() {
        return articles;
    }

    @GetMapping("/{id}")
    public Article findById(@PathVariable Integer id) {
        return articles.stream().filter(article -> article.id().equals(id)).findFirst().orElse(null);
    }

}
