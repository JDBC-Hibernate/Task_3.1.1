package com.itm.task8.controllers;

import com.itm.task8.model.User;
import com.itm.task8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String printUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/add")
    public String addUserForm(@ModelAttribute("user") User user) {
        return "add";
    }

    @PostMapping("/add")
    public String addUserView(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateUserForm(@ModelAttribute("user") User user) {
        return "update";
    }

    @PostMapping("/update")
    public String updateUserView(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("lastname") String lasname,
            Model model) {
        User user = userService.getUserById(id);
        if (user != null) {
            User updUser = new User(id, name, lasname);
            userService.updateUser(updUser);
            model.addAttribute("user", updUser);
        }
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteUserForm(@ModelAttribute("user") User user) {
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteUserView(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        if (user != null) {
            userService.deleteUser(id);
        }
        return "redirect:/";
    }
}
