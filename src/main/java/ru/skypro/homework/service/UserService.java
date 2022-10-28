package ru.skypro.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.ResponseWrapperUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseWrapperUser getUsers() {
        logger.info("Info getUsers");
        User user1 = new User("email1", "firstName1", "lastName1", "phone1", 1);
        User user2 = new User("email2", "firstName2", "lastName2", "phone2", 2);
        List<User> listUsers = new ArrayList<>();
        listUsers.add(user1);
        listUsers.add(user2);
        return new ResponseWrapperUser(2, listUsers);
    }

    public User updateUser(){
        logger.info("Info updateUser");
        Integer currUser = 5;
        return new User("email3", "firstName3", "lastName3", "phone1", currUser);
    }

    public NewPassword setPassword(){
        logger.info("Info setPasswod");
        return new NewPassword ("currentPassword","newPassword");
    }

    public User getUser(Integer id){
        logger.info("Info getUser");
        return new User("email3", "firstName3", "lastName3", "phone1", id);
    }

}
