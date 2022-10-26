package dev.fdifrison.springtok.controller;

import dev.fdifrison.springtok.repository.VideoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {

    /*
     The controller id dependent on the repository since it is where from it will fetch all the videos, but we want
     spring to be responsible to inject the dependency for us -> IoC Inversion of Control through Dependency Injection
    */


    /*
     we could add @Autowire directly here and avoid the constructor injection at all (this is field injection); it is
     also against the definition of java "private" variable. Spring use reflection to perform the initialization of
    the bean. Again, repository could be null, and we don't have a clue, and last but not least, testability is reduced:
    we should use reflection our self to mock an instance of the controller (something we don't want to do!).
    The only time when it is OK to use field injection is during test
    */
    private final VideoRepository repository;

    /*
     @Autowire is redundant if there is only a single constructor, moreover, with autowire we can't use the final
     keyword in the statement above which will let us be able to use setters method on the repository, something not
     recommended.
    */
    public VideoController(VideoRepository repository) {
        this.repository = repository;
//        this.repository = new VideoRepository(); NO! we don't want to manage the dependency, let spring do it!
    }

    public void start() {
        System.out.println("Video is starting!");
    }
}
