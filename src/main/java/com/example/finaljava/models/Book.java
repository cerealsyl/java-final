package com.example.finaljava.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Book {


  private String title;

  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToMany(mappedBy = "bookList")
  @JsonIgnore
  List<User> subscribers;

  public Book(String title, Long bookId, List<User> subscribers) {
    this.title = title;
    this.id = bookId;
    this.subscribers = subscribers;
  }


  public Book() {
  }

  public String getTitle() {
    return title;
  }

  public Long getBookId() {
    return id;
  }

  public List<User> getSubscribers() {
    return subscribers;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setBookId(Long bookId) {
    this.id = bookId;
  }

  public void setSubscribers(List<User> subscribers) {
    this.subscribers = subscribers;
  }
  
 
}
