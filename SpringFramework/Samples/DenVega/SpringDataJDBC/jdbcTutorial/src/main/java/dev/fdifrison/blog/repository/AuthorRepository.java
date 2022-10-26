package dev.fdifrison.blog.repository;

import dev.fdifrison.blog.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
