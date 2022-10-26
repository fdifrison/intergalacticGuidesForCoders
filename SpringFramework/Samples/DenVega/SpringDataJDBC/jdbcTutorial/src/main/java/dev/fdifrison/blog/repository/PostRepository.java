package dev.fdifrison.blog.repository;

import dev.fdifrison.blog.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
}
