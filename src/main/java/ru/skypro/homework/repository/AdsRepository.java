package ru.skypro.homework.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.entities.Advert;

import java.util.List;

public interface AdsRepository extends JpaRepository<Advert, Long> {

    List<Advert> getById(Integer id);

//    Advert getByUser(Integer idUser);

    Advert getByTitleLikeIgnoreCase(String title);

    default String getAllAds() {
        return null;
    }
    default String addAds() {
        return null;
    }

}
