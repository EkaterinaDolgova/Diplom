package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entities.Advert;
import ru.skypro.homework.entities.Comment;
import ru.skypro.homework.entities.Users;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.exception.AuthorizedUserNotFoundException;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Контроллер Объявления
 */

@RestController
public class AdsController {
    private final AdsService adsService;
    private final AdsMapper adsMapper;
    private final ImageService imageService;
    private final AdsCommentMapper adsCommentMapper;
    private final UserService userService;


    public AdsController(AdsService adsService, AdsMapper adsMapper, ImageService imageService, AdsCommentMapper adsCommentMapper, UserService userService) {
        this.adsService = adsService;
        this.adsMapper = adsMapper;
        this.imageService = imageService;
        this.adsCommentMapper = adsCommentMapper;
        this.userService = userService;
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
    public ResponseEntity<ResponseWrapperAdsDto> getAllAds() {
        List<AdsDto> listAdsDto = adsService.getAllAds().stream().map(adsMapper::toAdsDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseWrapperAdsDto(listAdsDto.size(), listAdsDto));
    }

    /**
     * Возвращает список объявлений по поиску наименования.
     */

    @Operation(
            summary = "Получить список объявлений по поиску наименования",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список объявлений успешно получен"),
                    @ApiResponse(responseCode = "201", description = "Созданный"),
                    @ApiResponse(responseCode = "401", description = "Неавторизованный"),
                    @ApiResponse(responseCode = "403", description = "Запрещенный"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @CrossOrigin(value = "http://localhost:3000")
    @GetMapping("/ads/search/{name}")
    public ResponseEntity<ResponseWrapperAdsDto> getAllAdsName(@Parameter(description = "Введите имя пользователя") @PathVariable String name, Authentication authentication) {
        //Поиск id из таблицы users по авторизованному имени пользователя
        /* Optional
                <Users> idUserFind= userService.getUsers().stream()
                 .filter(user -> user.getFirstName().equals(authentication.getName())).findFirst();*/
        //Получение id users
        Long idUser = userService.getUsers().stream()
                .filter(user -> user.getFirstName().equals(authentication.getName())).findFirst()
                .orElseThrow(() -> new AuthorizedUserNotFoundException("Ошибка 404: пользователь не найден")).getId();
        int i = idUser.intValue();
        //Проверяем есть ли у данного пользователя объявление
        if (adsService.getAllAds().stream().anyMatch(advert -> advert.getUsers().equals((i)))) {
            List<AdsDto> listAdsDto = adsService.getAllAdsName(name).stream().map(adsMapper::toAdsDTO).collect(Collectors.toList());
            return ResponseEntity.ok(new ResponseWrapperAdsDto(listAdsDto.size(), listAdsDto));
        } else //иначе нужно выкинуть ошибку 403  403??? Почему? Мы просто не нашли объявления, что запрещено?
        {
            throw new AdsNotFoundException("Ошибка 404: объявления не найдены");
        }

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
    //Загружаем объявление и картинку
    @PostMapping("/ads")
    public AdsDto addAds(@Valid @RequestPart(name = "properties") AdsDto ads,
                         @RequestPart("image") MultipartFile file) throws Exception {
        Advert advert = adsMapper.adsDTOtoAdvert(ads);
        adsService.addAds(advert);
        imageService.uploadImage(advert, file);
        return adsMapper.toAdsDTO(advert);
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
    public <object> ResponseEntity<ResponseWrapperAdsDto> getAdsMe(@Parameter(description = "") @PathVariable Advert.authenticated authenticated,
                                                                   @Parameter(description = "") @PathVariable String authority,
                                                                   @Parameter(description = "") @PathVariable object credentials,
                                                                   @Parameter(description = "") @PathVariable object details,
                                                                   @Parameter(description = "") @PathVariable object principal) {
        //return adsService.getAdsMe(authenticated,authority,credentials,details,principal);
        //Integer idUser = userService.getUser(id);
        Integer idUser = 1; // Get User = Me !!!
        List<Advert> adsList = adsService.getAdvertsByUserId(idUser);
        List<AdsDto> adsDtoList = adsList.stream().map(adsMapper::toAdsDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseWrapperAdsDto(adsDtoList.size(), adsDtoList));

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
    public ResponseEntity<ResponseWrapperAdsCommentDto> getAdsComments(@Parameter(description = "") @PathVariable Integer ad_pk) {
        List<Comment> commentList = adsService.getAdsComments(ad_pk);
        List<AdsCommentDto> adsCommentDtoList = commentList.stream().map(adsCommentMapper::toCommentDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseWrapperAdsCommentDto(adsCommentDtoList.size(), adsCommentDtoList));
    }

    /**
     * Создание комментариев по ad_pk .
     */
    @Operation(
            summary = "Создание комментариев по ad_pk",
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
    public AdsCommentDto addAdsComments(@Parameter(description = "") @PathVariable String ad_pk,
                                        @Parameter(description = "") @PathVariable AdsCommentDto adsCommentDto) {
        return adsCommentMapper.toCommentDTO(adsService.addComment(ad_pk, adsCommentMapper.toAsdComment(adsCommentDto)));
    }

    /**
     * Удаление комментария по id .
     */
    @PreAuthorize("hasRole('ADMIN')")
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
    public void deleteAdsComment(@Parameter(description = "") @PathVariable Integer ad_pk,
                                 @Parameter(description = "") @PathVariable Integer id) {
        adsService.deleteAdsComment(ad_pk, id);
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
    public Comment getAdsComment(@Parameter(description = "") @PathVariable Integer ad_pk,
                                 @Parameter(description = "") @PathVariable Integer id) {

/*
        Comment comment = adsService. .getAdsComments(ad_pk); // !!!
        List<AdsCommentDto> adsCommentDtoList = commentList.stream().map(adsCommentMapper::toCommentDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseWrapperAdsCommentDto(adsCommentDtoList.size(), adsCommentDtoList));

*/
        return adsService.getAdsComment(ad_pk, id);
    }

    /**
     * Изменение комментария по id .
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Изменение комментария по id .",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Удаление комментария успешно"),
                    @ApiResponse(responseCode = "201", description = "Созданный"),
                    @ApiResponse(responseCode = "401", description = "Неавторизованный"),
                    @ApiResponse(responseCode = "403", description = "Запрещенно"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @CrossOrigin(value = "http://localhost:3000")
    @PatchMapping("/ads/{ad_pk}/comment/{id}")
    public Comment updateAdsComment(@Parameter(description = "") @PathVariable Integer ad_pk,
                                    @Parameter(description = "") @PathVariable Integer id,
                                    @RequestBody Comment comment) {
        //ResponseEntity.ok(userMapper.toUserDTO(userService.updateUser(userMapper.userDtoFromUsers(userDto))));
//        return ResponseEntity.ok(adsCommentMapper.toCommentDTO())
        return adsService.updateAdsComment(ad_pk, id, comment);
    }

    /**
     * Удаление объявление по id .
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Удаление объявление по id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Удаление комментария успешно"),
                    @ApiResponse(responseCode = "201", description = "Созданный"),
                    @ApiResponse(responseCode = "401", description = "Неавторизованный"),
                    @ApiResponse(responseCode = "403", description = "Запрещенно"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @CrossOrigin(value = "http://localhost:3000")
    @DeleteMapping("/ads/{id}")
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
                    @ApiResponse(responseCode = "403", description = "Запрещенно"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @CrossOrigin(value = "http://localhost:3000")
    @GetMapping("/ads/{id}")
    public AdsDto getAds(@Parameter(description = "id объявления") @PathVariable Long id) {
        Advert advert = adsService.getAds(id);
        return adsMapper.toAdsDTO(advert);
    }

    /**
     * Изменение объявление по id .
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Изменение объявление по id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Удаление комментария успешно"),
                    @ApiResponse(responseCode = "201", description = "Созданный"),
                    @ApiResponse(responseCode = "401", description = "Неавторизованный"),
                    @ApiResponse(responseCode = "403", description = "Запрещенно"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @CrossOrigin(value = "http://localhost:3000")
    @PatchMapping("/ads/{id}")
    public AdsDto updateAds(@Parameter(description = "id объявления") @PathVariable Long id) {
        return adsMapper.toAdsDTO(adsService.updateAds(id));
    }

    @Operation(
            summary = "Обновить картинки объявлений",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Картинки успешно загружена"),
                    @ApiResponse(responseCode = "201", description = "Созданный"),
                    @ApiResponse(responseCode = "401", description = "Неавторизованный"),
                    @ApiResponse(responseCode = "403", description = "Запрещенный"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @PatchMapping(value = "ads/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateImage(@PathVariable Long id, @RequestParam MultipartFile image) throws Exception {
        imageService.updateImage(id, image);
        return ResponseEntity.ok().build();
    }
}
