package dev.fdifrison.jpasecurity.repository;

import dev.fdifrison.jpasecurity.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
