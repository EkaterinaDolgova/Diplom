package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entities.Advert;
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

    @Operation(
            summary = "Получить список User's",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список успешно получен"),
                    @ApiResponse(responseCode = "401", description = "Неавторизованный"),
                    @ApiResponse(responseCode = "403", description = "Запрещенный"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @GetMapping("/users/me")
    public ResponseEntity<ResponseWrapperUserDto> getUsers() {
        List<UserDto> userDtoList = userService.getUsers().stream().map(userMapper::toUserDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseWrapperUserDto(userDtoList.size(), userDtoList));
    }

    /**
     * Изменение Пароля User.
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
    @PatchMapping("/users/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        logger.info("Info updateUser");
        return ResponseEntity.ok(userMapper.toUserDTO(userService.updateUser(userMapper.userDtoFromUsers(userDto))));
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
    @PatchMapping("/users/set_password")
    public ResponseEntity<NewPasswordDto> setPassword() {
        return ResponseEntity.ok(userService.setPassword());
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


    @Operation(
            summary = "Add User",
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
           //     userService.addUser(userMapper.userDtoFromUsers(userDto));
        return ResponseEntity.ok(userMapper.toUserDTO(users));

    }

    /**
     * Загрузка картинки Users.
     */
    @Operation(
            summary = "Загрузка картинки Users",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно"),
                    @ApiResponse(responseCode = "204", description = "Нет соединения"),
                    @ApiResponse(responseCode = "403", description = "Запрещенный"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @PatchMapping("/users/me/image")
    public ResponseEntity<UserDto> UpdateUserImage(@Valid @RequestPart(name = "properties") UserDto userDto,
                                                   @RequestPart("image") MultipartFile file) {


        return null;
    }


}
