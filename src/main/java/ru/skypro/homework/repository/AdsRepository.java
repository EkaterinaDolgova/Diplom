package ru.skypro.homework.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.dto.AdsDto;
public interface AdsRepository extends JpaRepository<AdsDto, Long> {
    default String getAllAds() {
        return null;
    }
    default String addAds() {
        return null;
    }

}
