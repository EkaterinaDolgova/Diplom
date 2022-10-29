package ru.skypro.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.ResponseWrapperUserDto;
import ru.skypro.homework.dto.UserDto;
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

    public ResponseWrapperUserDto getUsers() {
        logger.info("Info getUsers");
        UserDto user1 = new UserDto("email1", "firstName1", "lastName1", "phone1", 1);
        UserDto user2 = new UserDto("email2", "firstName2", "lastName2", "phone2", 2);
        List<UserDto> listUsers = new ArrayList<>();
        listUsers.add(user1);
        listUsers.add(user2);
        return new ResponseWrapperUserDto(2, listUsers);
    }

    public UserDto updateUser(){
        logger.info("Info updateUser");
        Integer currUser = 5;
        return new UserDto("email3", "firstName3", "lastName3", "phone1", currUser);
    }

    public NewPasswordDto setPassword(){
        logger.info("Info setPasswod");
        return new NewPasswordDto("currentPassword","newPassword");
    }

    public UserDto getUser(Integer id){
        logger.info("Info getUser");
        return new UserDto("email3", "firstName3", "lastName3", "phone1", id);
    }

}
