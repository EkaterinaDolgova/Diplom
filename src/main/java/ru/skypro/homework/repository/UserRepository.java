package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.ResponseWrapperUserDto;
import ru.skypro.homework.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    default ResponseWrapperUserDto getUsers() {
        return null;
    }

    default User getUser(Integer id) {
        return null;
    }

    default NewPasswordDto setPassword() {
        return null;
    }

    default User updateUser() {
        return null;
    }
}
