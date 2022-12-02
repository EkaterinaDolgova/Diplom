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

    Optional <Users> findById(Long id);
    Optional <Users> findByUsername(String username) ;
}
