package dev.fdifrison.securityoldway.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// The non deprecated approach is to use a securityFilterChain

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    // something we won't use in production,  just for testing we'll create a mock user
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user  = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        UserDetails admin  = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                // using lambda dsl approach to write more readable code and get rid of the and()
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(auth -> {
                            auth.antMatchers("/").permitAll();
                            auth.antMatchers("/user").hasRole("USER");
                            auth.antMatchers("/admin").hasRole("ADMIN");
                        }
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}


//////////////////////////////////////////////////////////////////////////
// THE OLD WAY TO DO IT, prior spring 2.7 and spring security 5.x
//////////////////////////////////////////////////////////////////////////

//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll() //anyone that gets to the root "/" is allowed to do anything
//                .antMatchers("/user").hasRole("USER")
//                .anyRequest().authenticated() // all the other request must be authenticated
//                .and()
//                .httpBasic();
//    }
//}
