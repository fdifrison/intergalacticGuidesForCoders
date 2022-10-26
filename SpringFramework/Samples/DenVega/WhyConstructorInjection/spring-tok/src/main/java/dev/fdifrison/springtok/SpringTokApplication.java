package dev.fdifrison.springtok;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class SpringTokApplication {

    public static void main(String[] args) {

//        var repository = new VideoRepository();
//        var controller = new VideoController(repository);
//        controller.start();
//
//        SpringApplication.run(SpringTokApplication.class, args);

        ConfigurableApplicationContext app = SpringApplication.run(SpringTokApplication.class, args);
        /*
         like this we can manage the application context, what is managing all the instantiation and configuration
         and dependency injection of all the beans. This means that we can get out this kind of information
        */
        Arrays.stream(app.getBeanDefinitionNames()).forEach(System.out::println);
        /*
        print all the beans that the application context is managing for us.
        We have different type of annotations that can tell to the spring context that our object is a bean. At class
        level we have @Component and all its descendant (@Service, @Controller etc..) while at method level we have the
        @Bean annotation (e.g. for a class that return a java object).
        */


    }


    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("##################################");
            System.out.println("Hello from the command line runner");
            System.out.println("##################################");
        };
    }

}
