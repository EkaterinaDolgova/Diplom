package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.homework.entities.Advert;

import java.util.List;
import java.util.Optional;

public interface AdsRepository extends JpaRepository<Advert, Long> {
    Optional<Advert> findById(Long id);
    Optional<List<Advert>> findByUsers(Long id);
    List<Advert> findByTitleContaining(String title) ;

}
