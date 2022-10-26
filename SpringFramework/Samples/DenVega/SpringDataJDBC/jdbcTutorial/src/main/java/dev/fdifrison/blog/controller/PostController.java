package dev.fdifrison.blog.controller;

import dev.fdifrison.blog.model.Author;
import dev.fdifrison.blog.model.Post;
import dev.fdifrison.blog.model.dto.PostDetails;
import dev.fdifrison.blog.repository.AuthorRepository;
import dev.fdifrison.blog.repository.PostRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    public PostController(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public Iterable<Post> findAll() {
        return  postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable Integer id) {
        return postRepository.findById(id).orElse(null);
    }

    @GetMapping("/{id}/details")
    public PostDetails getPostDetails(@PathVariable Integer id) {
        Post post = postRepository.findById(id).orElse(null);
        assert post != null;
        Author author = authorRepository.findById(Objects.requireNonNull(post.getAuthor().getId())).orElse(null);
        return  new PostDetails(post, author);
    }



}
