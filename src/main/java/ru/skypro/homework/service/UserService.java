package ru.skypro.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.ResponseWrapperUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entities.Users;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.exception.AuthorizedUserNotFoundException;
import ru.skypro.homework.exception.UsersNotFoundException;
import ru.skypro.homework.repository.UserRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getUsers() {
        logger.info("Info getUsers Список пользователей");
        return userRepository.findAll();
    }

    public Users updateUser(Users user, UserDto userDto){
        logger.info("Info updateUser");
        if (userDto.getFirstName()!=null) {
            user.setFirstName(userDto.getFirstName());}
        if (userDto.getLastName()!=null) {
            user.setLastName(userDto.getLastName());}
        if(userDto.getPhone()!=null) {
            user.setPhone(userDto.getPhone());
        }
        if (userDto.getEmail()!=null) {
            user.setEmail(userDto.getEmail());
        }
        return userRepository.save(user);
    }

    public NewPasswordDto setPassword(){
        logger.info("Info setPassword");
        return new NewPasswordDto("currentPassword","newPassword");
    }

    public Users getUser(Long id){
        logger.info("Info getUser");
        return userRepository.findById(id).orElseThrow(()-> new UsersNotFoundException("Пользователь не найден"));
    }

    public Users addUser(Users users){
        logger.info("Info addUser Добавить пользователя");
        return userRepository.save(users);

    }

    public Users findIdUser(String author) {
        logger.info("Info findIdUser Поиск id пользователя по имени авторизованного пользователя");
         Users users = userRepository.findByUsername(author).orElseThrow(()-> new UsersNotFoundException("Пользователь не найден"));
        return users;

    }



}
