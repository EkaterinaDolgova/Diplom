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
import ru.skypro.homework.entities.Image;
import ru.skypro.homework.entities.Users;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Контроллер Объявления
 */

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {
    private final AdsService adsService;
    private final AdsMapper adsMapper;
    private final ImageService imageService;
    private final AdsCommentMapper adsCommentMapper;
    private final UserService userService;
    private final CommentRepository commentRepository;
    private final AuthService authService;
    private final ImageRepository imageRepository;


    public AdsController(AdsService adsService, AdsMapper adsMapper, ImageService imageService, AdsCommentMapper adsCommentMapper, UserService userService, CommentRepository commentRepository, AuthService authService, ImageRepository imageRepository) {
        this.adsService = adsService;
        this.adsMapper = adsMapper;
        this.imageService = imageService;
        this.adsCommentMapper = adsCommentMapper;
        this.userService = userService;
        this.commentRepository = commentRepository;
        this.authService = authService;
        this.imageRepository = imageRepository;
    }

    Logger logger = LoggerFactory.getLogger(AdsController.class);

    /**
     * Возвращает список объявлений.
     */
    @GetMapping()
    @Operation(summary = "Получить список объявлений", responses = {@ApiResponse(responseCode = "200", description = "Список объявлений успешно получен"), @ApiResponse(responseCode = "201", description = "Созданный"), @ApiResponse(responseCode = "401", description = "Неавторизованный"), @ApiResponse(responseCode = "403", description = "Запрещенный"), @ApiResponse(responseCode = "404", description = "Не найдено")})
    public ResponseEntity<ResponseWrapperAdsDto> getAllAds() {
        List<AdsDto> listAdsDto = adsService.getAllAds().stream().map(adsMapper::toAdsDTO).collect(Collectors.toList());
        System.out.println(listAdsDto);
        return ResponseEntity.ok(new ResponseWrapperAdsDto(listAdsDto.size(), listAdsDto));
    }

    /**
     * Возвращает список объявлений по поиску наименования.
     */
    @Operation(summary = "Получить список объявлений по поиску наименования", responses = {@ApiResponse(responseCode = "401", description = "Неавторизованный"), @ApiResponse(responseCode = "403", description = "Запрещенный"), @ApiResponse(responseCode = "404", description = "Не найдено")})
    @GetMapping("/search/{name}")
    public ResponseEntity<ResponseWrapperAdsDto> getAllAdsName(@Parameter(description = "Введите наименование объявления") @PathVariable String name,
                                                               Authentication authentication) {
        List<AdsDto> listAdsDto = adsService.getAllAdsName(name).stream().map(adsMapper::toAdsDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseWrapperAdsDto(listAdsDto.size(), listAdsDto));
    }

    /**
     * Добавить объявления.
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @Operation(summary = "Добавить объявление", responses = {@ApiResponse(responseCode = "200", description = "Список объявлений успешно получен"), @ApiResponse(responseCode = "201", description = "Созданный"), @ApiResponse(responseCode = "401", description = "Неавторизованный"), @ApiResponse(responseCode = "403", description = "Запрещенный"), @ApiResponse(responseCode = "404", description = "Не найдено")})
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<AdsDto> addAds(
            @Parameter(description = "Параметры объявления")
            @RequestPart(value = "properties") CreateAdsDto createAdsDto,
            @Parameter(description = "Изображение")
            @RequestPart(value = "image") MultipartFile file, Authentication authentication
    ) {
        logger.info("Добавление объявления: {}");
        Users users = userService.findIdUser(authentication.getName());
        Advert advert = adsMapper.createAdsDtoToAds(createAdsDto);
        advert.setUsers(users);
        Advert adsCreated = adsService.addAds(advert);

        String imageId = imageService.uploadImage(adsCreated, file);
        AdsDto adsDto = adsMapper.toAdsDTO(adsCreated);
        adsDto.setImage("/ads/image/" + imageId);

        //Запишем url картинки
        String imageString = "/ads/image/" + imageId;
        Integer idAdvert = adsDto.getPk();
        adsService.updateAdsImage(idAdvert.longValue(), imageString);
        return ResponseEntity.ok(adsDto);
    }

    /**
     * Возвращает список комментариев по ad_pk .
     */
    @Operation(summary = "Возвращает список комментариев по ad_pk", responses = {@ApiResponse(responseCode = "200", description = "ОК"), @ApiResponse(responseCode = "201", description = "Созданный"), @ApiResponse(responseCode = "401", description = "Неавторизованный"), @ApiResponse(responseCode = "403", description = "Запрещенный"), @ApiResponse(responseCode = "404", description = "Не найдено")})
    @GetMapping("/{ad_pk}/comment")
    public ResponseEntity<ResponseWrapperAdsCommentDto> getAdsComments(@Parameter(description = "") @PathVariable Integer ad_pk) {
        List<Comment> comments = adsService.getAdsComments(ad_pk);
        List<AdsCommentDto> adsCommentDtoList = comments.stream().map(adsCommentMapper::toCommentDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseWrapperAdsCommentDto(adsCommentDtoList.size(), adsCommentDtoList));
    }

    /**
     * Создание комментариев по ad_pk .
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @Operation(summary = "Создание комментариев по ad_pk", responses = {@ApiResponse(responseCode = "200", description = "ОК"), @ApiResponse(responseCode = "201", description = "Созданный"), @ApiResponse(responseCode = "401", description = "Неавторизованный"), @ApiResponse(responseCode = "403", description = "Запрещенный"), @ApiResponse(responseCode = "404", description = "Не найдено")})
    @PostMapping("/{ad_pk}/comment")
    public AdsCommentDto addAdsComments(@Parameter(description = "") @PathVariable Integer ad_pk, @Parameter(description = "") @RequestBody AdsCommentDto adsCommentDto) {

        return adsCommentMapper.toCommentDTO(adsService.addComment(ad_pk, adsCommentMapper.toAsdComment(adsCommentDto)));
    }

    /**
     * Удаление комментария по id .
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @Operation(summary = "Удаление комментария по id .", responses = {@ApiResponse(responseCode = "200", description = "Удаление комментария успешно"), @ApiResponse(responseCode = "201", description = "Созданный"), @ApiResponse(responseCode = "401", description = "Неавторизованный"), @ApiResponse(responseCode = "403", description = "Запрещенный"), @ApiResponse(responseCode = "404", description = "Не найдено")})
    @DeleteMapping("/{ad_pk}/comment/{id}")
    public void deleteAdsComment(@Parameter(description = "") @PathVariable Integer ad_pk, @Parameter(description = "") @PathVariable Integer id, Authentication authentication) {
        Users users = userService.findIdUser(authentication.getName());
        String userRole = adsService.findIdUserRole(authentication.getName());
        //Проверяем есть ли у данного пользователя комментарии и записываем их id в лист
        Set<Long> idComment = adsService.findIdComment(users.getId());
        //Если выбраный комментарий создан пользователем, то можно удалять
        if (idComment.contains(Long.valueOf(id)) || userRole.equals("ADMIN")) {
            adsService.deleteAdsComment(id);
        } else {
            throw new AdsNotFoundException("Ошибка 403: Вы не можете удалить данный комментарий!");
        }
    }

    /**
     * Поиск комментария по id .
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @Operation(summary = "Поиск комментария по id .", responses = {@ApiResponse(responseCode = "200", description = "Удаление комментария успешно"), @ApiResponse(responseCode = "201", description = "Созданный"), @ApiResponse(responseCode = "401", description = "Неавторизованный"), @ApiResponse(responseCode = "403", description = "Запрещенный"), @ApiResponse(responseCode = "404", description = "Не найдено")})
    @GetMapping("/{ad_pk}/comment/{id}")
    public Comment getAdsComment(@Parameter(description = "") @PathVariable Integer ad_pk, @Parameter(description = "") @PathVariable Integer id) {
        return adsService.getAdsComment(ad_pk, id);
    }

    /**
     * Изменение комментария по id .
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @Operation(summary = "Изменение комментария по id .", responses = {@ApiResponse(responseCode = "200", description = "Удаление комментария успешно"), @ApiResponse(responseCode = "201", description = "Созданный"), @ApiResponse(responseCode = "401", description = "Неавторизованный"), @ApiResponse(responseCode = "403", description = "Запрещенно"), @ApiResponse(responseCode = "404", description = "Не найдено")})
    @PatchMapping("/{ad_pk}/comment/{id}")
    public Comment updateAdsComment(@Parameter(description = "") @PathVariable Integer ad_pk, @Parameter(description = "") @PathVariable Integer id, @RequestBody Comment comment, Authentication authentication) {
        Users users = userService.findIdUser(authentication.getName());
        String userRole = adsService.findIdUserRole(authentication.getName());
        //Проверяем есть ли у данного пользователя комментарии и записываем их id в лист
        Set<Long> idComment = adsService.findIdComment(users.getId());
        //Если выбраный комментарий создан пользователем, то можно редактировать
        if (idComment.contains(Long.valueOf(id)) || userRole.equals("ADMIN")) {
            return adsService.updateAdsComment(ad_pk, id, comment);
        } else {
            throw new AdsNotFoundException("Ошибка 403: Вы не можете редактировать данный комментарий!");
        }
    }

    /**
     * Возвращает список объявлений пользователя.
     */
    @Operation(summary = "Получить список объявлений по параметрам", responses = {@ApiResponse(responseCode = "200", description = "Список объявлений успешно получен"), @ApiResponse(responseCode = "201", description = "Созданный"), @ApiResponse(responseCode = "401", description = "Неавторизованный"), @ApiResponse(responseCode = "403", description = "Запрещенный"), @ApiResponse(responseCode = "404", description = "Не найдено")})
    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAdsDto> getAdsMe(@Parameter(description = "") @RequestParam(required = false) Boolean authenticated,
                                                          @Parameter(description = "") @RequestParam(required = false) String authority,
                                                          @Parameter(description = "") @RequestParam(required = false) Object credentials,
                                                          @Parameter(description = "") @RequestParam(required = false) Object details,
                                                          @Parameter(description = "") @RequestParam(required = false) Object principal,
                                                          Authentication authentication) {
        Users users = userService.findIdUser(authentication.getName());
        List<Advert> adsList = adsService.getAdvertsByUserId(users.getId());
        if (!adsList.isEmpty()) {
            List<AdsDto> adsDtoList = adsList.stream().map(adsMapper::toAdsDTO).collect(Collectors.toList());
            return ResponseEntity.ok(new ResponseWrapperAdsDto(adsDtoList.size(), adsDtoList));
        } else {
            ArrayList<AdsDto> defaultListEmptyAdsDto = new ArrayList<AdsDto>();
            return ResponseEntity.ok(new ResponseWrapperAdsDto(defaultListEmptyAdsDto.size(), defaultListEmptyAdsDto));
        }

    }

    /**
     * Удаление объявление по id .
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @Operation(summary = "Удаление объявление по id.", responses = {@ApiResponse(responseCode = "200", description = "Удаление комментария успешно"), @ApiResponse(responseCode = "201", description = "Созданный"), @ApiResponse(responseCode = "401", description = "Неавторизованный"), @ApiResponse(responseCode = "403", description = "Запрещенно"), @ApiResponse(responseCode = "404", description = "Не найдено")})
    @DeleteMapping("/{id}")
    public ResponseEntity removeAds(@Parameter(description = "id объявления") @PathVariable Long id, Authentication authentication) {
        Users users = userService.findIdUser(authentication.getName());
        String userRole = adsService.findIdUserRole(authentication.getName());
        //Проверяем есть ли у данного пользователя объявления и записываем их в лист
        Set<Long> idAdvert = adsService.findAdvertIdUser(users.getId());
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
    @Operation(summary = "Поиск объявление по id.", responses = {@ApiResponse(responseCode = "200", description = "Удаление комментария успешно"), @ApiResponse(responseCode = "201", description = "Созданный"), @ApiResponse(responseCode = "401", description = "Неавторизованный"), @ApiResponse(responseCode = "403", description = "Запрещенно"), @ApiResponse(responseCode = "404", description = "Не найдено")})
    @GetMapping("/{id}")
    public AdsDto getAds(@Parameter(description = "id объявления") @PathVariable Long id) {
        Advert advert = adsService.getAds(id);
        return adsMapper.toAdsDTO(advert);
    }

    /**
     * Изменение объявление по id .
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @Operation(summary = "Изменение объявление по id.", responses = {@ApiResponse(responseCode = "200", description = "Изменение комментария успешно"), @ApiResponse(responseCode = "201", description = "Созданный"), @ApiResponse(responseCode = "401", description = "Неавторизованный"), @ApiResponse(responseCode = "403", description = "Запрещенно"), @ApiResponse(responseCode = "404", description = "Не найдено")})
    @PatchMapping("/{id}")
    public AdsDto updateAds(@Parameter(description = "id объявления") @PathVariable Long id,
                            @Parameter(description = "") @RequestBody AdsDto adsDto,
                            Authentication authentication) throws Exception {
        Users users = userService.findIdUser(authentication.getName());
        String userRole = adsService.findIdUserRole(authentication.getName());
        //Проверяем есть ли у данного пользователя объявления и записываем их в лист
        Set<Long> idAdvert1 = adsService.findAdvertIdUser(users.getId());
        //Если выбранное объявление создано пользователем, то можно редактировать
        if (idAdvert1.contains(id) || userRole.equals("ADMIN")) {
            return adsMapper.toAdsDTO(adsService.updateAds(id, adsMapper.adsDTOtoAdvert(adsDto)));
        } else {
            throw new AdsNotFoundException("Ошибка 403: Вы не можете редактировать данное объявление!");
        }
    }

    /**
     * Изменение картинки в объявлении .
     */
    @Operation(summary = "Обновить картинки объявлений", responses = {@ApiResponse(responseCode = "200", description = "Картинки успешно загружена"), @ApiResponse(responseCode = "201", description = "Созданный"), @ApiResponse(responseCode = "401", description = "Неавторизованный"), @ApiResponse(responseCode = "403", description = "Запрещенный"), @ApiResponse(responseCode = "404", description = "Не найдено")})
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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
}
