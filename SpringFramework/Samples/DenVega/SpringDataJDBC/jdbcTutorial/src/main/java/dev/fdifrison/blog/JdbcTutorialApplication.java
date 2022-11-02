package dev.fdifrison.blog;

import dev.fdifrison.blog.model.Author;
import dev.fdifrison.blog.model.Comment;
import dev.fdifrison.blog.model.Post;
import dev.fdifrison.blog.repository.AuthorRepository;
import dev.fdifrison.blog.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

@SpringBootApplication
public class JdbcTutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdbcTutorialApplication.class, args);
    }

    /* We have two kind of aggregation in jdbc, one between post and author (on the same level) and one within
     post and comment (comment is a child of post) */

//    @Bean
//    CommandLineRunner commandLineRunner(PostRepository posts, AuthorRepository authors) {
//        return args -> {
//            // we want to retrieve the aggregate object to pass it to the post object.
//            AggregateReference<Author, Integer> myId = AggregateReference.to(authors.save(new
//                    Author(null, // we need to pass null or jdbc will think it is an update
//                    "Giovanni",
//                    "Frison",
//                    "giovanni.frison@alten.it",
//                    "fdifrison")
//            ).id());
//            Post post = new Post("My first Post", "Welcome to my blog", myId);
//            post.addComment(new Comment("Comment","This is a comment"));
//            posts.save(post);
//        };
//    }

}
