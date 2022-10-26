package dev.fdifrison.server.repository;

import dev.fdifrison.server.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, Long> {

    Server findByIp(String ip);
}
