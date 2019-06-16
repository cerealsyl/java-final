package com.example.finaljava.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String role;
	private String email;

	@OneToMany(mappedBy = "writer")
	@JsonIgnore
	private List<Story> storyList;

	@ManyToMany
	@JsonIgnore
	@JoinTable(name="SUBSCRIPTION",
	joinColumns = @JoinColumn(name="USER_ID"),
	inverseJoinColumns = @JoinColumn(name="BOOK_ID"))
	private List<Book> bookList;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}


	public User(Long id, String username, String password, String firstName, String lastName,
							String role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.storyList = storyList;
		this.bookList = bookList;
	}

	public User() {
		super();
	}


	public List<Story> getStoryList() {
		return storyList;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setStoryList(List<Story> shortStoryList) {
		this.storyList = shortStoryList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
