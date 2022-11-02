package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.ResponseWrapperUserDto;
import ru.skypro.homework.entities.Users;
import ru.skypro.homework.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

    Users getById(Integer id);
    Users getByFirstName(String firstName);
    Users getByLastName(String lastName);
    Users getByPhone(String Phone);
    Users getByEmail(String email);

    default ResponseWrapperUserDto getUsers() {
        return null;
    }

    default Users getUser(Integer id) {
        return null;
    }

    default NewPasswordDto setPassword() {
        return null;
    }

    default Users updateUser() {
        return null;
    }
}
