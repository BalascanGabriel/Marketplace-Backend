package com.example.demo.controllers.access;


import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDto;
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
		Optional<User> oldUser = userService.findByUsername(user.getUsername());
		if(oldUser.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		if(user.getPassword().length() < 3) {
			return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
		}
        User newUser = userService.register(user);
        return ResponseEntity.ok(newUser);
    }
	
	  @PostMapping("/login")
	    public ResponseEntity<LoginDto> login(@RequestBody User user) {
	        String token = userService.authenticate(user.getUsername(), user.getPassword());
	        LoginDto dto = new LoginDto();
	        if (token != null) {
	        	dto.setToken(token);
	        	dto.setUsername(user.getUsername());
	            return ResponseEntity.ok(dto);
	        } 
	        return ResponseEntity.badRequest().build();
	        
	    }
}
