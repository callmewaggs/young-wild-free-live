package com.github.callmewaggs.youngwildfreelive.controller;

import com.github.callmewaggs.youngwildfreelive.manager.RoomManager;
import com.github.callmewaggs.youngwildfreelive.model.Room;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

  private final RoomManager roomManager;

  public IndexController(RoomManager roomManager) {
    this.roomManager = roomManager;
  }

  //TODO : 문제점. 세션은 아직 남아있는데 로그인은 안되어있는 상황이 대처가 안됨
  @GetMapping("/")
  public ModelAndView displayIndexView() {
    List<Room> rooms = roomManager.findAll();
    ModelAndView mav = new ModelAndView();
    mav.setViewName("index");
    mav.addObject("rooms", rooms);

    return mav;
  }
}
