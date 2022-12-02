package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entities.Users;
import ru.skypro.homework.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Контроллер Объявления для User
 */
@RestController
@CrossOrigin(value = "http://localhost:3000")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * Вывод профиля пользователя.
     */
    @Operation(
            summary = "выводим профиль пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorised"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/users/me")
    public ResponseEntity<UserDto> getUsersMe(Authentication authentification) {
        logger.info("вывод авторизовавшегося пользователя пользователя");
        return userService.getMe(authentification);
    }

    /**
     * Изменение User.
     */
    @Operation(
            summary = "Изменение User",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно"),
                    @ApiResponse(responseCode = "204", description = "Нет соединения"),
                    @ApiResponse(responseCode = "403", description = "Запрещенный"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PatchMapping("/users/me")
    public ResponseEntity<UserDto> updateUser(Authentication authentication, @RequestBody UserDto userDto) {
        logger.info("Info updateUser");
        Users users = userService.findIdUser(authentication.getName());
        return ResponseEntity.ok(userMapper.toUserDTO(userService.updateUser(users, userDto)));
    }

    /**
     * Изменение Пароля User.
     */
    @Operation(
            summary = "Изменение Пароля User",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно"),
                    @ApiResponse(responseCode = "204", description = "Нет соединения"),
                    @ApiResponse(responseCode = "403", description = "Запрещенный"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )

    @PostMapping("/users/set_password")
    public ResponseEntity<NewPasswordDto> setPassword(@Valid @RequestBody NewPasswordDto passwordDto, Authentication auth) {
        logger.info("метод установки пользователю нового пароля");
        return userService.setPassword(passwordDto, auth);
    }

    /**
     * Поиск User по id.
     */
    @Operation(
            summary = "Поиск User по id .",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Поиск User успешно"),
                    @ApiResponse(responseCode = "401", description = "Неавторизованный"),
                    @ApiResponse(responseCode = "403", description = "Запрещенный"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return Optional.ofNullable(userMapper.toUserDTO(userService.getUser(id)))
                .map(st -> ResponseEntity.ok(st))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Добавить пользователя
     */
    @Operation(
            summary = "Добавить пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список успешно получен"),
                    @ApiResponse(responseCode = "401", description = "Неавторизованный"),
                    @ApiResponse(responseCode = "403", description = "Запрещенный"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @PostMapping("/users/add")
    public ResponseEntity<UserDto> addUsers(@Parameter(description = "") @PathVariable UserDto userDto) {
        Users users = userMapper.userDtoFromUsers(userDto);
        userService.addUser(users);
        return ResponseEntity.ok(userMapper.toUserDTO(users));

    }

}
