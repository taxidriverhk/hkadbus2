package com.taxidriverhk.hkadbus2.mapper;

import com.taxidriverhk.hkadbus2.model.domain.Advertisement;
import com.taxidriverhk.hkadbus2.model.domain.BusModel;
import com.taxidriverhk.hkadbus2.model.domain.Category;
import com.taxidriverhk.hkadbus2.model.entity.AdvertisementEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusBrandEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusModelEntity;
import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntityMapper {

    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    @Mapping(target = "id", expression = "java(categoryEntity.getHashKey())")
    @Mapping(target = "name", expression = "java(categoryEntity.getName().get(language))")
    Category categoryEntityToCategory(CategoryEntity categoryEntity, String language);

    @Mapping(target = "id", expression = "java(advertisementEntity.getHashKey())")
    @Mapping(target = "name", expression = "java(advertisementEntity.getName().get(language))")
    @Mapping(target = "thumbnail", expression = "java(advertisementEntity.getThumbnail())")
    @Mapping(target = "categoryId", expression = "java(categoryEntity.getHashKey())")
    @Mapping(target = "categoryName", expression = "java(categoryEntity.getName().get(language))")
    Advertisement advertisementEntityToAdvertisement(
            AdvertisementEntity advertisementEntity, CategoryEntity categoryEntity, String language);

    @Mapping(target = "id", expression = "java(busModelEntity.getHashKey())")
    @Mapping(target = "name", expression = "java(busModelEntity.getName().get(language))")
    @Mapping(target = "busBrandId", expression = "java(busBrandEntity.getHashKey())")
    @Mapping(target = "busBrandName", expression = "java(busBrandEntity.getName().get(language))")
    BusModel busModelEntityToBusModel(
            BusModelEntity busModelEntity, BusBrandEntity busBrandEntity, String language);
}
