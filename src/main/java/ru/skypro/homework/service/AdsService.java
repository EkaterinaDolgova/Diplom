package ru.skypro.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entities.Advert;
import ru.skypro.homework.entities.Comment;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;

import java.util.List;

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

    public List<Advert> getAllAds() {
        logger.info("Info getAllAds - Все объявления");
        return adsRepository.findAll();
    }

    public List<Advert> getAllAdsName(String name) {
        logger.info("Info getAllAds - Все объявления по наименованию");
        return adsRepository.getAllAdsNameS(name);
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
    public List<Comment> getAdsComments(String ad_pk) {
        logger.info("Info getAdsComments"); // !!!
        return commentRepository.getCommentById(ad_pk);
    }

    public String addAdsComments(String ad_pk) {
        logger.info("Info addAdsComments");
        return "OK";
    }

    public void deleteAdsComment(String ad_pk, Integer id) {
        logger.info("Info deleteAdsComment");
        // ad_pk
        // Comment comment = commentRepository.getCommentById(String.valueOf(id));
        commentRepository.deleteById(Long.valueOf(id));
    }

    public String getAdsComment(String ad_pk, Integer id) {
        logger.info("Info getAdsComment");
        return "OK";
    }

    public String updateAdsComment(String ad_pk, Integer id) {
        logger.info("Info updateAdsComment");
        return "OK";
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

    public List<Advert> getAdvertsByUserId(Integer id) {
        logger.info("Info getAdsMe");
        return adsRepository.getAdvertsByUsers(id);
    }

    public Comment addComment(String ad_pk,Comment comment){
        logger.info("Info addComment");
        // ad_pk !!!
       return commentRepository.save(comment);
    }

}
