package com.example.finaljava.Repositories;


import com.example.finaljava.models.Story;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoryRepository extends CrudRepository<Story, Long>{

  @Query("select s FROM Story s WHERE s.writer.id=:writer_id")
  public List<Story> findStoriesByUserId(@Param("writer_id")Long userId);

  // HOW TO QUERY FOR SIMILAR NAMES
  // like if I type in harry, any story whose title contains harry should be return
  @Query(value = "select * from story where title like %search%", nativeQuery = true)
  public List<Story> findStoriesThatContainsSearchWord(@Param("search")String search);




}
