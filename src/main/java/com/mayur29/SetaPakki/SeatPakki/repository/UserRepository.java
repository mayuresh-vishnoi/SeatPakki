package com.mayur29.SetaPakki.SeatPakki.repository;


import com.mayur29.SetaPakki.SeatPakki.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUserName(String userName);
}
