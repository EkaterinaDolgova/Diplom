package ru.skypro.homework.dto;

import org.mapstruct.*;
import ru.skypro.homework.entities.Advert;
import ru.skypro.homework.entities.Image;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AdsMapper {
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "users.id", target = "author")
    @Mapping(source = "images",target = "image", qualifiedByName = "getLastImageString")
    AdsDto toAdsDTO(Advert advert);

 @Named("getLastImageString")
    default String getLastImageString(Advert advert) {
        Image lastImage = advert.getImages();//.getLastImage();
        return (lastImage == null) ? null : "/ads/image/" + lastImage.getId().toString();
    }
    @Mapping(source = "pk", target = "id")
    @Mapping(source = "author", target = "users.id")
    Advert adsDTOtoAdvert(AdsDto adsDto);

    @Mapping(source = "id", target = "pk")
     AdsDto createAdsDtoTOAdvert(Advert advert);

    //@Mapping(source = "pk", target = "id")
    Advert advertTOCreateAdsDto(CreateAdsDto createAdsDto);

    Advert createAdsDtoToAds(CreateAdsDto createAdsDto);


}



