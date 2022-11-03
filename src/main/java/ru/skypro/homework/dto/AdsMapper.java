package ru.skypro.homework.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.entities.Advert;

@Mapper(componentModel="spring")
public interface AdsMapper {
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "users", target = "author")
    AdsDto AdsDtoToAdvert(Advert advert);

}



