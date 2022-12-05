package ru.skypro.homework.servicetest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import ru.skypro.homework.dto.AdsMapper;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.CreateAdsDtoMapper;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.entities.Advert;
import ru.skypro.homework.entities.Comment;
import ru.skypro.homework.entities.Image;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static ru.skypro.homework.TestsConstants.*;

@ExtendWith(MockitoExtension.class)
public class AdsServiceTest {
    @Mock
    private AdsRepository adsRepository;

    @Mock
    private CommentRepository commentRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ImageRepository imageRepository;
    @Mock
    private AdsMapper adsMapper;
    @Mock
    private CreateAdsDtoMapper createAdsDtoMapper;

    @InjectMocks
    private AdsService adsService;

    @BeforeEach
    void setUp() {
        ADVERT1.setId(1L);
        ADVERT1.setUsers(AUTHOR);
        ADVERT1.setTitle(TITLE);
        ADVERT1.setDescription(DESCRIPTION1);
        ADVERT1.setPrice(PRICE);

        ADVERT2.setId(2L);
        ADVERT2.setUsers(AUTHOR);
        ADVERT2.setTitle(TITLE1);
        ADVERT2.setDescription(DESCRIPTION1);
        ADVERT2.setPrice(PRICE1);
        ADVERT2.setImage(IMAGE_STR1);

        COMMENT1.setCreatedAt(DATE);
        COMMENT1.setUsers(2);
        COMMENT1.setAdvert(ADVERT2);
        COMMENT1.setText(TEXT);
        COMMENT1.setId(1L);

        COMMENT2.setCreatedAt(DATE);
        COMMENT2.setUsers(1);
        COMMENT2.setAdvert(ADVERT2);
        COMMENT2.setText(TEXT1);
        COMMENT2.setId(2L);

        LIST_COMMENTS.add(COMMENT1);
        LIST_COMMENTS.add(COMMENT2);

        LIST_ADS_NAME.add(ADVERT2);
        LIST_ADS.add(ADVERT2);
        LIST_ADS.add(ADVERT2);

    }

    @Test
    void getAllAdsIsCorrectWork() {
        when(adsRepository.findAll()).thenReturn(LIST_ADS);

        List<Advert> list= adsService.getAllAds();

        assertEquals(LIST_ADS,list);
    }

    @Test
    void getAdvertBiIdIsCorrectWork() {
        when(adsRepository.findById(anyLong())).thenReturn(Optional.of(ADVERT2));

        Advert advert= adsService.getAdvertBiId(2L);

        assertEquals(ADVERT2,advert);
    }

    @Test
    void getAllAdsNameIsCorrectWork() {
        when(adsRepository.findByTitleContaining(anyString())).thenReturn(LIST_ADS_NAME);

        List<Advert> list= adsService.getAllAdsName(TITLE1);

        assertEquals(LIST_ADS_NAME,list);
    }
    @Test
    void addAdsIsCorrectWork() {
        Advert advert = adsService.addAds(ADVERT2);
        assertEquals(ADVERT2, advert);
    }

    @Test
    void getAddsCommentsIsCorrectWork() {
        when(adsRepository.findByTitleContaining(anyString())).thenReturn(LIST_ADS_NAME);
        Advert advert = adsService.addAds(ADVERT2);
        assertEquals(ADVERT2, advert);
    }

    @Test
    void getAdsCommentsisCorrectWork() {
        when(adsRepository.findById(anyLong())).thenReturn(Optional.of(ADVERT2));
        when(commentRepository.findCommentsByAdvert(any(Advert.class))).thenReturn(LIST_COMMENTS);
        List <Comment> listOfComments = adsService.getAdsComments(2);
        assertEquals(LIST_COMMENTS, listOfComments);
    }


}
