package com.fdifrison.streamsschedule.controller;

import com.fdifrison.streamsschedule.exceptions.LiveStreamNotFoundException;
import com.fdifrison.streamsschedule.model.LiveStream;
import com.fdifrison.streamsschedule.repositories.LiveStreamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/streams")
/*
 Following rest principles, we want a uniform resource (livestream in this case) to work with.
Therefore, all the methods related to livestreams should be under /streams
*/
public class LiveStreamController {

    // A controller in MVC pattern are meant to be thin, no data generation, no business logic
    // we use services and repositories for that
    private final LiveStreamRepository repository;

    public LiveStreamController(LiveStreamRepository repository) {
        this.repository = repository;
    }


    // GET http://localhost:8080/streams
    @GetMapping
    public List<LiveStream> findAll() {
        return repository.findAll();
    }

    // GET http://localhost:8080/streams
    @GetMapping("/{id}")
    public LiveStream finById(@PathVariable String id) throws LiveStreamNotFoundException {
        return repository.findById(id);
    }

    // POST http://localhost:8080/streams/{{$uuid}}
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public LiveStream create(@RequestBody LiveStream stream) {
        return  repository.create(stream);
    }

    // PUT http://localhost:8080/streams/{{$uuid}}
    @ResponseStatus(HttpStatus.NO_CONTENT) // don't expect anything back
    @PutMapping("/{id}")
    public void update(@RequestBody LiveStream stream, @PathVariable String id) {
        repository.update(stream, id);
    }

    // DELETE http://localhost:8080/streams/{{$uuid}}
    @ResponseStatus(HttpStatus.NO_CONTENT) // don't expect anything back
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repository.delete(id);
    }


}
