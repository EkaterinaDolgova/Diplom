package ru.skypro.homework.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entities.Advert;
public interface AdsRepository extends JpaRepository<Advert, Long> {
    default String getAllAds() {
        return null;
    }
    default String addAds() {
        return null;
    }

}
