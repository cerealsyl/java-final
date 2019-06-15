package com.example.finaljava.controllers;

import com.example.finaljava.Repositories.BookRepository;
import com.example.finaljava.Repositories.UserRepository;
import com.example.finaljava.models.Book;
import com.example.finaljava.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class BookListControllers {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/api/users/{userId}/books")
	public ResponseEntity<?> addBookToList(
			@PathVariable("userId")Long userId,
			@RequestBody Book newBook) {
		User user = userRepository.findUserById(userId);
		Book book = bookRepository.findBookById(newBook.getBookId());
		if(book == null) {
			List<User> emptyList = new ArrayList<>();
			newBook.setSubscribers(emptyList);
		}
		if(user != null) {
			newBook.getSubscribers().add(user);
			bookRepository.save(newBook);
			user.getBookList().add(newBook);
			userRepository.save(user);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	    
	}
	  
	@GetMapping("/api/users/{userId}/books")
	public List<Book> findBooksByUserId(@PathVariable("userId")Long userId){
		 return bookRepository.findAllByUserId(userId);	  
	}


	@DeleteMapping("/api/users/{userId}/books/{bookId}")
	public List<Book> deleteBookById(
					@PathVariable("userId")Long userId,
					@PathVariable("bookId")Long bookId){
		User user = userRepository.findUserById(userId);
		Book book = bookRepository.findBookById(bookId);
		if(user == null || book == null) {
			return null;
		}
		user.getBookList().remove(book);
		book.getSubscribers().remove(user);
		bookRepository.save(book);
		userRepository.save(user);
		return user.getBookList();
	}

	@GetMapping("/api/books/{bookId}/users")
	public List<User> findAllUsersByBookId(@PathVariable("bookId")Long bookId){
		return userRepository.findAllUsersByBookId(bookId);
	}
	

}
