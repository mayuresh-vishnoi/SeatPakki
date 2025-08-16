package com.mayur29.SetaPakki.SeatPakki.controller;

import com.mayur29.SetaPakki.SeatPakki.entity.User;
import com.mayur29.SetaPakki.SeatPakki.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Component
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get-all-user")
    public ResponseEntity<List<User>> getAllUsers(){
        return userService.getAllUsers();
    }
}
