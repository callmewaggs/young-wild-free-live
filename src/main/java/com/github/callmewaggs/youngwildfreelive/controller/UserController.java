package com.github.callmewaggs.youngwildfreelive.controller;

import com.github.callmewaggs.youngwildfreelive.controller.request.UserSigninRequest;
import com.github.callmewaggs.youngwildfreelive.controller.vo.UserVO;
import com.github.callmewaggs.youngwildfreelive.manager.UserManager;
import com.github.callmewaggs.youngwildfreelive.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private UserManager userManager;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping("/signin")
    public ModelAndView displaySignInView() {
        return createModelAndView("signin", null, null);
    }

    @PostMapping("/signin")
    public ModelAndView checkUserValidityAndSetSession(@ModelAttribute UserSigninRequest request
            , HttpServletRequest servletRequest, HttpServletResponse response) throws IOException {
        Optional<User> user = userManager
            .findUserByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (user.isPresent()) {
            servletRequest.getSession().setAttribute("id", user.get().getId());
            return createModelAndView("index", null, null);
        } else {
            servletRequest.getSession().removeAttribute("id");
            alertMessage(response, "Username or Password is wrong. Try again.");
            return createModelAndView("signin", null, null);
        }
    }

    @GetMapping("/logout")
    public ModelAndView removeSessionAndDisplayIndexView(HttpServletRequest servletRequest) {
        servletRequest.getSession().invalidate();
        servletRequest.getSession().removeAttribute("id");
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
        Optional<User> found = userManager.findUserByUsername(userVO.getUsername());
        if (found.isPresent()) {
            alertMessage(response, "Username already exist. Try again.");
            return createModelAndView("signup", "signupInfo", userVO);
        } else {
            User user = new User(userVO, LocalDateTime.now());
            userManager.createUser(user);
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
