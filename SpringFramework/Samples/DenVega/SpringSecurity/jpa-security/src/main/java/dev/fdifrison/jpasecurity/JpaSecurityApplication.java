package dev.fdifrison.jpasecurity;

import dev.fdifrison.jpasecurity.model.Post;
import dev.fdifrison.jpasecurity.model.User;
import dev.fdifrison.jpasecurity.repository.PostRepository;
import dev.fdifrison.jpasecurity.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaSecurityApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PostRepository postRepository, UserRepository userRepository) {
        return args -> {
            postRepository.save(new Post(
                    "Hello world",
                    "hello-world",
                    "Welcome to my blog",
                    "fdifrison"));
            userRepository.save(new User("user", "password", "ROLE_USER"));
            userRepository.save(new User("admin", "password", "ROLE_USER,ROLE_ADMIN"));
        };
    }

}
