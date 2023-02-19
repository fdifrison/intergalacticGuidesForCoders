package dev.fdifrison.cassaincloudbirgo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.JsonNode;
import dev.fdifrison.cassaincloudbirgo.model.Token;
import dev.fdifrison.cassaincloudbirgo.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class CassaInCloudBirgoApplication extends SpringBootServletInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(CassaInCloudBirgoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CassaInCloudBirgoApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    CommandLineRunner runner(LoginService login) {
        return args -> {
            Token token = login.auth();
            LOG.info("Access token: " + token);
            login.getRestaurants(token.getAccess_token());
//            LOG.info("Expires in " + token.getExpiresIn());
        };
    }

}
