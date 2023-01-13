package dev.fdifrison.jpasecurity.config;

import dev.fdifrison.jpasecurity.service.JpaUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // we want a jpa user manager; we have a builtin method for jdbc abd in memory user but not for jpa
    private final JpaUserDetailService jpaUserDetailService;

    public SecurityConfig(JpaUserDetailService jpaUserDetailService) {
        this.jpaUserDetailService = jpaUserDetailService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) // we don0t won't to logging to and h2 console
                .authorizeRequests(auth -> auth
                        .mvcMatchers("/api/posts").permitAll()
                        .antMatchers("/h2/**").permitAll()
                        .anyRequest().authenticated())
                .userDetailsService(jpaUserDetailService)
                .headers(headers -> headers.frameOptions().sameOrigin())
                .build();
    }

}
