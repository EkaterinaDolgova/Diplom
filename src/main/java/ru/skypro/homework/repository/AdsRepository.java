package ru.skypro.homework.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.entities.Advert;

import java.util.List;

public interface AdsRepository extends JpaRepository<Advert, Long> {
    default String getAllAds() {
        return null;
    }
    default String addAds() {
        return null;
    }

    List<AdsDto> getById(Integer id);

    /*   @Modifying
    @Query(value = "INSERT INTO advert (id, users,image,price,title) value (:id, :users, :image, :price, :title), nativeQuery = true)
    void addAds(Long id, Integer users, String image, Integer price, String title);
*/
}
