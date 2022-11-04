package dev.fdifrison.dashboardservice.repository;

import dev.fdifrison.dashboardservice.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Integer> {
}
