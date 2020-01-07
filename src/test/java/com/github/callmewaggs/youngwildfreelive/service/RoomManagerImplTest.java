package com.github.callmewaggs.youngwildfreelive.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.github.callmewaggs.youngwildfreelive.manager.RoomManager;
import com.github.callmewaggs.youngwildfreelive.manager.RoomManagerImpl;
import com.github.callmewaggs.youngwildfreelive.manager.UserManager;
import com.github.callmewaggs.youngwildfreelive.model.Category;
import com.github.callmewaggs.youngwildfreelive.model.Room;
import com.github.callmewaggs.youngwildfreelive.repository.RoomRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RoomManagerImplTest {

    RoomRepository roomRepository;
    UserManager userManager;
    RoomManager roomManager;

    @BeforeEach
    public void setup() {
        roomRepository = mock(RoomRepository.class);
        userManager = mock(UserManager.class);
        roomManager = new RoomManagerImpl(roomRepository, userManager);
    }

    @DisplayName("RoomService는 방이 없는 경우 빈 방 목록을 리턴한다.")
    @Test
    public void return_room_list_when_empty_room() {
        // Arrange
        when(roomRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Room> actual = roomManager.findAll();

        // Assert
        List<Room> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @DisplayName("RoomService는 방이 있는 경우 방 목록을 리턴한다.")
    @Test
    public void return_room_list_when_room_exist() {
        // Arrange
        List<Room> rooms = Arrays.asList(
            new Room("room1", "host1", Category.GAME, LocalDateTime.now(), new ArrayList<>()),
            new Room("room2", "host2", Category.EAT, LocalDateTime.now(), new ArrayList<>())
        );
        when(roomRepository.findAll()).thenReturn(rooms);

        // Act
        List<Room> actual = roomManager.findAll();

        // Assert
        assertEquals(rooms, actual);
    }

}