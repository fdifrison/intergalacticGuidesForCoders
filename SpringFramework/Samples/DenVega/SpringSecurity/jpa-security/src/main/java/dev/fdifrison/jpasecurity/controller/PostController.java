package dev.fdifrison.jpasecurity.controller;

import dev.fdifrison.jpasecurity.model.Post;
import dev.fdifrison.jpasecurity.repository.PostRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository postRepository;


    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public  Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable("id") Post post) {
        /*
         using PathVariable annotation we are applying a "domain class converter"
         meaning that we are leaving to the crud repository the burden to do the findById operation
        */
        return post;
    }
}
