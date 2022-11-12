package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ImageService;

/**
 * Контроллер Картинок
 */

@RestController
public class ImageController {
    Logger logger = LoggerFactory.getLogger(AdsController.class);
    private ImageService imageService;
    /**
     * Загрузка картинки объявления.
     */
   @Operation(
            summary = "Загрузить картинки объявлений",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Картинки успешно загружена"),
                    @ApiResponse(responseCode = "201", description = "Созданный"),
                    @ApiResponse(responseCode = "401", description = "Неавторизованный"),
                    @ApiResponse(responseCode = "403", description = "Запрещенный"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @CrossOrigin(value = "http://localhost:8080")
    @PostMapping(value = "image/{advertId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long advertId, @RequestParam MultipartFile image) throws Exception {
        imageService.uploadImage(advertId, image);
        return ResponseEntity.ok().build();
    }
}
