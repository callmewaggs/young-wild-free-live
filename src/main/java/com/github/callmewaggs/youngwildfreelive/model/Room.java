package com.github.callmewaggs.youngwildfreelive.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String roomname;

    // TODO : User 를 들고 있도록
    private String hostname;

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

    public Room(String roomname, String hostname, Category category, LocalDateTime createdAt, List<User> watchingUsers) {
        this.roomname = roomname;
        this.hostname = hostname;
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
