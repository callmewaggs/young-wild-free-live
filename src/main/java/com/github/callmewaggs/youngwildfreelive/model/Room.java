package com.github.callmewaggs.youngwildfreelive.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String roomName;
    String hostName;
    int nowWatching;
    String thumbnailURL;
    @Enumerated(EnumType.STRING)
    Category category;
    String resolution;
    LocalDateTime createdAt;

    private Room() {

    }

    public Room(String roomName, String hostName, Category category, LocalDateTime createdAt) {
        this.roomName = roomName;
        this.hostName = hostName;
        this.category = category;
        this.createdAt = createdAt;
    }

    public String getShortURL() {
        return "/" + hostName + "/" + id;
    }
}
