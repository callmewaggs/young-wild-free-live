package com.github.callmewaggs.youngwildfreelive.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RoomTest {

    private List<User> watchingUsers;
    private Room room;

    @BeforeEach
    public void setup() {
        watchingUsers = new ArrayList<>(Arrays.asList(
            new User(1L, "user1", "user1", "user1", "user1@user1.com", "01000000000",
                LocalDateTime.now()),
            new User(2L, "user2", "user2", "user2", "user2@user2.com", "01000000000",
                LocalDateTime.now())
        ));
        room = new Room("room1",
            new User(3L, "host", "host", "host", "host", "host", LocalDateTime.now()),
            Category.GAME, LocalDateTime.now(), watchingUsers);
    }

    @DisplayName("Room은 사용자를 반환할 수 있다.")
    @Test
    public void room_should_return_users() {
        // Arrange

        // Act
        List<User> actual = room.watchingUsers();

        // Assert
        assertEquals(watchingUsers, actual);
    }

    @DisplayName("Room은 사용자가 참여할 수 있다.")
    @Test
    public void room_can_join_by_user() {
        // Arrange
        User user = new User(10L, "user3", "user3", "user3", "user3@user3.com", "01000000000",
            LocalDateTime.now());

        // Act
        room.join(user);
        int actual = room.getNowWatching();

        // Assert
        assertEquals(3, actual);
    }

    @DisplayName("Room은 사용자가 두 명 참여할 수 있다.")
    @Test
    public void room_can_join_by_two_user() {
        // Arrange
        User user3 = new User(5L, "user3", "user3", "user3", "user3@user3.com", "01000000000",
            LocalDateTime.now());
        User user4 = new User(6L, "user4", "user4", "user3", "user3@user3.com", "01000000000",
            LocalDateTime.now());

        // Act
        room.join(user3);
        room.join(user4);
        int actual = room.getNowWatching();

        // Assert
        assertEquals(4, actual);
    }
}