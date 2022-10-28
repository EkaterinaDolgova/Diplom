package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.ResponseWrapperUser;
import ru.skypro.homework.dto.User;

public interface UserRepository extends JpaRepository<User, Long> {

    default ResponseWrapperUser getUsers() {
        return null;
    }

    default User getUser(Integer id) {
        return null;
    }

    default NewPassword setPassword() {
        return null;
    }

    default User updateUser() {
        return null;
    }
}
