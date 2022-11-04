package ru.skypro.homework.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.entities.Advert;

@Mapper(componentModel="spring")
public interface AdsMapper {
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "users", target = "author")
    AdsDto toAdsDTO(Advert advert);

    @Mapping(source = "pk", target = "id")
    @Mapping(source = "author", target = "users")
    Advert adsDTOtoAdvert(AdsDto adsDto);

}



