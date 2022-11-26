package ru.skypro.homework.dto;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.entities.Advert;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AdsMapper {
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "users", target = "author")
    //@Mapping(source = "images",target = "image", qualifiedByName = "getLastImageString")
    AdsDto toAdsDTO(Advert advert);

   /* @Named("getLastImageString")
    default String getLastImageString(Advert advert) {
        Image lastImage = advert.getLastImage();
        return (lastImage == null) ? null : "/ads/image/" + lastImage.getId().toString();
    }*/
    @Mapping(source = "pk", target = "id")
    @Mapping(source = "author", target = "users")
    Advert adsDTOtoAdvert(AdsDto adsDto);

    @Mapping(source = "id", target = "pk")
     AdsDto createAdsDtoTOAdvert(Advert advert);

    //@Mapping(source = "pk", target = "id")
    Advert advertTOCreateAdsDto(CreateAdsDto createAdsDto);

    Advert createAdsDtoToAds(CreateAdsDto createAdsDto);


}



