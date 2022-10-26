package dev.fdifrison.server.model;

import dev.fdifrison.server.enumeration.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true) // create a constraint on the ip address so that it will be unique
    @NotEmpty(message = "IP address cannot be empty or null")
    private String ip;
    private String name;
    private String memory;
    private String type;
    private String imageUrl;
    private Status status;

    public Server() {
    }

    public Server(Long id, String ip, String name, String memory, String type, String imageUrl, Status status) {
        this.id = id;
        this.ip = ip;
        this.name = name;
        this.memory = memory;
        this.type = type;
        this.imageUrl = imageUrl;
        this.status = status;
    }


    @Override
    public String toString() {
        return "Server{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", name='" + name + '\'' +
                ", memory='" + memory + '\'' +
                ", type='" + type + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", status=" + status +
                '}';
    }
}
