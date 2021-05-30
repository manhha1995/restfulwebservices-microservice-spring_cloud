package com.microservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.microservices.restfulwebservices.repository.entity.User;
import com.microservices.restfulwebservices.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

//    @Value("${user/id}")
//    String id;

    @GetMapping("/user")
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = userService.findOne(id);

        if (user != null) {
            throw new UsernameNotFoundException("-id"+id);
        }

        return  User.builder().build();
    }

    @PostMapping("user/{id}")
    public User deleteUser(@PathVariable int id) {
        User user = userService.deleteById(id);

        if (user != null) {
            throw new UsernameNotFoundException("-id"+id);
        }
        return User.builder().build();
    }

    @PostMapping("/user")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User userSave = userService.saveAll(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/user/{id}")
                .buildAndExpand(userSave.getUserId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
