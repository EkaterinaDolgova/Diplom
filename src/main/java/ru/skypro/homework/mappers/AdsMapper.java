package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.entities.Advert;

@Mapper(componentModel="spring")
public interface AdsMapper {
    AdsMapper INSTANCE = Mappers.getMapper( AdsMapper.class );

    @Mapping(source = "id", target = "pk")
    AdsDto createAdsDtoToAdvertEntity(Advert advert);

}


