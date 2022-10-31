package ru.skypro.homework.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.Mapping;
import ru.skypro.homework.entities.Advert;

@Mapper(componentModel="spring")
public interface AdsMapper {
  /*  AdsMapper INSTANCE = Mappers.getMapper( AdsMapper.class );

    @Mapping(source = "pk", target = "id")
     AdsDto createAdsDtoToAdvertEntity(Advert advert);
*/
}
