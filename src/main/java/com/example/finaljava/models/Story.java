
package com.example.finaljava.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Story {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String story;

  @ManyToOne
  @JsonIgnore
  private User writer;

  public Story(Long storyId, String title, String story, User writer) {
    this.id = storyId;
    this.title = title;
    this.story = story;
    this.writer = writer;
  }

  public Story() {
  }

  public Long getStoryId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getStory() {
    return story;
  }

  public User getWriter() {
    return writer;
  }

  public void setStoryId(Long storyId) {
    this.id = storyId;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setStory(String story) {
    this.story = story;
  }

  public void setWriter(User writer) {
    this.writer = writer;
  }

}