package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.homework.entities.Advert;

import java.util.List;

public interface AdsRepository extends JpaRepository<Advert, Long> {

    Advert findAdvertById(Long id);
    List<Advert> findAdvertByUsers(Long id);
    Advert findByTitleLikeIgnoreCase(String title);
    List<Advert> findAdsByTitleContaining(String title) ;
    List<Advert> findAll() ;

}
