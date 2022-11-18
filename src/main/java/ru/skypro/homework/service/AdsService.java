package ru.skypro.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entities.Advert;
import ru.skypro.homework.entities.Comment;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdsService {
    Logger logger = LoggerFactory.getLogger(AdsService.class);

    private final AdsRepository adsRepository;
    private final CommentRepository commentRepository;

    /**
     * Возвращает все записи объявления.
     *
     * @return список записей объявления.
     */
    public AdsService(AdsRepository adsRepository, CommentRepository commentRepository) {
        this.adsRepository = adsRepository;
        this.commentRepository = commentRepository;
    }

    /*Отсортированы в алфавитном порядке по названию*/
    public List<Advert> getAllAds() {
        logger.info("Info getAllAds - Все объявления");
        return adsRepository.findAll().stream().sorted(Comparator.comparing(Advert::getTitle)).collect(Collectors.toList());
    }

    /*Список объявлений отсортирован по авторам*/
    public List<Advert> getAllAdsName(String name) {
        logger.info("Info getAllAds - Все объявления по наименованию");
        return adsRepository.getAllAdsNameS(name).stream()
                .sorted(Comparator.comparing(Advert::getUsers)).collect(Collectors.toList());
    }

    public Advert addAds(Advert advert) {
        logger.info("Info addAds Запись объявления");
        adsRepository.save(advert);
        return advert;
    }

    /* public <object> String getAdsMe(Advert.authenticated authenticated, String authority, object credentials, object details, object principal) {
         logger.info("Info getAdsMe");
         return "OK";

     }*/
    /*Список комментариев отсортирован по дате добавления, начиная с самого позднего*/
    public List<Comment> getAdsComments(Integer ad_pk) {
        logger.info("Info getAdsComments"); // !!!
        return commentRepository.getCommentsByAdvert_Id(ad_pk);
        //.stream().sorted(Comparator.comparing(Comment::getCreatedAt).reversed()).collect(Collectors.toList());
    }

    public String addAdsComments(Integer ad_pk) {
        logger.info("Info addAdsComments");
        return "OK";
    }

    public void deleteAdsComment(Integer ad_pk, Integer id) {
        logger.info("Info deleteAdsComment");
        commentRepository.deleteCommentByIdAndAdvert_Id(ad_pk, id);
    }

    public Comment getAdsComment(Integer ad_pk, Integer id) {
        logger.info("Info getAdsComment");
        return commentRepository.getCommentByIdAndAdvertId(ad_pk, id);
    }

    public Comment updateAdsComment(Integer ad_pk, Integer id, Comment comment_new) {
        logger.info("Info updateAdsComment");
        Comment comment = commentRepository.getCommentByIdAndAdvertId(ad_pk, id);
        comment.setId(comment_new.getId());
        comment.setText(comment_new.getText());
        comment.setCreatedAt(comment_new.getCreatedAt());
        comment.setText(comment_new.getText());
        comment.setAdvert(comment_new.getAdvert());
        return commentRepository.save(comment);
    }

    public void removeAds(Long id) {
        logger.info("Info removeAds Удаление по id");
        adsRepository.deleteById(id);
    }

    public Advert getAds(Long id) {
        logger.info("Info Список объявлений по id");
        return adsRepository.getById(id);
    }

    public Advert updateAds(Long id) {
        logger.info("Info updateAds");
        return adsRepository.getById(id);
    }

    /* Объявления одного пользователя отсортированы по названию в алфавитном порядке*/
    public List<Advert> getAdvertsByUserId(Integer id) {
        logger.info("Info getAdsMe");
        return adsRepository.getAdvertsByUsers(id).stream()
                .sorted(Comparator.comparing(Advert::getTitle)).collect(Collectors.toList());
    }

    public Comment addComment(String ad_pk, Comment comment) {
        logger.info("Info addComment");
        // ad_pk !!!
        return commentRepository.save(comment);
    }

}
