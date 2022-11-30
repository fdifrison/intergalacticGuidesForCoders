package dev.fdifrison.newsletter.functions;

import dev.fdifrison.newsletter.model.Subscriber;
import dev.fdifrison.newsletter.service.SubscriberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
public class Subscribers {

    private static final Logger LOG = LoggerFactory.getLogger(Subscribers.class);
    private final SubscriberService subscribers;

    public Subscribers(SubscriberService subscribers) {
        this.subscribers = subscribers;
    }

    /*
     Spring cloud will automatically create under the hood the endpoint with the functions name:
     - http://localhost:8080/create
     - http://localhost:8080/findAll
     We could potentially create all the crud rest api inside here
     Once the shadowed jar is uploaded to aws lambda, and once the access url has been created, we can access the API
     e.g. via postman with the headers:
     - key = spring.cloud.function.definition
     - value = <functionName>
    */

    @Bean
    public Supplier<List<Subscriber>> findAll() {
        LOG.info("Subscriber.findAll() was called");
        return subscribers::findAll;
    }

    @Bean
    public Consumer<String> create() {
        return subscribers::create;
    }

}
