package com.edirin.activity_tracker.controller;

import com.edirin.activity_tracker.dto.UserDTO;
import com.edirin.activity_tracker.model.Task;
import com.edirin.activity_tracker.model.Users;
import com.edirin.activity_tracker.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("registrationForm", "Form", new UserDTO());
    }

    @PostMapping("/register")
    public String register_a_user(@ModelAttribute UserDTO userDTO){
        Users registeredUser = userService.register(userDTO);
        return registeredUser!=null?"redirect:/login":"redirect:/register";
    }

    @GetMapping("/login")
    public ModelAndView loginPage(){
        return new ModelAndView("login", "loginForm", new UserDTO());
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Users users, HttpServletRequest httpServletRequest){
        Users user = userService.getUser(users.getUsername(), users.getPassword());
        if (user!=null){
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("username", user.getUsername());
            return "redirect:/home";
        }else return "redirect:/login";
    }

    @GetMapping("/home")
    public ModelAndView allTasks() {
        return new ModelAndView("home", "taskList", new Task());
    }

}
