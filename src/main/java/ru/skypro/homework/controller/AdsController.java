package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.service.AdsService;

/**
 * Контроллер Объявления
 */
@CrossOrigin(value = "http://localhost:3000")
@RestController
public class AdsController {
    private final AdsService adsService;

    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }

    /**
     * Возвращает список объявлений.
     */
    @Operation(
            summary = "Получить список объявлений",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список объявлений успешно получен")
            }
    )
    @GetMapping("/ads")
    public String getAllAds() {
        return adsService.getAllAds();
    }
}
