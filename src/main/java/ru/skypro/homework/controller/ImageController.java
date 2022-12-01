package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entities.Advert;
import ru.skypro.homework.entities.Image;
import ru.skypro.homework.entities.Users;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/image")
@CrossOrigin(value = "http://localhost:3000")
public class ImageController {

    private final ImageService imageService;
    private final UserService userService;


    public ImageController (ImageService imageService, UserService userService) {
        this.imageService = imageService;
        this.userService = userService;
    }

    Logger logger = LoggerFactory.getLogger(AdsController.class);

    /*@Operation(summary = "Обновить картинки объявлений", responses = {@ApiResponse(responseCode = "200", description = "Картинки успешно загружена"), @ApiResponse(responseCode = "201", description = "Созданный"), @ApiResponse(responseCode = "401", description = "Неавторизованный"), @ApiResponse(responseCode = "403", description = "Запрещенный"), @ApiResponse(responseCode = "404", description = "Не найдено")})
    @PatchMapping(value = "", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<String> updateImage(@PathVariable Long id, @RequestParam MultipartFile image, Authentication authentication) throws Exception {
        Users users = userService.findIdUser(authentication.getName());
        String userRole = adsService.findIdUserRole(authentication.getName());
        //Проверяем есть ли у данного пользователя объявления и записываем их в лист
        Set<Long> idAdvert1 = adsService.findAdvertIdUser(users.getId());
        //Если выбранное объявление создано пользователем, то можно редактировать
        if (idAdvert1.contains(id) || userRole.equals("ADMIN")) {
            Advert advert = adsService.getAds(id);
            imageService.createImage(advert, image);
            return ResponseEntity.ok().build();
        } else {
            throw new AdsNotFoundException("Ошибка 403: Вы не можете редактировать данное объявление!");
        }
    }

   /* @GetMapping(value = "/{id}/", produces = {MediaType.MULTIPART_FORM_DATA_VALUE})
    /*public byte[] getImage(@PathVariable Long id) {
        logger.info("Показ картинки: {}");
        Image image = imageService.getImageByAdvertId(id);
        return image.getImage();
    public byte[] getImage(@PathVariable Long id) {
        logger.info("Показ картинки: {}");
        Image image = imageService.getImageById(id);
        return image.getImage();
    }*/


}
