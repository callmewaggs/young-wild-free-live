package com.github.callmewaggs.youngwildfreelive.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Room {
    long id;
    String roomName;
    String hostName;
    int nowWatching;
    String thumbnail;
    String shortURL;
    String category;
    String resolution;
    String createdAt;

    public Room(long id, String roomName, String hostName, int nowWatching, String thumbnail, String category, String resolution, String createdAt) {
        String newShortURL = "/" + hostName + "/" + id;

        this.id = id;
        this.roomName = roomName;
        this.hostName = hostName;
        this.nowWatching = nowWatching;
        this.thumbnail = thumbnail;
        this.shortURL = newShortURL;
        this.category = category;
        this.resolution = resolution;
        this.createdAt = createdAt;
    }
}
