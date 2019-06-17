package com.example.finaljava.controllers;

import com.example.finaljava.Repositories.StoryRepository;
import com.example.finaljava.Repositories.UserRepository;
import com.example.finaljava.models.Story;
import com.example.finaljava.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class StoryListControllers {

  @Autowired
  StoryRepository storyRepository;

  @Autowired
  UserRepository userRepository;

  @GetMapping("/api/stories")
  public List<Story> findAllStories() {
    return (List<Story>)storyRepository.findAll();
  }


  @GetMapping("/api/users/{userId}/stories")
  public List<Story> findStoriesByUserId(@PathVariable("userId")Long userId) {
    

    User user = userRepository.findUserById(userId);
    if(user.getRole().equals("VIEWER")){
      return null;
    }else{
      return storyRepository.findStoriesByUserId(userId);
    }


  }

  @PostMapping("/api/users/{userId}/stories")
  public ResponseEntity<List<Story>> createStory(
          @PathVariable("userId")Long userId,
          @RequestBody Story newStory) {
    User user  = userRepository.findUserById(userId);
    if(user == null || user.getRole().equals("VIEWER")|| newStory == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    newStory.setWriter(user);
    storyRepository.save(newStory);
    List<Story> stories = findStoriesByUserId(userId);
    return ResponseEntity.status(HttpStatus.OK).body(stories);

  }

  @PutMapping("/api/stories/{storyId}")
  public List<Story> updateStoryById(
          @PathVariable("storyId")Long storyId,
          @RequestBody Story newStory){
    Story story = storyRepository.findById(storyId).get();
    if(newStory == null) {
      return null;
    }
    story.setStory(newStory.getStory());
    story.setTitle(newStory.getTitle());
    storyRepository.save(story);
    return storyRepository.findStoriesByUserId(story.getWriter().getId());
  }

  @DeleteMapping("/api/users/{userId}/stories/{storyId}")
  public List<Story> deleteStoryById(
          @PathVariable("userId")Long userId,
          @PathVariable("storyId")Long storyId){
    User user = userRepository.findUserById(userId);
    Story story = storyRepository.findById(storyId).get();
    user.getStoryList().remove(story);
    userRepository.save(user);
    storyRepository.delete(story);
    return user.getStoryList();
  }

  @PostMapping("/api/stories")
  public List<Story> searchStoryBasedOnKeyword(@RequestBody String word) {
    String search = word.replace("\"", "");
    return storyRepository.findStoriesThatContainsSearchWord(search);

  }



  @GetMapping("/api/stories/{storyId}")
  public ResponseEntity<Story> findStoryById(@PathVariable("storyId")Long storyId) {
    Story story = storyRepository.findStoryById(storyId);
    if(story == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }else{
      return ResponseEntity.status(HttpStatus.OK).body(story);
    }


  }



}
