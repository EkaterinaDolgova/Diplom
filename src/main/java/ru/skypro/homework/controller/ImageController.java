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
  /*  @Operation(
            summary = "Загрузить картинки объявлений",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Картинки успешно загружена"),
                    @ApiResponse(responseCode = "201", description = "Созданный"),
                    @ApiResponse(responseCode = "401", description = "Неавторизованный"),
                    @ApiResponse(responseCode = "403", description = "Запрещенный"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @CrossOrigin(value = "http://localhost:3000")
    @PostMapping(value = "image/{studentId}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long studentId, @RequestParam MultipartFile avatar) throws Exception {
        imageService.uploadImage(studentId, avatar);
        return ResponseEntity.ok().build();
    }*/
}
