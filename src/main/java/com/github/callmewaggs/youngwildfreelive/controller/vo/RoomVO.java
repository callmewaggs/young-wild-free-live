package com.github.callmewaggs.youngwildfreelive.controller.vo;

import com.github.callmewaggs.youngwildfreelive.model.Category;
import com.github.callmewaggs.youngwildfreelive.model.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomVO {
    long id;
    String roomname;
    String hostname;
    int nowwatching;
    String thumbnailURL;
    Category category;
    String resolution;
    LocalDateTime createdAt;

    public RoomVO(Room room) {
        this.id = room.getId();
        this.roomname = room.getRoomname();
        this.hostname = room.getHostname();
        this.nowwatching = room.getNowwatching();
        this.thumbnailURL = room.getThumbnailURL();
        this.category = room.getCategory();
        this.resolution = room.getResolution();
        this.createdAt = room.getCreatedAt();
    }
}
