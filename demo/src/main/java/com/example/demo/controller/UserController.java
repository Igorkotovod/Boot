package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String mainPage() {
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("listUser", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("/new")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user, @PathVariable(value = "id") Long id) {
        userService.editUser(user, id);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String removeUserById(@PathVariable(value = "id") Long id) {
        userService.removeUserById(id);
        return "redirect:/users";
    }
}
