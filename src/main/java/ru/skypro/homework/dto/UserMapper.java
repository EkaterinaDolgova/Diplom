package ru.skypro.homework.dto;

import org.mapstruct.Mapper;
import ru.skypro.homework.entities.Users;
import ru.skypro.homework.service.ImageService;

@Mapper(componentModel="spring")
public interface UserMapper {
    UserDto toUserDTO(Users users);
    Users userDtoFromUsers(UserDto userDto);
}
