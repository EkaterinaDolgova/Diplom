package ru.skypro.homework.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.entities.Comment;

@Mapper(componentModel="spring")
public interface AdsCommentMapper {
/*
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "users", target = "author")
    AdsCommentDto toCommentDTO(Comment comment);*/
}
