package dev.fdifrison.server.service;

import dev.fdifrison.server.model.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerService {
    // contains all the functionality that the Server application should have
    Server create(Server server);

    Server ping(String ip) throws IOException; // return the server we are trying to ping

    Collection<Server> findAll(int limit); // limit the number of rows to return

    Server get(Long id);

    Server update(Server server);

    Boolean delete(Long id);

}
