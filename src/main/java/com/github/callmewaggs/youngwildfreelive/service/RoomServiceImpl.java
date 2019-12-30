package com.github.callmewaggs.youngwildfreelive.service;

import com.github.callmewaggs.youngwildfreelive.model.Category;
import com.github.callmewaggs.youngwildfreelive.model.Room;
import com.github.callmewaggs.youngwildfreelive.model.User;
import com.github.callmewaggs.youngwildfreelive.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;
    private UserService userService;

    public RoomServiceImpl(RoomRepository roomRepository, UserService userService) {
        this.roomRepository = roomRepository;
        this.userService = userService;
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    // TODO : 코드 정리!!!!!!
    @Override
    public Room createRoom(String username, String roomname, Category category) {
        Optional<User> user = userService.findUserByUsername(username);
        if (!user.isPresent()) {
            throw new IllegalStateException("가입하지 않은 사용자입니다.");
        }

        Room room = new Room(roomname, user.get().getNickname(), category, LocalDateTime.now(), new ArrayList<>());
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
