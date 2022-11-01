package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entities.Advert;

public interface AdsRepository extends JpaRepository<Advert, Long> {

    Advert getById(Long id);

    Advert getByUser(Integer idUser);

    Advert getByTitleLikeIgnoreCase(String title);

    default String getAllAds() {
        return null;
    }

    default String addAds() {
        return null;
    }

}
