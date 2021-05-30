package com.microservices.restfulwebservices;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableWebSecurity
public class RestfulwebservicesApplication {

    private static final String ROLE_APP = "ADMIN";

    @Value("${spring.datasource.username}")
    String username;

    @Value("${spring.datasource.password}")
    String password;

    public static void main(String[] args) {
        SpringApplication.run(RestfulwebservicesApplication.class, args);
    }

    @Bean
    public WebSecurityConfigurerAdapter webSecurityConfig(DataSource dataSource) {
        return new WebSecurityConfigurerAdapter() {
            @Override
            protected void configure(HttpSecurity http) throws Exception {
                http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/h2-console/**").hasRole(ROLE_APP)
                .antMatchers("*").denyAll()
                .and().httpBasic();
                http.headers().frameOptions().disable(); // login to h2 
            }

            @Override
            protected void configure(AuthenticationManagerBuilder builder) throws Exception {

                PasswordEncoder encoder = passwordEncode();    
                builder.inMemoryAuthentication()
                .withUser(username).password(encoder.encode(password)).roles(ROLE_APP);                     
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncode() throws Exception {
        return new BCryptPasswordEncoder();
    }

}
