package ru.skypro.homework.dto;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.skypro.homework.entities.Advert;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CreateAdsDtoMapper {
    Advert createAdsDtoToAds(CreateAdsDto dto);
    CreateAdsDto adsToCreateAdsDto(Advert advert);
}
