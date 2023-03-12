package com.example.demo.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/rest/users")
@CrossOrigin(value = {"http://localhost:4200/"})
public class RestControllerUser {
	
	 private final UserService userService;

	 public RestControllerUser(UserService userService) {
	        this.userService = userService;
	    }

	 @GetMapping("/all")
	 public ResponseEntity<Iterable<User>> getUsers() {
	     Iterable<User> users = userService.findAll();
	     return ResponseEntity.ok(users);
	 }
	 
	@PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User newUser = userService.register(user);
        return ResponseEntity.ok(newUser);
    }
	
	  @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
	        boolean authenticated = userService.authenticate(email, password);
	        if (authenticated) {
	            return ResponseEntity.ok("User authenticated");
	        } else {
	            return ResponseEntity.badRequest().body("Invalid email or password");
	        }
	    }
}
