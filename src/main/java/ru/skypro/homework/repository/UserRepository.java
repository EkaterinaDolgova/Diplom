package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.ResponseWrapperUserDto;
import ru.skypro.homework.entities.Advert;
import ru.skypro.homework.entities.Users;
import ru.skypro.homework.entities.Users;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Users getUsersById(Long id);
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

    List<Users> findUsersByFirstName(String name) ;
}
