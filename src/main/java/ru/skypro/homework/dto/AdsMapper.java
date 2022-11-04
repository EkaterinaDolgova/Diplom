package ru.skypro.homework.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.entities.Advert;
//import ru.skypro.homework.mappers.UserMapper;

@Mapper(componentModel="spring")
public interface AdsMapper {
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "users", target = "author")
    AdsDto adsDtoToAdvert(Advert advert);

    @Mapping(source = "pk", target = "id")
    @Mapping(source = "author", target = "users")
    Advert advertToAdsDto(AdsDto adsDto);

}



