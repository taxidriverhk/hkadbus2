package com.taxidriverhk.hkadbus2.mapper;

import com.taxidriverhk.hkadbus2.model.domain.Category;
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
}
