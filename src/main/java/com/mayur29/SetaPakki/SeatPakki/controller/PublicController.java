package com.mayur29.SetaPakki.SeatPakki.controller;

import com.mayur29.SetaPakki.SeatPakki.dto.UserDTO;
import com.mayur29.SetaPakki.SeatPakki.entity.User;
import com.mayur29.SetaPakki.SeatPakki.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seatpakki")
public class PublicController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDTO userDTO){
        User user = userService.signupPublicUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO){
        return userService.loginPublicUser(userDTO);
    }
}
