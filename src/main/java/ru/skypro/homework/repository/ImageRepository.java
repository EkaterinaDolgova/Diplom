package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entities.Image;

import java.util.Optional;

public  interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByAdvertId(Long advertId);
}
