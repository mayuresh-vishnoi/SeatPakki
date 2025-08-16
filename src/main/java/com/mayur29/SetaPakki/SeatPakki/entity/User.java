package com.mayur29.SetaPakki.SeatPakki.entity;

import com.mongodb.lang.NonNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "SeatPakki")
@Data
@Component
@NoArgsConstructor
public class User {
    @Id
    String id;
    @Indexed(unique = true)
    @NonNull
    String userName;
    @NonNull
    String password;
    List<String> roles = new ArrayList<>();
    String emailId;
}
