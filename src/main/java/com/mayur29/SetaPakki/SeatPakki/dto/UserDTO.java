package com.mayur29.SetaPakki.SeatPakki.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserDTO {
    private String userName;
    private String password;
    private String email;
}
