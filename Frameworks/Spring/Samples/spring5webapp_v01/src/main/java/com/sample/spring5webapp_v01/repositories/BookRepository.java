package com.sample.spring5webapp_v01.repositories;

import com.sample.spring5webapp_v01.models.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
