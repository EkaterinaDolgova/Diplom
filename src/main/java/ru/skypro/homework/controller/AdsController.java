package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.AdsMapper;
import ru.skypro.homework.entities.Advert;
import ru.skypro.homework.service.AdsService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Контроллер Объявления
 */

@RestController
public class AdsController {
    private final AdsService adsService;
    private final AdsMapper adsMapper;

    public AdsController(AdsService adsService, AdsMapper adsMapper) {
        this.adsService = adsService;

        this.adsMapper = adsMapper;
    }
    Logger logger = LoggerFactory.getLogger(AdsController.class);
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
    public List<AdsDto> getAllAds() {
        return adsService.getAllAds().stream().map(adsMapper::adsDtoToAdvert).collect(Collectors.toList());
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
    public AdsDto addAds(AdsDto adsDto) {
        return adsMapper.adsDtoToAdvert(adsService.addAds(adsMapper.advertToAdsDto(adsDto)));
    }

    /**
     * Возвращает список объявлений.
     */
 /*   @Operation(
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
    public <object> String getAdsMe(@Parameter(description = "") @PathVariable Advert.authenticated authenticated,
                                    @Parameter(description = "") @PathVariable String authority,
                                    @Parameter(description = "") @PathVariable object credentials,
                                    @Parameter(description = "") @PathVariable object details,
                                    @Parameter(description = "") @PathVariable object principal) {
        return adsService.getAdsMe(authenticated,authority,credentials,details,principal);
    }*/

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
    public ResponseEntity removeAds(@Parameter(description = "id объявления") @PathVariable Long id) {
        adsService.removeAds(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
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
    public AdsDto getAds(@Parameter(description = "id объявления") @PathVariable Long id) {
        Advert advert=adsService.getAds(id);
        return adsMapper.adsDtoToAdvert(advert);
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
    public AdsDto  updateAds(@Parameter(description = "id объявления") @PathVariable Long id) {
        return adsMapper.adsDtoToAdvert(adsService.updateAds(id));
    }
}
