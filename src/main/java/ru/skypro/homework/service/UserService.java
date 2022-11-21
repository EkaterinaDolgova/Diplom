package ru.skypro.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.ResponseWrapperUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entities.Users;
import ru.skypro.homework.exception.AuthorizedUserNotFoundException;
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
        logger.info("Info getUsers");
        return userRepository.findAll().stream()
                .sorted(Comparator.comparing(Users::getLastName)).collect(Collectors.toList());
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
        return userRepository.getUsersById(id);
    }

    public Users addUser(Users users){
        logger.info("Info gaddUser");
        return userRepository.save(users);
    }

    public Long findIdUser(String author) {
        logger.info("Info findIdUser Поиск id пользователя по имени авторизованного пользователя");
         List<Users> users = userRepository.findUsersByFirstName(author);
         return users.stream().findFirst().get().getId();
    }



}
