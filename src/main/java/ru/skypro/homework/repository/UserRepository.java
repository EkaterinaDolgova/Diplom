package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.ResponseWrapperUserDto;
import ru.skypro.homework.dto.UserDto;

public interface UserRepository extends JpaRepository<UserDto, Long> {

    default ResponseWrapperUserDto getUsers() {
        return null;
    }

    default UserDto getUser(Integer id) {
        return null;
    }

    default NewPasswordDto setPassword() {
        return null;
    }

    default UserDto updateUser() {
        return null;
    }
}
