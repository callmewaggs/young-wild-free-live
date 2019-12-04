package com.github.callmewaggs.youngwildfreelive.service;

import com.github.callmewaggs.youngwildfreelive.model.Room;
import com.github.callmewaggs.youngwildfreelive.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public void createRoom(Room room) {
        roomRepository.save(room);
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
