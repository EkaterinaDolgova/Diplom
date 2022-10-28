package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.service.AdsService;

/**
 * Контроллер Объявления
 */

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
                    @ApiResponse(responseCode = "200", description = "Список объявлений успешно получен"),
                    @ApiResponse(responseCode = "201", description = "Созданный"),
                    @ApiResponse(responseCode = "401", description = "Неавторизованный"),
                    @ApiResponse(responseCode = "403", description = "Запрещенный"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @CrossOrigin(value = "http://localhost:3000")
    @GetMapping("/ads")
    public String getAllAds() {
        return adsService.getAllAds();
    }

    /**
     * Добавить объявления.
     */
    @Operation(
            summary = "Добавить объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Объявление успешно создано"),
                    @ApiResponse(responseCode = "201", description = "Созданный"),
                    @ApiResponse(responseCode = "401", description = "Неавторизованный"),
                    @ApiResponse(responseCode = "403", description = "Запрещенный"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @CrossOrigin(value = "http://localhost:3000")
    @PostMapping("/ads")
    public String addAds() {
        return adsService.addAds();
    }

    /**
     * Возвращает список объявлений.
     */
    @Operation(
            summary = "Получить список объявлений по параметрам",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список объявлений успешно получен"),
                    @ApiResponse(responseCode = "201", description = "Созданный"),
                    @ApiResponse(responseCode = "401", description = "Неавторизованный"),
                    @ApiResponse(responseCode = "403", description = "Запрещенный"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @CrossOrigin(value = "http://localhost:3000")
    @GetMapping("/ads/me")
    public <object> String getAdsMe(@Parameter(description = "") @PathVariable Ads.authenticated authenticated,
                                    @Parameter(description = "") @PathVariable String authority,
                                    @Parameter(description = "") @PathVariable object credentials,
                                    @Parameter(description = "") @PathVariable object details,
                                    @Parameter(description = "") @PathVariable object principal) {
        return adsService.getAdsMe(authenticated,authority,credentials,details,principal);
    }

    /**
     * Возвращает список комментариев по ad_pk .
     */
    @Operation(
            summary = "Возвращает список комментариев по ad_pk",
            responses = {
                    @ApiResponse(responseCode = "200", description = "ОК"),
                    @ApiResponse(responseCode = "201", description = "Созданный"),
                    @ApiResponse(responseCode = "401", description = "Неавторизованный"),
                    @ApiResponse(responseCode = "403", description = "Запрещенный"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @CrossOrigin(value = "http://localhost:3000")
    @GetMapping("/ads/{ad_pk}/comment")
    public String getAdsComments(@Parameter(description = "") @PathVariable String ad_pk) {
        return adsService.getAdsComments(ad_pk);
    }

    /**
     * Возвращает список комментариев по ad_pk .
     */
    @Operation(
            summary = "Возвращает список комментариев по ad_pk",
            responses = {
                    @ApiResponse(responseCode = "200", description = "ОК"),
                    @ApiResponse(responseCode = "201", description = "Созданный"),
                    @ApiResponse(responseCode = "401", description = "Неавторизованный"),
                    @ApiResponse(responseCode = "403", description = "Запрещенный"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @CrossOrigin(value = "http://localhost:3000")
    @PostMapping("/ads/{ad_pk}/comment")
    public String addAdsComments(@Parameter(description = "") @PathVariable String ad_pk) {
        return adsService.addAdsComments(ad_pk);
    }

    /**
     * Удаление комментария по id .
     */
    @Operation(
            summary = "Удаление комментария по id .",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Удаление комментария успешно"),
                    @ApiResponse(responseCode = "201", description = "Созданный"),
                    @ApiResponse(responseCode = "401", description = "Неавторизованный"),
                    @ApiResponse(responseCode = "403", description = "Запрещенный"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @CrossOrigin(value = "http://localhost:3000")
    @DeleteMapping("/ads/{ad_pk}/comment/{id}")
    public String deleteAdsComment(@Parameter(description = "") @PathVariable String ad_pk,
                                   @Parameter(description = "") @PathVariable Integer id) {
        return adsService.deleteAdsComment(ad_pk, id);
    }

    /**
     * Поиск комментария по id .
     */
    @Operation(
            summary = "Поиск комментария по id .",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Удаление комментария успешно"),
                    @ApiResponse(responseCode = "201", description = "Созданный"),
                    @ApiResponse(responseCode = "401", description = "Неавторизованный"),
                    @ApiResponse(responseCode = "403", description = "Запрещенный"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @CrossOrigin(value = "http://localhost:3000")
    @GetMapping("/ads/{ad_pk}/comment/{id}")
    public String getAdsComment(@Parameter(description = "") @PathVariable String ad_pk,
                                   @Parameter(description = "") @PathVariable Integer id) {
        return adsService.getAdsComment(ad_pk, id);
    }

    /**
     * Изменение комментария по id .
     */
    @Operation(
            summary = "Изменение комментария по id .",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Удаление комментария успешно"),
                    @ApiResponse(responseCode = "201", description = "Созданный"),
                    @ApiResponse(responseCode = "401", description = "Неавторизованный"),
                    @ApiResponse(responseCode = "403", description = "Запрещенный"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @CrossOrigin(value = "http://localhost:3000")
    @PatchMapping ("/ads/{ad_pk}/comment/{id}")
    public String updateAdsComment(@Parameter(description = "") @PathVariable String ad_pk,
                                   @Parameter(description = "") @PathVariable Integer id) {
        return adsService.updateAdsComment(ad_pk, id);
    }

    /**
     * Удаление объявление по id .
     */
    @Operation(
            summary = "Удаление объявление по id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Удаление комментария успешно"),
                    @ApiResponse(responseCode = "201", description = "Созданный"),
                    @ApiResponse(responseCode = "401", description = "Неавторизованный"),
                    @ApiResponse(responseCode = "403", description = "Запрещенный"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @CrossOrigin(value = "http://localhost:3000")
    @DeleteMapping ("/ads/{id}")
    public String removeAds(@Parameter(description = "id объявления") @PathVariable Integer id) {
        return adsService.removeAds(id);
    }

    /**
     * Поиск объявление по id .
     */
    @Operation(
            summary = "Поиск объявление по id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Удаление комментария успешно"),
                    @ApiResponse(responseCode = "201", description = "Созданный"),
                    @ApiResponse(responseCode = "401", description = "Неавторизованный"),
                    @ApiResponse(responseCode = "403", description = "Запрещенный"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @CrossOrigin(value = "http://localhost:3000")
    @GetMapping ("/ads/{id}")
    public String getAds(@Parameter(description = "id объявления") @PathVariable Integer id) {
        return adsService.getAds(id);
    }

    /**
     * Изменение объявление по id .
     */
    @Operation(
            summary = "Изменение объявление по id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Удаление комментария успешно"),
                    @ApiResponse(responseCode = "201", description = "Созданный"),
                    @ApiResponse(responseCode = "401", description = "Неавторизованный"),
                    @ApiResponse(responseCode = "403", description = "Запрещенный"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @CrossOrigin(value = "http://localhost:3000")
    @PatchMapping ("/ads/{id}")
    public String updateAds(@Parameter(description = "id объявления") @PathVariable Integer id) {
        return adsService.updateAds(id);
    }
}
