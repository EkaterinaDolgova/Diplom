package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.entities.Comment;

@Mapper(componentModel = "spring")
public interface AdsCommentMapper {
    AdsCommentMapper INSTANCE = Mappers.getMapper(AdsCommentMapper.class);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "users", target = "author")
    AdsCommentDto toCommentDTO(Comment comment);
}
