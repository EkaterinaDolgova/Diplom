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
import java.util.Set;
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
            List<AdsDto> listAdsDto = adsService.getAllAdsName(name).stream().map(adsMapper::toAdsDTO).collect(Collectors.toList());
            return ResponseEntity.ok(new ResponseWrapperAdsDto(listAdsDto.size(), listAdsDto));
    }

    /**
     * Добавить объявления.
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
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
    public ResponseEntity<ResponseWrapperAdsCommentDto> getAdsComments(@Parameter(description = "") @PathVariable String ad_pk) {
        List<Comment> commentList = adsService.getAdsComments(ad_pk); // !!!
        List<AdsCommentDto> adsCommentDtoList = commentList.stream().map(adsCommentMapper::toCommentDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseWrapperAdsCommentDto(adsCommentDtoList.size(), adsCommentDtoList));
    }

    /**
     * Создание комментариев по ad_pk .
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
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
    public void deleteAdsComment(@Parameter(description = "") @PathVariable String ad_pk,
                                 @Parameter(description = "") @PathVariable Integer id,
                                 Authentication authentication) {
        Long idUser = userService.getUsers().stream()
                .filter(user -> user.getFirstName().equals(authentication.getName())).findFirst()
                .orElseThrow(() -> new AuthorizedUserNotFoundException("Ошибка 403: пользователь не найден")).getId();
        String userRole = userService.getUsers().stream()
                .filter(user -> user.getFirstName().equals(authentication.getName())).findFirst()
                .orElseThrow(() -> new AuthorizedUserNotFoundException("Ошибка 403: пользователь не найден")).getRole();
        int i = idUser.intValue();
        //Проверяем есть ли у данного пользователя комментарии и записываем их в лист
        Set<Long> idComment = adsService.getAdsComments(ad_pk).stream().
                filter(comment -> comment.getUsers().equals((i))).
                map(comment -> comment.getId()).collect(Collectors.toSet());
        System.out.println(idComment);
        //Если выбраный комментарий создан пользователем, то можно удалять
        if (idComment.contains(id) || userRole.equals("ADMIN")) {
        adsService.deleteAdsComment(ad_pk, id);
        } else {
            throw new AdsNotFoundException("Ошибка 403: Вы не можете редактировать данный комментарий!");
        }
    }

    /**
     * Поиск комментария по id .
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
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
    public String updateAdsComment(@Parameter(description = "") @PathVariable String ad_pk,
                                   @Parameter(description = "") @PathVariable Integer id,
                                   Authentication authentication) {
        Long idUser = userService.getUsers().stream()
                .filter(user -> user.getFirstName().equals(authentication.getName())).findFirst()
                .orElseThrow(() -> new AuthorizedUserNotFoundException("Ошибка 403: пользователь не найден")).getId();
        String userRole = userService.getUsers().stream()
                .filter(user -> user.getFirstName().equals(authentication.getName())).findFirst()
                .orElseThrow(() -> new AuthorizedUserNotFoundException("Ошибка 403: пользователь не найден")).getRole();
        int i = idUser.intValue();
        //Проверяем есть ли у данного пользователя комментарии и записываем их в лист
        Set<Long> idComment = adsService.getAdsComments(ad_pk).stream().
                filter(comment -> comment.getUsers().equals((i))).
                map(comment -> comment.getId()).collect(Collectors.toSet());
        System.out.println(idComment);
        //Если выбраный комментарий создан пользователем, то можно удалять
        if (idComment.contains(id) || userRole.equals("ADMIN")) {
        return adsService.updateAdsComment(ad_pk, id);
        } else {
            throw new AdsNotFoundException("Ошибка 403: Вы не можете редактировать данный комментарий!");
        }
    }

    /**
     * Удаление объявление по id .
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
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
    public ResponseEntity removeAds(@Parameter(description = "id объявления") @PathVariable Long id, Authentication authentication) {
        Long idUser = userService.getUsers().stream()
                .filter(user -> user.getFirstName().equals(authentication.getName())).findFirst()
                .orElseThrow(() -> new AuthorizedUserNotFoundException("Ошибка 403: пользователь не найден")).getId();
        String userRole = userService.getUsers().stream()
                .filter(user -> user.getFirstName().equals(authentication.getName())).findFirst()
                .orElseThrow(() -> new AuthorizedUserNotFoundException("Ошибка 403: пользователь не найден")).getRole();
        int i = idUser.intValue();
        //Проверяем есть ли у данного пользователя объявления и записываем их в лист
        Set<Long> idAdvert = adsService.getAllAds().stream().
                filter(advert -> advert.getUsers().equals((i))).
                map(advert -> advert.getId()).collect(Collectors.toSet());
        System.out.println(idAdvert);
        //Если выбранное объявление создано пользователем, то можно удалять
        if (idAdvert.contains(id) || userRole.equals("ADMIN")) {
            adsService.removeAds(id);
            return ResponseEntity.ok().body(HttpStatus.OK);
        } else {
            throw new AdsNotFoundException("Ошибка 403: Вы не можете удалять данное объявление!");
        }
    }

    /**
     * Поиск объявление по id .
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @Operation(
            summary = "Изменение объявление по id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Изменение комментария успешно"),
                    @ApiResponse(responseCode = "201", description = "Созданный"),
                    @ApiResponse(responseCode = "401", description = "Неавторизованный"),
                    @ApiResponse(responseCode = "403", description = "Запрещенно"),
                    @ApiResponse(responseCode = "404", description = "Не найдено")
            }
    )
    @CrossOrigin(value = "http://localhost:3000")
    @PatchMapping("/ads/{id}")
    public AdsDto updateAds(@Parameter(description = "id объявления") @PathVariable Long id,Authentication authentication,Role role) throws Exception  {
        Long idUser1 = userService.getUsers().stream()
                    .filter(user -> user.getFirstName().equals(authentication.getName())).findFirst()
                .orElseThrow(() -> new AuthorizedUserNotFoundException("Ошибка 403: пользователь не найден")).getId();
        String userRole = userService.getUsers().stream()
                .filter(user -> user.getFirstName().equals(authentication.getName())).findFirst()
                .orElseThrow(() -> new AuthorizedUserNotFoundException("Ошибка 403: пользователь не найден")).getRole();
        int i = idUser1.intValue();
        //Проверяем есть ли у данного пользователя объявления и записываем их в лист
        Set<Long> idAdvert1 = adsService.getAllAds().stream().
                filter(advert -> advert.getUsers().equals((i))).
                map(advert -> advert.getId()).collect(Collectors.toSet());
        System.out.println(idAdvert1);
        //Если выбранное объявление создано пользователем, то можно удалять
        if (idAdvert1.contains(id) || userRole.equals("ADMIN")) {
        return adsMapper.toAdsDTO(adsService.updateAds(id));
        } else {
            throw new AdsNotFoundException("Ошибка 403: Вы не можете редактировать данное объявление!");
        }
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
    public ResponseEntity<String> updateImage(@PathVariable Long id, @RequestParam MultipartFile image,Authentication authentication) throws Exception {
        Long idUser1 = userService.getUsers().stream()
                .filter(user -> user.getFirstName().equals(authentication.getName())).findFirst()
                .orElseThrow(() -> new AuthorizedUserNotFoundException("Ошибка 403: пользователь не найден")).getId();
        String userRole = userService.getUsers().stream()
                .filter(user -> user.getFirstName().equals(authentication.getName())).findFirst()
                .orElseThrow(() -> new AuthorizedUserNotFoundException("Ошибка 403: пользователь не найден")).getRole();
        int i = idUser1.intValue();
        //Проверяем есть ли у данного пользователя объявления и записываем их в лист
        Set<Long> idAdvert1 = adsService.getAllAds().stream().
                filter(advert -> advert.getUsers().equals((i))).
                map(advert -> advert.getId()).collect(Collectors.toSet());
        System.out.println(idAdvert1);
        //Если выбранное объявление создано пользователем, то можно удалять
        if (idAdvert1.contains(id) || userRole.equals("ADMIN")) {
        imageService.updateImage(id, image);
        return ResponseEntity.ok().build();
        } else {
            throw new AdsNotFoundException("Ошибка 403: Вы не можете редактировать данное объявление!");
        }
    }
}
