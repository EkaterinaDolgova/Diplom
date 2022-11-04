package ru.skypro.homework.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.entities.Users;

@Mapper(componentModel="spring")
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    UserDto toUserDTO(Users users);
}
