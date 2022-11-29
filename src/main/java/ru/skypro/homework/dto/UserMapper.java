package ru.skypro.homework.dto;

import org.mapstruct.Mapper;
import ru.skypro.homework.entities.Users;

@Mapper(componentModel="spring")
public interface UserMapper {
    UserDto toUserDTO(Users users);
    Users userDtoFromUsers(UserDto userDto);
}
