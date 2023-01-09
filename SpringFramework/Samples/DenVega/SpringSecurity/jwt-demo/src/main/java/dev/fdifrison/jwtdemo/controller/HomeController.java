package dev.fdifrison.jwtdemo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {

    @GetMapping
    public String home(Principal principal, Authentication authentication) {
        System.out.println(authentication.getAuthorities().stream().findFirst().get().getAuthority());
        return "Hello " + principal.getName() + ", welcome to the JWT-demo in Spring Boot \n" ;

    }
}
