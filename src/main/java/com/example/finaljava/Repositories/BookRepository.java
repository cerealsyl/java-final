package com.example.finaljava.Repositories;

import com.example.finaljava.models.Book;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BookRepository extends CrudRepository<Book, Long> {

//  @Query(value = "select * from subscription where book_id=:book_id", nativeQuery = true)
//  public User findAllUserByBookId(@Param("book_id")Long bid);
//  
  @Query("select b FROM Book b JOIN b.subscribers u WHERE u.id = :user_id")
  public List<Book> findAllByUserId(@Param("user_id")Long userId);

  @Query("select b FROM Book b WHERE b.id = :b_id")
  public Book findBookById(@Param("b_id")Long bookId);

<<<<<<< HEAD
  @Query("select b FROM Book b WHERE b.title = :b_title")
  public Book findBookByTitle(@Param("b_title")String title);

=======
>>>>>>> 8904f1fe176814a68c993ff87afef88fb362d90a
  // METHOD 1: findAllBooksByUserId

  //METHOD 2: findAllUsersByBookId



}
