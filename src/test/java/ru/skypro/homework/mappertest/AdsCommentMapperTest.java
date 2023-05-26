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
import ru.skypro.homework.entities.Comment;
import ru.skypro.homework.repository.AdsRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static ru.skypro.homework.TestsConstants.*;

@ExtendWith(MockitoExtension.class)
public class AdsCommentMapperTest {
    @Mock
    AdsRepository adsRepository;
    @InjectMocks
    AdsCommentMapper mapper = Mappers.getMapper(AdsCommentMapper.class);
    @BeforeEach
    void setUp() {

        COMMENT1.setId(1L);
        COMMENT1.setUsers(1);
        COMMENT1.setAdvert(ADVERT);
        COMMENT1.setCreatedAt(DATE);
        COMMENT1.setText(TEXT);

        ADS_COMMENT_DTO.setPk(1L);
        ADS_COMMENT_DTO.setAuthor(1);
        ADS_COMMENT_DTO.setCreatedAt(DATE);
        ADS_COMMENT_DTO.setText(TEXT);

        ADVERT.setId(1L);
        ADVERT.setDescription(DESCRIPTION);
        ADVERT.setPrice(PRICE);
        ADVERT.setImage(IMAGE_STR);
    }

        @Test
        void toCommentDtoIsCorrectWork() {
            AdsCommentDto adsCommentDto = mapper.toCommentDTO(COMMENT1);
            assertEquals(1L, adsCommentDto.getPk());
            assertEquals(1, adsCommentDto.getAuthor());
            assertEquals(DATE, adsCommentDto.getCreatedAt());
            assertEquals("text", adsCommentDto.getText());
        }

       /*@Test
        void toAsdCommentIsCorrectWork() {
            lenient().when(adsRepository.findById(anyLong())).thenReturn(Optional.of(ADVERT));
            Comment comment = mapper.toAsdComment(ADS_COMMENT_DTO);
            assertEquals(1L, comment.getAdvert().getId());
            assertEquals(1, comment.getUsers());
            assertEquals(PARSE_DATE, comment.getCreatedAt());
            assertEquals("text", comment.getText());
        }*/
    }


