package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.entities.Image;
import ru.skypro.homework.service.ImageService;

@Slf4j
@RestController
@RequestMapping("/image")
@CrossOrigin(value = "http://localhost:3000")
public class ImageController {

    private final ImageService imageService;

    public ImageController (ImageService imageService) {
        this.imageService = imageService;
    }

    Logger logger = LoggerFactory.getLogger(AdsController.class);

    @GetMapping(value = "/{id}/", produces = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public byte[] getImage(@PathVariable Long id) {
        logger.info("Показ картинки: {}");
        Image image = imageService.getImageByAdvertId(id);
        return image.getImage();
    }

}
