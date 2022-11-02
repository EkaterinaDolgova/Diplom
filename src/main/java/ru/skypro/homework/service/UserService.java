package ru.skypro.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.ResponseWrapperUserDto;
import ru.skypro.homework.entities.Users;
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
        Users user1 = new Users("email1", "firstName1", "lastName1", "phone1",1L);
        Users user2 = new Users("email2", "firstName2", "lastName2", "phone2",1L );
        List<Users> listUsers = new ArrayList<>();
        listUsers.add(user1);
        listUsers.add(user2);
        return new ResponseWrapperUserDto(2, listUsers);
    }

    public Users updateUser(){
        logger.info("Info updateUser");
        Long currUser = 5L;
        return new Users("email3", "firstName3", "lastName3", "phone1", currUser);
    }

    public NewPasswordDto setPassword(){
        logger.info("Info setPasswod");
        return new NewPasswordDto("currentPassword","newPassword");
    }

    public Users getUser(Integer id){
        logger.info("Info getUser");
        return new Users("email3", "firstName3", "lastName3", "phone1",1L);
    }

}
