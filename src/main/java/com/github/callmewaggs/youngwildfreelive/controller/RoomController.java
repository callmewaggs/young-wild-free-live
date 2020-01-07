package com.github.callmewaggs.youngwildfreelive.controller;

import com.github.callmewaggs.youngwildfreelive.controller.vo.RoomVO;
import com.github.callmewaggs.youngwildfreelive.manager.RoomManager;
import com.github.callmewaggs.youngwildfreelive.manager.UserManager;
import com.github.callmewaggs.youngwildfreelive.model.Category;
import com.github.callmewaggs.youngwildfreelive.model.Room;
import com.github.callmewaggs.youngwildfreelive.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/room")
public class RoomController {

  private final RoomManager roomManager;
  private final UserManager userManager;

  public RoomController(RoomManager roomManager, UserManager userManager) {
    this.roomManager = roomManager;
    this.userManager = userManager;
  }

  // get 으로 데이터를 받을 시 유효 세션이 없을경우 잘못된 접근으로 간주
  @GetMapping("/{id}")
  public ModelAndView displayRoomView(@PathVariable Long id
      , HttpServletRequest request, HttpServletResponse response) throws IOException {
    String username = (String) request.getSession().getAttribute("username");
    if (username == null) {
      alertMessage(response, "Invalid access has been detected.");
      return createModelAndView("signin", null, null);
    }

    Optional<Room> found = roomManager.findRoomById(id);

    // TODO : 1. 유저가 있는지 없는지, 2. 호스트 여부
    if (found.isPresent()) {
      return createModelAndView("room", "roomInfo", new RoomVO(found.get()));
    } else {
      request.getSession().removeAttribute("username");
      alertMessage(response, "Invalid access has been detected.");
      return createModelAndView("index", null, null);
    }
  }

  @PostMapping("/join-room")
  public String joinRoom() {

    return "";
  }

  @GetMapping("/create-room")
  public ModelAndView displayCreateRoomView() {
    return createModelAndView("create-room", null, null);
  }

  @PostMapping("/create-room")
  public String createRoom(String roomname, Category category, HttpServletRequest request) {
    Long id = (long) request.getSession().getAttribute("id");
    User host = userManager.
        Room room = roomManager.createRoom(username, roomname, category);
    return "redirect:/room" + room.getShortURL();
  }

  private void alertMessage(HttpServletResponse response, String message) throws IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<script>alert('" + message + "');</script>");
    out.flush();
  }

  private ModelAndView createModelAndView(String viewName, String attributeName,
      Object attributeValue) {
    ModelAndView mav = new ModelAndView();
    if (attributeName != null && attributeValue != null) {
      mav.addObject(attributeName, attributeValue);
    }
    mav.setViewName(viewName);
    return mav;
  }
}
