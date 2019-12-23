package com.github.callmewaggs.youngwildfreelive.controller;

import com.github.callmewaggs.youngwildfreelive.controller.request.UserSigninRequest;
import com.github.callmewaggs.youngwildfreelive.controller.vo.UserVO;
import com.github.callmewaggs.youngwildfreelive.model.User;
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
import java.util.Optional;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signin")
    public ModelAndView displaySignInView() {
        return createModelAndView("signin", null, null);
    }

    @PostMapping("/signin")
    public ModelAndView checkUserValidityAndSetSession(@ModelAttribute UserSigninRequest request
            , HttpServletRequest servletRequest, HttpServletResponse response) throws IOException {
        Optional<User> user = userService.findMemberByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (user.isPresent()) {
            servletRequest.getSession().setAttribute("username", request.getUsername());
            return createModelAndView("index", null, null);
        } else {
            servletRequest.getSession().removeAttribute("username");
            alertMessage(response, "Username or Password is wrong. Try again.");
            return createModelAndView("signin", null, null);
        }
    }

    @GetMapping("/logout")
    public ModelAndView removeSessionAndDisplayIndexView(HttpServletRequest servletRequest) {
        servletRequest.getSession().invalidate();
        servletRequest.getSession().removeAttribute("username");
        return createModelAndView("index", null, null);
    }

    @GetMapping("/signup")
    public ModelAndView displaySignUpView(@ModelAttribute UserVO userVO) {
        if (userVO == null)
            userVO = new UserVO();

        return createModelAndView("signup", "signupInfo", userVO);
    }

    @PostMapping("/signup")
    public ModelAndView signupAndDisplayIndexView(@ModelAttribute UserVO userVO, HttpServletResponse response) throws IOException {
        Optional<User> found = userService.findUserByUsername(userVO.getUsername());
        if (found.isPresent()) {
            alertMessage(response, "Username already exist. Try again.");
            return createModelAndView("signup", "signupInfo", userVO);
        } else {
            User user = new User(userVO, LocalDateTime.now());
            userService.createUser(user);
            return createModelAndView("signin", null, null);
        }
    }

    private void alertMessage(HttpServletResponse response, String message) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + message + "');</script>");
        out.flush();
    }

    private ModelAndView createModelAndView(String viewName, String attributeName, Object attributeValue) {
        ModelAndView mav = new ModelAndView();
        if (attributeName != null && attributeValue != null) {
            mav.addObject(attributeName, attributeValue);
        }
        mav.setViewName(viewName);
        return mav;
    }
}
