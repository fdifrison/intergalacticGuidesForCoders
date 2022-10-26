package dev.fdifrison.server.service.implementation;

import dev.fdifrison.server.enumeration.Status;
import dev.fdifrison.server.model.Server;
import dev.fdifrison.server.repository.ServerRepository;
import dev.fdifrison.server.service.ServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;

@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;

    public ServerServiceImpl(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @Override
    public Server create(Server server) {
        log.info("Saving new server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepository.save(server);
    }

    private String setServerImageUrl() {
        // TODO implement
        return null;
    }

    @Override
    public Server ping(String ip) throws IOException {
        log.info("Pinging server with ip: {}", ip);
        Server server = serverRepository.findByIp(ip);
        InetAddress address = InetAddress.getByName(ip); // class to perform hostname resolution, check if the server is alive
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        log.info("The server is {}", server.getStatus());
        serverRepository.save(server);
        return server;
    }

    @Override
    public Collection<Server> findAll(int limit) {
        log.info("Listing up to {} servers", limit );
        Collection<Server> servers = serverRepository.findAll(PageRequest.of(0, limit)).toList();
        log.info("{} servers where found", servers.size());
        return servers;
    }

    @Override
    public Server get(Long id) {
        log.info("Looking for server with id {}", id);
        return serverRepository.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        serverRepository.deleteById(id);
        return serverRepository.findById(id).isPresent();
    }
}
