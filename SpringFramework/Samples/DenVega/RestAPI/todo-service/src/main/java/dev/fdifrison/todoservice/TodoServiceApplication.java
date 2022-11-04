package dev.fdifrison.todoservice;

import dev.fdifrison.todoservice.model.Todo;
import dev.fdifrison.todoservice.repository.TodoRepository;
import dev.fdifrison.todoservice.service.JsonPlaceholderService;
import dev.fdifrison.todoservice.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class TodoServiceApplication {

    private static  final Logger LOG = LoggerFactory.getLogger(TodoServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TodoServiceApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    CommandLineRunner commandLineRunner(JsonPlaceholderService jsonPlaceholderService, TodoRepository todoRepository, TodoService service) {
        return args -> {
            List<Todo> todos = jsonPlaceholderService.getTodos();
            // Persist data to the db
            todoRepository.saveAll(todos);
            LOG.info("Saved {} todos in the database", todos.size());

            //Push information to dashboard
            service.sendToDashboard(todos);
        };


    }

}
