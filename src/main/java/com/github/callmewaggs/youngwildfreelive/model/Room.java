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
    String roomname;
    String hostname;
    int nowwatching;
    String thumbnailURL;
    @Enumerated(EnumType.STRING)
    Category category;
    String resolution;
    LocalDateTime createdAt;

    public Room() {

    }

    public Room(String roomname, String hostname, Category category, LocalDateTime createdAt) {
        this.roomname = roomname;
        this.hostname = hostname;
        this.category = category;
        this.createdAt = createdAt;
    }

    public String getShortURL() {
        return "/" + id;
    }
}
