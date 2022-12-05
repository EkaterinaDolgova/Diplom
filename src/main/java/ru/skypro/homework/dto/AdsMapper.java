package ru.skypro.homework.dto;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.entities.Advert;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AdsMapper {
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "users.id", target = "author")
    AdsDto toAdsDTO(Advert advert);

    @Mapping(source = "pk", target = "id")
    @Mapping(source = "author", target = "users.id")
    Advert adsDTOtoAdvert(AdsDto adsDto);



}



