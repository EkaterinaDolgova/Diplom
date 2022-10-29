package ru.skypro.homework.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.dto.Ads;
public interface AdsRepository extends JpaRepository<Ads, Long> {
    default String getAllAds() {
        return null;
    }
    default String addAds() {
        return null;
    }

}
