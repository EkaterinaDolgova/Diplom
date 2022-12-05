package ru.skypro.homework.mappertest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.dto.AdsCommentMapper;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.AdsMapper;
import ru.skypro.homework.entities.Advert;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static ru.skypro.homework.TestsConstants.*;

@ExtendWith(MockitoExtension.class)
public class AdsMapperTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    AdsMapper mapper = Mappers.getMapper(AdsMapper.class);

    @BeforeEach
    void setUp() {
        AUTHOR.setId(2L);
        AUTHOR.setEmail(EMAIL);
        AUTHOR.setPhone(PHONE);
        AUTHOR.setLastName(LAST_NAME);
        AUTHOR.setFirstName(FIRST_NAME);

        ADVERT.setId(1L);
        ADVERT.setPrice(PRICE);
        ADVERT.setTitle(TITLE);
        ADVERT.setImage(IMAGE_STR);
        ADVERT.setDescription(DESCRIPTION);
        ADVERT.setUsers(AUTHOR);

        ADS_DTO.setPk(1);
        ADS_DTO.setAuthor(2);
        ADS_DTO.setImage(IMAGE_STR);
        ADS_DTO.setTitle(TITLE);
        ADS_DTO.setPrice(PRICE);
    }

    @Test
    void toAdsDTOIsCorrectWork() {
        AdsDto adsDto = mapper.toAdsDTO(ADVERT);
        assertEquals(1, adsDto.getPk());
        assertEquals(2, adsDto.getAuthor());
        assertEquals(PRICE, adsDto.getPrice());
        assertEquals(TITLE, adsDto.getTitle());
        assertEquals(IMAGE_STR, adsDto.getImage());
    }

    @Test
    void adsDTOtoAdvertIsCorrectWork() {
        lenient().when(userRepository.findById(anyLong())).thenReturn(Optional.of(AUTHOR));

        Advert advert = mapper.adsDTOtoAdvert(ADS_DTO);

        assertEquals(1, advert.getId());
        assertEquals(2, advert.getUsers().getId());
        assertEquals(IMAGE_STR, advert.getImage());
        assertEquals(TITLE, advert.getTitle());
        assertEquals(PRICE, advert.getPrice());
    }
}
