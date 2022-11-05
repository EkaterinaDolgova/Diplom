package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entities.Advert;

import java.util.List;

public interface AdsRepository extends JpaRepository<Advert, Long> {

    Advert getById(Long id);

    List<Advert> getAdvertsByUsers(Integer id);

    void deleteById(Long id);

    // Advert getByUser(Integer idUser);

    Advert getByTitleLikeIgnoreCase(String title);

    default String getAllAds() {
        return null;
    }

    default String addAds() {
        return null;
    }

}
