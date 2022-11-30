package ru.skypro.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entities.Advert;
import ru.skypro.homework.entities.Comment;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.exception.AuthorizedUserNotFoundException;
import ru.skypro.homework.exception.UsersNotFoundException;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdsService {
    Logger logger = LoggerFactory.getLogger(AdsService.class);
    private final AdsRepository adsRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;

    public AdsService(AdsRepository adsRepository, CommentRepository commentRepository, UserService userService) {
        this.adsRepository = adsRepository;
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    /**
     * Возвращает все объявления.
     *
     * @return список объявления.
     */
    public List<Advert> getAllAds() {
        logger.info("Info getAllAds - Все объявления");
        System.out.println(adsRepository.findAll());
        return adsRepository.findAll();
    }

    /**
     * Возвращает объявления по наименованию.
     *
     * @return список объявления по наименованию.
     */
    public List<Advert> getAllAdsName(String name) {
        logger.info("Info getAllAds - Все объявления по наименованию");
        List<Advert> adverts1 = adsRepository.findByTitleContaining(name);
        return adverts1;
    }

    /**
     * Создание нового объявления.
     *
     * @return создание нового объявления.
     */
    public Advert addAds(Advert advert) {
        logger.info("Info addAds Запись объявления");
        adsRepository.save(advert);
        return advert;
    }

    /**
     * Возвращает список комментраиев определенного объявления.
     *
     * @return список комментраиев определенного объявления.
     */
    public List<Comment> getAdsComments(Integer ad_pk) {
        logger.info("Info getAdsComments Список комментариев");
        return commentRepository.findCommentsByAdvert(adsRepository.findById(Long.valueOf(ad_pk)).get());
    }

    /**
     * Удаление комментария определенного объявления.
     *
     * @return удаление комментрия определенного объявления.
     */
    public void deleteAdsComment(Integer id) {
        logger.info("Info deleteAdsComment");
        commentRepository.deleteById(Long.valueOf(id));
    }

    /**
     * Возвращает комментарий определенного объявления по id.
     *
     * @return комментарий определенного объявления по id.
     */
    public Comment getAdsComment(Integer ad_pk, Integer id) {
        logger.info("Info getAdsComment Поиск комментария по ad_pk/id");
        return commentRepository.findByAdvertAndId(adsRepository.findById(Long.valueOf(ad_pk)).get(), Long.valueOf(id)).orElseThrow(() -> new AdsNotFoundException("Комментарий не найден"));
    }

    /**
     * Обновление комментария определенного объявления по id.
     *
     * @return обновление комментария определенного объявления по id.
     */
    public Comment updateAdsComment(Integer ad_pk, Integer id, Comment comment_new) {
        logger.info("Info updateAdsComment Изменение Комментария у объявления");
        Comment comment = commentRepository.findByAdvertAndId(adsRepository.findById(Long.valueOf(ad_pk)).get(), Long.valueOf(id)).orElseThrow(() -> new AdsNotFoundException("Комментарий не найден"));
        comment.setText(comment_new.getText());
        comment.setCreatedAt(comment_new.getCreatedAt());
        return commentRepository.save(comment);

    }

    /**
     * Удаление объявления по id.
     *
     * @return удаление объявления по id.
     */
    public void removeAds(Long id) {
        logger.info("Info removeAds Удаление по id");
        adsRepository.deleteById(id);
    }

    /**
     * Возвращает список объявлений по id.
     *
     * @return список объявлений по id.
     */
    public Advert getAds(Long id) {
        logger.info("Info Список объявлений по id");
        return adsRepository.findById(id).orElseThrow(() -> new AdsNotFoundException("Объявления по id не найдено"));
    }

    /**
     * Обновление объявления по id.
     *
     * @return обновление объявления по id.
     */
    public Advert updateAds(Long id, Advert advert) {
        logger.info("Info updateAds");
        Advert advert_ = adsRepository.findById(id).orElseThrow(() -> new AdsNotFoundException("Объявления по id не найдено"));
        advert_.setTitle(advert.getTitle());
        advert_.setPrice(advert.getPrice());
        return adsRepository.save(advert_);
    }

    /**
     * Обновление картинки у объявления.
     *
     * @return обновление картинки.
     */
    public Advert updateAdsImage(Long id, String images) {
        logger.info("Info updateAdsImage");
        Advert advert_ = adsRepository.findById(id).orElseThrow(() -> new AdsNotFoundException("Объявления по id не найдено"));
        advert_.setImage(images);
        return adsRepository.save(advert_);
    }

    /**
     * Возвращает список объявлений авторизованного пользователя.
     *
     * @return список объявлений авторизованного пользователя.
     */
    public List<Advert> getAdvertsByUserId(Long id) {
        logger.info("Info getAdsMe");
        return adsRepository.findByUsers(id);//.orElseThrow(() -> new UsersNotFoundException("У данного пользователя нет объявлений"));
    }

    /**
     * Создание комментария определенного объявления.
     *
     * @return создание комментария.
     */
    public Comment addComment(Integer ad_pk, Comment comment) {
        logger.info("Info addComment Добавление комментария");
        Advert advert = adsRepository.findById(Long.valueOf(ad_pk)).orElseThrow(() -> new AdsNotFoundException("Объявление не найдено"));
        logger.info("Advert", advert);
        comment.setAdvert(advert);
        return commentRepository.save(comment);
    }


    /**
     * Возвращает роль авторизованного пользователя.
     *
     * @return роль авторизованного пользователя.
     */
    public String findIdUserRole(String author) {
        logger.info("Info findIdUserRole Поиск роли по имени авторизованного пользователя");
        return userService.getUsers().stream()
                .filter(user -> user.getUsername().equals(author)).findFirst()
                .orElseThrow(() -> new AuthorizedUserNotFoundException("Ошибка 403: пользователь не найден")).getRole();
    }

    /**
     * Возвращает список объявлений авторизованного пользователя.
     *
     * @return список объявлений авторизованного пользователя.
     */
    public Set<Long> findAdvertIdUser(Long userId) {
        logger.info("Info findAdvertIdUser Поиск объявления у пользователя");
        return adsRepository.findAll().stream().
                filter(advert -> advert.getUsers().getId().equals(userId)).
                map(Advert::getId).collect(Collectors.toSet());
    }

    /**
     * Возвращает комментарий по id.
     *
     * @return комментарий по id.
     */
    public Set<Long> findIdComment(Long userId) {
        logger.info("Info findIdComment Поиск  id комментария пользователя");
        return commentRepository.findAll().stream().
                filter(comment -> Long.valueOf(comment.getUsers()).equals((userId))).
                map(Comment::getId).collect(Collectors.toSet());
    }

}
