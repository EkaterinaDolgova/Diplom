package ru.skypro.homework.dto;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.entities.Advert;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FullAdsDtoMapper {
    @Mapping(target = "authorFirstName", source = "users.firstName")
    @Mapping(target = "authorLastName", source = "users.lastName")
    @Mapping(target = "email", source = "users.username")
    @Mapping(target = "phone", source = "users.phone")
    @Mapping(target = "pk", source = "id")
    FullAdsDto adsToFullAdsDto(Advert advert);
}
