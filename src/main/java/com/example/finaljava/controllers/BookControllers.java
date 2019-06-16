package com.example.finaljava.controllers;


import com.example.finaljava.Repositories.BookRepository;
import com.example.finaljava.models.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class BookControllers {
	@Autowired
	BookRepository bookRepo;
	
	

  
  @GetMapping("/api/books")
  public List<Book> findAllBooks(){
	  return (List<Book>)bookRepo.findAll();
  }
  
  @PostMapping("/api/books")
  public Book createBook(@RequestBody Book newBook) {
	  return bookRepo.save(newBook);
  }



}
