package com.github.callmewaggs.youngwildfreelive.controller.vo;

import com.github.callmewaggs.youngwildfreelive.model.Category;
import com.github.callmewaggs.youngwildfreelive.model.Room;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomVO {
    long id;
  String roomName;
  String hostName;
  int nowWatching;
    String thumbnailURL;
    Category category;
    String resolution;
    LocalDateTime createdAt;

    public RoomVO(Room room) {
        this.id = room.getId();
      this.roomName = room.getRoomName();
      this.hostName = room.getHost().getNickname();
      this.nowWatching = room.getNowWatching();
        this.thumbnailURL = room.getThumbnailURL();
        this.category = room.getCategory();
        this.resolution = room.getResolution();
        this.createdAt = room.getCreatedAt();
    }
}
