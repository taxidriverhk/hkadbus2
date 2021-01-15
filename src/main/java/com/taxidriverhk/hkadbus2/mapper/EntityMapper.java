package com.taxidriverhk.hkadbus2.mapper;

import com.taxidriverhk.hkadbus2.model.domain.Advertisement;
import com.taxidriverhk.hkadbus2.model.domain.Category;
import com.taxidriverhk.hkadbus2.model.entity.AdvertisementEntity;
import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

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
}
