package com.example.finaljava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.finaljava.models.User;
import com.example.finaljava.Repositories.*;

import java.util.List;

@RestController
public class UserControllers {
	
	@Autowired
	UserRepository repository;
	
	@GetMapping("/api/users")
	public List<User> findAllUsers() {
		return (List<User>)repository.findAll();
	
	}

	@GetMapping("/api/users/username/{username}")
	public User findUserByUsername(@PathVariable("username") String username) {
		return repository.findUserByUsername(username);

	}
	@GetMapping("/api/users/{userId}")
	public User findUserById(@PathVariable("userId")Long id) {
		return repository.findById(id).get();
        
	}
	
	@GetMapping("/api/users/username/{username}/password/{password}")
    public User findUserByCredentials(
            @PathVariable("username") String username,
            @PathVariable("password") String password) {
        return repository.findUserByCredentials(username, password);
    }

	
	@PostMapping("/api/users")
	public Boolean createUser(@RequestBody User newUser) {
		User user = repository.findUserByUsername(newUser.getUsername());
		if(user != null) {
			return false;
		}else {
			repository.save(newUser);
			return true;
		}
	
	}
		
	
}

