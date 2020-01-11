package com.github.callmewaggs.youngwildfreelive.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Room {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String roomName;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private User host;

  private String thumbnailURL;

  @Enumerated(EnumType.STRING)
  private Category category;

  private String resolution;

  private LocalDateTime createdAt;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "room_user",
      joinColumns = @JoinColumn(name = "room_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> watchingUsers;

  public Room() {

  }

  public Room(String roomName, User host, Category category, LocalDateTime createdAt,
      List<User> watchingUsers) {
    this.roomName = roomName;
    this.host = host;
    this.category = category;
    this.createdAt = createdAt;
    this.watchingUsers = watchingUsers;
  }

  public String getShortURL() {
    return "/" + id;
  }

  public List<User> watchingUsers() {
    return this.watchingUsers;
  }

  public int getNowWatching() {
    return this.watchingUsers.size();
  }

  public void join(User user) {
    this.watchingUsers.add(user);
  }
}
