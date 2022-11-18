package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.homework.entities.Advert;

import java.util.List;

public interface AdsRepository extends JpaRepository<Advert, Long> {

    Advert getById(Long id);

    List<Advert> getAdvertsByUsers(Integer id);

    void deleteById(Long id);

    Advert getByTitleLikeIgnoreCase(String title);

    default String addAds() {
        return null;
    }

    @Query(value = "Select * from advert where title like '%'||:name||'%'  ORDER BY id DESC  LIMIT 5 ", nativeQuery = true)
    List<Advert> getAllAdsNameS(String name) ;


}
