package com.taxidriverhk.hkadbus2.mapper;

import com.taxidriverhk.hkadbus2.model.domain.Category;
import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EntityMapper {

    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    List<Category> categoryEntitiesToCategories(List<CategoryEntity> categoryEntities);

    @Mapping(source = "hashKey", target = "id")
    Category categoryEntityToCategory(CategoryEntity categoryEntity);
}
