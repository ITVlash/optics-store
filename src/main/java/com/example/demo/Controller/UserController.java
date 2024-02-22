package com.example.demo.Controller;

import com.example.demo.Service.UserService;
import com.example.demo.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//UserController.java
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.registerUser(user.getLogin(), user.getPassword(), user.getSession());
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String login,
                            @RequestParam String password,
                            @RequestParam int session, Model model) {
        User user = userService.authenticateUser(login, password, session);
        if (user != null) {
            model.addAttribute("user", user);
            return "redirect:/products";
        } else {
            model.addAttribute("error", "Неверные учетные данные");
            return "login";
        }
    }
}