package com.taxidriverhk.hkadbus2.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.taxidriverhk.hkadbus2.model.domain.Advertisement;
import com.taxidriverhk.hkadbus2.model.domain.BusModel;
import com.taxidriverhk.hkadbus2.model.domain.Category;
import com.taxidriverhk.hkadbus2.model.domain.Photo;
import com.taxidriverhk.hkadbus2.model.domain.SearchRecord;
import com.taxidriverhk.hkadbus2.model.domain.User;
import com.taxidriverhk.hkadbus2.model.entity.AdvertisementEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusModelEntity;
import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import com.taxidriverhk.hkadbus2.model.entity.PhotoEntity;
import com.taxidriverhk.hkadbus2.model.entity.SearchRecordEntity;
import com.taxidriverhk.hkadbus2.model.entity.UserEntity;

@Mapper
public interface EntityMapper {

    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    @Mapping(target = "id", expression = "java(advertisementEntity.getHashKey())")
    @Mapping(target = "name", expression = "java(advertisementEntity.getName().get(language))")
    @Mapping(target = "categoryId", expression = "java(advertisementEntity.getCategory().getHashKey())")
    @Mapping(target = "categoryName", expression = "java(advertisementEntity.getCategory().getName().get(language))")
    Advertisement advertisementEntityToAdvertisement(AdvertisementEntity advertisementEntity, String language);

    @Mapping(target = "id", expression = "java(categoryEntity.getHashKey())")
    @Mapping(target = "name", expression = "java(categoryEntity.getName().get(language))")
    Category categoryEntityToCategory(CategoryEntity categoryEntity, String language);

    @Mapping(target = "id", expression = "java(busModelEntity.getHashKey())")
    @Mapping(target = "name", expression = "java(busModelEntity.getName().get(language))")
    @Mapping(target = "busBrandId", expression = "java(busModelEntity.getBusBrand().getHashKey())")
    @Mapping(target = "busBrandName", expression = "java(busModelEntity.getBusBrand().getName().get(language))")
    BusModel busModelEntityToBusModel(BusModelEntity busModelEntity, String language);

    @Mapping(target = "photoId", expression = "java(photoEntity.getShortId())")
    @Mapping(target = "advertisementId", expression = "java(photoEntity.getAdvertisement().getHashKey())")
    @Mapping(target = "advertisement", expression = "java(photoEntity.getAdvertisement().getName().get(language))")
    @Mapping(target = "categoryId", expression = "java(photoEntity.getAdvertisement().getCategory().getHashKey())")
    @Mapping(target = "category", expression = "java(photoEntity.getAdvertisement().getCategory().getName().get(language))")
    @Mapping(
            target = "busCompany",
            expression = "java(com.taxidriverhk.hkadbus2.model.domain.BusCompany.fromString(photoEntity.getBus().getBusCompany()))")
    @Mapping(target = "busId", expression = "java(photoEntity.getBus().getShortId())")
    @Mapping(target = "busModelId", expression = "java(photoEntity.getBus().getBusModel().getHashKey())")
    @Mapping(target = "busModel", expression = "java(photoEntity.getBus().getBusModel().getName().get(language))")
    @Mapping(target = "busBrand", expression = "java(photoEntity.getBus().getBusModel().getBusBrand().getName().get(language))")
    @Mapping(target = "routeNumber", expression = "java(photoEntity.getBusRoute().getRouteNumber())")
    @Mapping(target = "licensePlateNumber", expression = "java(photoEntity.getBus().getLicensePlateNumber())")
    @Mapping(target = "fleetPrefix", expression = "java(photoEntity.getBus().getFleetPrefix())")
    @Mapping(target = "fleetNumber", expression = "java(photoEntity.getBus().getFleetNumber())")
    @Mapping(target = "username", expression = "java(photoEntity.getUser().getUsername())")
    @Mapping(target = "uploadedDate", expression = "java(photoEntity.getUploadedDate().getTime())")
    Photo photoEntityToPhoto(PhotoEntity photoEntity, String language);

    @Mapping(target = "registrationDate", expression = "java(userEntity.getRegistrationDate().getTime())")
    User userEntityToUser(UserEntity userEntity);

    @Mapping(source = "advertisementHashKey", target = "advertisementId")
    @Mapping(source = "advertisementName", target = "advertisement")
    @Mapping(source = "categoryHashKey", target = "categoryId")
    @Mapping(source = "categoryName", target = "category")
    @Mapping(
            target = "busCompany",
            expression = "java(com.taxidriverhk.hkadbus2.model.domain.BusCompany.fromString(searchRecordEntity.getBusCompany()))")
    @Mapping(source = "busModelHashKey", target = "busModelId")
    @Mapping(source = "busModelName", target = "busModel")
    @Mapping(source = "photoShortId", target = "photoId")
    @Mapping(source = "routeHashKey", target = "routeId")
    @Mapping(target = "tags", expression = "java(java.util.Arrays.asList(searchRecordEntity.getTags().split(\",\")))")
    SearchRecord searchRecordEntityToSearchRecord(SearchRecordEntity searchRecordEntity);

    List<SearchRecord> searchRecordEntitiesToSearchRecords(List<SearchRecordEntity> searchRecordEntities);

    @Mapping(source = "advertisementId", target = "advertisementHashKey")
    @Mapping(source = "advertisement", target = "advertisementName")
    @Mapping(source = "categoryId", target = "categoryHashKey")
    @Mapping(source = "category", target = "categoryName")
    @Mapping(source = "busModelId", target = "busModelHashKey")
    @Mapping(source = "busModel", target = "busModelName")
    @Mapping(source = "photoId", target = "photoShortId")
    @Mapping(source = "routeId", target = "routeHashKey")
    @Mapping(target = "tags", expression = "java(String.join(\",\", searchRecord.getTags()))")
    @Mapping(target = "language", constant = "")
    SearchRecordEntity searchRecordToSearchRecordEntity(SearchRecord searchRecord);
}
