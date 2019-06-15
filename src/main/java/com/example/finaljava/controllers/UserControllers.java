package com.example.finaljava.controllers;

import com.example.finaljava.Repositories.UserRepository;
import com.example.finaljava.models.LoginRequest;
import com.example.finaljava.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserControllers {
	
	@Autowired
	UserRepository repository;
	
	@GetMapping("/api/users")
	public List<User> findAllUsers() {
		return (List<User>)repository.findAll();
	
	}

	@GetMapping("/api/auth/{username}")
	public User findUserByUsername(@PathVariable("username") String username) {
		return repository.findUserByUsername(username);

	}
	@GetMapping("/api/users/{userId}")
	public User findUserById(@PathVariable("userId")Long id) {
		return repository.findById(id).get();
        
	}
	
	
	@PutMapping("/api/users/{userId}")
	public User updateUserInfo(
			@PathVariable("userId")Long userId,
			@RequestBody User newUser) {
		User user = repository.findById(userId).get();
		user.setFirstName(newUser.getFirstName());
		user.setLastName(newUser.getLastName());
		user.setPassword(newUser.getPassword());
		user.setUsername(newUser.getUsername());
		user.setEmail(newUser.getEmail());
		repository.save(user);
		return user;
	}

	
	@PostMapping("/api/users")
	public ResponseEntity<?> createUser(@RequestBody User newUser) {
		User user = repository.findUserByUsername(newUser.getUsername());


		if (user != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			repository.save(newUser);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}

	@PostMapping("/api/login")
	public ResponseEntity<User> validateUser(@RequestBody LoginRequest loginRequest) {

		User user = repository.findUserByCredentials(loginRequest.getUsername(), loginRequest.getPassword());

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(user);
		}
	}

	
}

