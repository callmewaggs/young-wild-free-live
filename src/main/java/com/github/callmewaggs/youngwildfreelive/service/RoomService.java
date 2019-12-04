package com.github.callmewaggs.youngwildfreelive.service;

import com.github.callmewaggs.youngwildfreelive.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    List<Room> findAll();

    void createRoom(Room room);

    Optional<Room> findRoomById(Long id);

    void updateRoom(Room room);

    void deleteRoomById(Long id);
}
