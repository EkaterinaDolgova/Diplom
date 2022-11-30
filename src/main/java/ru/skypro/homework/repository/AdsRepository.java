package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.User;
import ru.skypro.homework.entities.Advert;
import ru.skypro.homework.entities.Users;

import java.util.List;
import java.util.Optional;

public interface AdsRepository extends JpaRepository<Advert, Long> {
    Optional<Advert> findById(Long id);
    List findByUsersId(Long id);
  //  List findByUsers(User user);
    List<Advert> findByTitleContaining(String title) ;

}
