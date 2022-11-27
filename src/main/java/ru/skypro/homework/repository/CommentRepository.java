package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entities.Advert;
import ru.skypro.homework.entities.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByAdvertAndId(Advert advert, Long id );

    //Исправлено - метод поиска по объявлению возвращает списое комментариев
    List<Comment> findCommentsByAdvert(Advert advert);
    void deleteById(Long id);

}
