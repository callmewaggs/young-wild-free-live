package com.github.callmewaggs.youngwildfreelive.controller;

import com.github.callmewaggs.youngwildfreelive.controller.request.UserSigninRequest;
import com.github.callmewaggs.youngwildfreelive.controller.vo.UserVO;
import com.github.callmewaggs.youngwildfreelive.model.Category;
import com.github.callmewaggs.youngwildfreelive.model.Room;
import com.github.callmewaggs.youngwildfreelive.model.User;
import com.github.callmewaggs.youngwildfreelive.repository.RoomRepository;
import com.github.callmewaggs.youngwildfreelive.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class LiveChatController {

    private final RoomRepository roomRepository;
    private final UserService userService;

    public LiveChatController(RoomRepository roomRepository, UserService userService) {
        this.roomRepository = roomRepository;
        this.userService = userService;
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
    public String createRoom(String roomname, Category category) {
        String hostName = "test";
        Room room = new Room(roomname, hostName, category, LocalDateTime.now());
        roomRepository.save(room);

        return "redirect:/" + room.getShortURL();
    }

    @GetMapping("/signin")
    public ModelAndView displaySignInView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("signin");
        return mav;
    }
    @PostMapping("/signin")
    public ModelAndView checkUserValidityAndSetSession(@ModelAttribute UserSigninRequest request
            , HttpServletRequest servletRequest, HttpServletResponse response) throws IOException {
        Optional<User> user = userService.findMemberByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (user.isPresent()) {
            servletRequest.getSession().setAttribute("username", request.getUsername());

            ModelAndView mav = new ModelAndView();
            mav.setViewName("/index");
            return mav;
        } else {
            servletRequest.getSession().removeAttribute("username");

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('Username or Password is wrong. Try again.');</script>");
            out.flush();

            ModelAndView mav = new ModelAndView();
            mav.setViewName("signin");
            return mav;
        }
    }



    @GetMapping("/logout")
    public ModelAndView removeSessionAndDisplayIndexView(HttpServletRequest servletRequest) {
        servletRequest.getSession().invalidate();
        servletRequest.getSession().removeAttribute("username");

        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }


    @GetMapping("/signup")
    public ModelAndView displaySignUpView(@ModelAttribute UserVO userVO) {
        if (userVO == null)
            userVO = new UserVO();
        ModelAndView mav = new ModelAndView();
        mav.addObject("signupInfo", userVO);
        mav.setViewName("signup");
        return mav;
    }

    @PostMapping("/signup")
    public ModelAndView signupAndDisplayIndexView(@ModelAttribute UserVO userVO, HttpServletResponse response) throws IOException {
        Optional<User> found = userService.findUserByUsername(userVO.getUsername());
        if(found.isPresent()) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('Username already exist. Try again.');</script>");
            out.flush();

            ModelAndView mav = new ModelAndView();
            mav.addObject("signupInfo", userVO);
            mav.setViewName("signup");
            return mav;
        } else {
            User user = new User(userVO.getUsername(), userVO.getPassword(), userVO.getNickname(), userVO.getEmail(), userVO.getContact(), LocalDateTime.now());
            userService.createUser(user);

            ModelAndView mav = new ModelAndView();
            mav.setViewName("signin");
            return mav;
        }
    }
}
