package ru.skypro.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.ResponseWrapperUserDto;
import ru.skypro.homework.dto.UserDto;
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

    public List<Users> getUsers() {
        logger.info("Info getUsers");
        return userRepository.findAll();
    }

    public Users updateUser(Users user){
        logger.info("Info updateUser");
        return userRepository.save(user);
    }

    public NewPasswordDto setPassword(){
        logger.info("Info setPasswod");
        return new NewPasswordDto("currentPassword","newPassword");
    }

    public Users getUser(Long id){
        logger.info("Info getUser");
        return userRepository.getById(id);
    }

}
