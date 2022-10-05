package com.sample.spring5webapp_v01.repositories;

import com.sample.spring5webapp_v01.models.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
