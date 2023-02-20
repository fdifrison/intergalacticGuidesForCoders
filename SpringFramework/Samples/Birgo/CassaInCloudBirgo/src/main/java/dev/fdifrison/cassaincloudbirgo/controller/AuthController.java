package dev.fdifrison.cassaincloudbirgo.controller;

import dev.fdifrison.cassaincloudbirgo.model.Token;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RestController
@RequestMapping("https://api.cassanova.com")
public class AuthController {

    private final RestTemplate restTemplate;

    public AuthController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping(value = "/apikey/token", consumes = {"application/json"})
    public Token getToken(@RequestHeader("X-Requested-With:*")
                              @RequestParam(value = "apiKey") String apiKey) {
        final String uri = MvcUriComponentsBuilder.
        return Token;
    }


}
