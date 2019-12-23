package com.github.callmewaggs.youngwildfreelive.service;

import com.github.callmewaggs.youngwildfreelive.model.Category;
import com.github.callmewaggs.youngwildfreelive.model.Room;
import com.github.callmewaggs.youngwildfreelive.repository.RoomRepository;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RoomServiceImplTest {
    @DisplayName("RoomService는 방이 없는 경우 빈 방 목록을 리턴한다.")
    @Test
    public void return_room_list_when_empty_room() {
        // Arrange
        RoomRepository roomRepository = mock(RoomRepository.class);
        RoomService roomService = new RoomServiceImpl(roomRepository);
        when(roomRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Room> actual = roomService.findAll();

        // Assert
        List<Room> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @DisplayName("RoomService는 방이 있는 경우 방 목록을 리턴한다.")
    @Test
    public void return_room_list_when_room_exist() {
        // Arrange
        RoomRepository roomRepository = mock(RoomRepository.class);
        RoomService roomService = new RoomServiceImpl(roomRepository);
        List<Room> rooms = Arrays.asList(
                new Room("room1", "host1", Category.GAME, LocalDateTime.now(), new ArrayList<>()),
                new Room("room2", "host2", Category.EAT, LocalDateTime.now(), new ArrayList<>())
        );
        when(roomRepository.findAll()).thenReturn(rooms);

        // Act
        List<Room> actual = roomService.findAll();

        // Assert
        assertEquals(rooms, actual);
    }

}