package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.ResponseWrapperUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entities.Users;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Контроллер Объявления для User
 */
@RestController
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService; this.userMapper = userMapper;
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
    @CrossOrigin(value = "http://localhost:3000")
    @GetMapping("/users/me")
    public ResponseEntity<ResponseWrapperUserDto> getUsers() {
        List<UserDto> userDtoList= userService.getUsers().stream().map(userMapper::toUserDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseWrapperUserDto(userDtoList.size(),userDtoList));
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
    @CrossOrigin(value = "http://localhost:3000")
    @PatchMapping("/users/me")
    public ResponseEntity<UserDto> updateUser(UserDto userDto) {

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
    @CrossOrigin(value = "http://localhost:3000")
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
    @CrossOrigin(value = "http://localhost:3000")
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return Optional.ofNullable(userMapper.toUserDTO(userService.getUser(id)))
                .map(st -> ResponseEntity.ok(st))
                .orElse(ResponseEntity.notFound().build());
    }

}
