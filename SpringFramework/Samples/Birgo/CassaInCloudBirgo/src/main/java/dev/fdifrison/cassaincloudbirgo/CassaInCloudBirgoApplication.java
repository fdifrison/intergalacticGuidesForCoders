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

//    Create a new Spring Boot project using Spring Initializr. You can choose the dependencies you need based on your requirements, but at a minimum, you will need the Spring Web, Spring Data JPA and H2 dependencies to create a web application, manipulate data and persist it.
//
//    Create a new Java class that will represent the data you are consuming from the API. This class should have fields that match the data you will be receiving from the API.
//
//    Create a new Java entity class that will represent the data you will be persisting in the database. This class should have fields that match the data you want to persist.
//
//    Create a new interface that will define the methods for consuming the API. You can use the WebClient class from Spring to make HTTP requests to the API.
//
//    Implement the interface created in the previous step in a service class. This service class will handle the logic for making the API request and transforming the response into instances of the Java class you created in step 2.
//
//    Create a new repository interface that extends the JpaRepository interface from Spring Data JPA. This interface will define the methods for persisting and retrieving the data from the database.
//
//    Implement the repository interface created in the previous step in a repository class. This class will handle the logic for persisting and retrieving the data from the database.
//
//    Create a new controller class that will handle requests to your application's API. This class should use the service class from step 5 to retrieve the data from the external API, and use the repository class from step 7 to persist the data in the database.
//
//    Add a dependency to your project for a dashboard library like Chart.js or D3.js.
//
//    In the controller class, use the data retrieved from the API to create the data structures needed by the dashboard library to display the data.
//
//    Create a view for your dashboard that includes the necessary HTML, CSS, and JavaScript to display the dashboard.
//
//    Deploy your application to a server and access it using a web browser to view the dashboard.
//
//    This is just a basic blueprint, and you will likely need to make adjustments based on the specifics of your project. Good luck with your application!

}
