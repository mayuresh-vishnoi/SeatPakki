package com.mayur29.SetaPakki.SeatPakki.service;

import com.mayur29.SetaPakki.SeatPakki.dto.UserDTO;
import com.mayur29.SetaPakki.SeatPakki.entity.User;
import com.mayur29.SetaPakki.SeatPakki.repository.UserRepository;
import com.mayur29.SetaPakki.SeatPakki.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsImpl userDetails;
    @Autowired
    private JwtUtils jwtUtils;
    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> all = userRepository.findAll();
        log.info("Users :: {}",all);
        if(!all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public User signupPublicUser(UserDTO userDTO) {
        log.info("user dto :: {}",userDTO);
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmailId(user.getEmailId());
        log.info("user created :: {}",user);
        userRepository.save(user);
        return user;
    }

    public ResponseEntity<String> loginPublicUser(UserDTO userDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUserName(),userDTO.getPassword()));
        userDetails.loadUserByUsername(userDTO.getUserName());
        try {
            String token = jwtUtils.createToken(userDTO.getUserName());
            log.info("token ::{}",token);
            return new ResponseEntity<>(token,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
