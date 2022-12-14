package ru.skypro.homework.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.UserMapper;
import ru.skypro.homework.entities.Users;
import ru.skypro.homework.exception.UsersNotFoundException;
import ru.skypro.homework.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private static final String ENCRYPTION_PREFIX = "{bcrypt}";

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Возвращает список пользователей.
     *
     * @return список пользователей.
     */
    public List<Users> getUsers() {
        logger.info("Info getUsers Список пользователей");
        return userRepository.findAll();
    }

    public ResponseEntity<UserDto> getMe(Authentication auth) {
        logger.info("Сервис получения авторизовавшегося пользователя");
        Users user = userRepository.findByUsername(auth.getName()).orElseThrow();
        UserDto userDto = userMapper.toUserDTO(user);
        return ResponseEntity.ok(userDto);
    }

    /**
     * Обновление пользователя.
     *
     * @return обновление пользователя.
     */
    public Users updateUser(Users user, UserDto userDto) {
        logger.info("Info updateUser");
        if (userDto.getFirstName() != null) {
            user.setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName() != null) {
            user.setLastName(userDto.getLastName());
        }
        if (userDto.getPhone() != null) {
            user.setPhone(userDto.getPhone());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        return userRepository.save(user);
    }

    /**
     * Изменение пароля пользователя.
     *
     * @return изменение пароля пользователя.
     */
    public ResponseEntity<NewPasswordDto> setPassword(NewPasswordDto passwordDto, Authentication authentification) {
            logger.info("Смена пароля");
            Optional<Users> optionalUser = userRepository.findByUsername(authentification.getName());
            if (optionalUser.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            logger.info(passwordDto.getCurrentPassword());
            logger.info(optionalUser.get().getPassword());
        String encryptedPassword = optionalUser.get().getPassword();
        String prefix = encryptedPassword.substring(0, ENCRYPTION_PREFIX.length());
        String ecryptedPasswordWithoutEncryptionType = encryptedPassword;
        if (prefix.equals(ENCRYPTION_PREFIX)) {
            ecryptedPasswordWithoutEncryptionType = encryptedPassword.substring(ENCRYPTION_PREFIX.length());
        }
        if (passwordDto.getNewPassword().isEmpty() || !passwordEncoder.matches(passwordDto.getCurrentPassword(), ecryptedPasswordWithoutEncryptionType)) {
            logger.info("Текущий пароль указан неверно");
            return ResponseEntity.notFound().build();
        }
            Users user = optionalUser.get();
            user.setPassword(ENCRYPTION_PREFIX+passwordEncoder.encode(passwordDto.getNewPassword()));
            userRepository.save(user);
            log.info("Пароль текущего пользователя обновлен");
            return ResponseEntity.ok(passwordDto);
    }

    /**
     * Информация о пользователя по id.
     *
     * @return информация о пользователе.
     */
    public Users getUser(Long id) {
        logger.info("Info getUser");
        return userRepository.findById(id).orElseThrow(() -> new UsersNotFoundException("Пользователь не найден"));
    }

    /**
     * Создание нового пользователя.
     *
     * @return создание нового пользователя.
     */
    public Users addUser(Users users) {
        logger.info("Info addUser Добавить пользователя");
        return userRepository.save(users);

    }

    /**
     * Поиск id по авторизованному пользователю.
     *
     * @return id авторизованного пользователя.
     */
    public Users findIdUser(String author) {
        logger.info("Info findIdUser Поиск id пользователя по имени авторизованного пользователя");
        Users users = userRepository.findByUsername(author).orElseThrow(() -> new UsersNotFoundException("Пользователь не найден"));
        return users;

    }


}
