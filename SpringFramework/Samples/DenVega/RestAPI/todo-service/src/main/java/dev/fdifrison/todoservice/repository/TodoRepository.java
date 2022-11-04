package dev.fdifrison.todoservice.repository;

import dev.fdifrison.todoservice.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository  extends CrudRepository<Todo, Integer> {
}
