package dev.fdifrison.consumerest;

import dev.fdifrison.consumerest.domain.Quote;
import dev.fdifrison.consumerest.domain.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;


@SpringBootApplication
public class ConsumeRestApplication {

    private static final Logger log = LoggerFactory.getLogger(ConsumeRestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ConsumeRestApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            // Quote quote = restTemplate.getForObject("https://quoters.apps.pcfone.io/api/random", Quote.class);
            // Hard coded since the server is down
            Quote quote = new Quote("success", new Value(10L, "Really loving Spring Boot, makes stand alone Spring apps easy."));
            log.info(Objects.requireNonNull(quote).toString());
        };
    }

}
