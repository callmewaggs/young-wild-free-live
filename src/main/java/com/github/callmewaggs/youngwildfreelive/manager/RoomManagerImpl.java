package com.github.callmewaggs.youngwildfreelive.manager;

import com.github.callmewaggs.youngwildfreelive.model.Category;
import com.github.callmewaggs.youngwildfreelive.model.Room;
import com.github.callmewaggs.youngwildfreelive.model.User;
import com.github.callmewaggs.youngwildfreelive.repository.RoomRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class RoomManagerImpl implements RoomManager {

  private RoomRepository roomRepository;
  private UserManager userManager;

  public RoomManagerImpl(RoomRepository roomRepository, UserManager userManager) {
    this.roomRepository = roomRepository;
    this.userManager = userManager;
  }

  @Override
  public List<Room> findAll() {
    return roomRepository.findAll();
  }

  @Override
  public Room createRoom(String roomName, User user, Category category) {
    Room room = new Room(roomName, user, category, LocalDateTime.now(), new ArrayList<>());
    return roomRepository.save(room);
  }

  @Override
  public Optional<Room> findRoomById(Long id) {
    try {
      return roomRepository.findRoomById(id);
    } catch (IllegalArgumentException e) {
      return Optional.empty();
    }
  }

  @Override
  public void updateRoom(Room room) {

  }

  @Override
  public void deleteRoomById(Long id) {

  }
}
