package dev.fdifrison.server;

import dev.fdifrison.server.enumeration.Status;
import dev.fdifrison.server.model.Server;
import dev.fdifrison.server.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class ServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ServerRepository repository) {
        return args -> {
            repository.save(
                    new Server(
                            null,
                            "192.168.1.160",
                            "Ubuntu Linux",
                            "16 GB",
                            "Personal PC",
                            "http://localhost:8080/server/image/server1.png",
                            Status.SERVER_UP));
            repository.save(
                    new Server(
                            null,
                            "192.168.1.165",
                            "Windows Server",
                            "32 GB",
                            "Personal PC",
                            "http://localhost:8080/server/image/server3.png",
                            Status.SERVER_DOWN));
            repository.save(
                    new Server(
                            null,
                            "192.168.1.180",
                            "Fedora",
                            "64 GB",
                            "Cineca Server",
                            "http://localhost:8080/server/image/server2.png",
                            Status.SERVER_UP));
            repository.save(
                    new Server(
                            null,
                            "192.168.10.147",
                            "Studio Piscaglia Wifi",
                            "- GB",
                            "Wifi connection",
                            "http://localhost:8080/server/image/server2.png",
                            Status.SERVER_UP));
        };
    }

    // the following bean is to allow interaction in the browser by two services (backend and frontend application)
    // and avoid a CORS (Cross-Origin Resource Sharing) error

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList(
                "http://localhost:3000", // react.js default port
                "http://localhost:4200" // angular default port
        ));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type",
                "Accept", "Jwt-Token", "Authorization", "Access-Control-Allow-Origin",
                "Access-Control-Allow-Credentials", "Filename"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }


}
