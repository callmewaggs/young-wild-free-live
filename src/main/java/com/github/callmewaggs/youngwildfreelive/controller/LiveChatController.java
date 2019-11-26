package com.github.callmewaggs.youngwildfreelive.controller;

import com.github.callmewaggs.youngwildfreelive.model.Room;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
public class LiveChatController {

    @GetMapping("/")
    public ModelAndView displayIndexView() {
        Room room1 = new Room(1, "test1", "yoonsung", 100
                , "", "game", "1920x1080", LocalDateTime.now().toString());
        Room room2 = new Room(2, "test2", "yoonsung", 100
                , "", "game", "1920x1080", LocalDateTime.now().toString());

        List<Room> rooms = Arrays.asList(room1, room2);

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

    @GetMapping("/livechat")
    public ModelAndView displayLiveChatView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("livechat");
        return mav;
    }
}
