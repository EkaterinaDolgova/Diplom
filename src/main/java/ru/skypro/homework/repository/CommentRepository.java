package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entities.Advert;
import ru.skypro.homework.entities.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment getCommentById(Integer id);

    Comment getCommentByIdAndAdvertId (Integer ad_pk, Integer id );

    Comment deleteCommentByIdAndAdvert_Id (Integer ad_pk, Integer id );

    List<Comment> findCommentsByAdvert(Advert advert);

    default String getAllAds() {
        return null;
    }
    default String addAds() {
        return null;
    }
    void deleteById(Long id);

}
