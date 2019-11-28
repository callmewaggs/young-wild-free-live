package com.github.callmewaggs.youngwildfreelive.controller;

import com.github.callmewaggs.youngwildfreelive.model.Category;
import com.github.callmewaggs.youngwildfreelive.model.Room;
import com.github.callmewaggs.youngwildfreelive.repository.RoomRepository;
import org.hibernate.annotations.WhereJoinTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class LiveChatController {

    private final RoomRepository roomRepository;

    public LiveChatController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/")
    public ModelAndView displayIndexView() {

        List<Room> rooms = roomRepository.findAll();

        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("rooms", rooms);

        return mav;
    }

    @GetMapping("/room")
    public ModelAndView displayRoomView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("room");
        return mav;
    }
    @GetMapping("/create-room")
    public ModelAndView displayCreateRoomView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("create-room");
        return mav;
    }
    @PostMapping("/create-room")
    public String createRoom(String name, Category category) {
        String hostName = "test";
        Room room = new Room(name, hostName, category, LocalDateTime.now());
        roomRepository.save(room);

        return "redirect:/" + room.getShortURL();
    }

    @GetMapping("/livechat")
    public ModelAndView displayLiveChatView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("livechat");
        return mav;
    }
    @GetMapping("/login")
    public ModelAndView displayLoginView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }
}
