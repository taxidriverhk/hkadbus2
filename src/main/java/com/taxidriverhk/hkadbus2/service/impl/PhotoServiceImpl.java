package com.taxidriverhk.hkadbus2.service.impl;

import com.taxidriverhk.hkadbus2.exception.BadRequestException;
import com.taxidriverhk.hkadbus2.exception.ItemNotFoundException;
import com.taxidriverhk.hkadbus2.mapper.EntityMapper;
import com.taxidriverhk.hkadbus2.model.domain.Photo;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoFilter;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoResult;
import com.taxidriverhk.hkadbus2.model.entity.PhotoEntity;
import com.taxidriverhk.hkadbus2.model.entity.SearchRecordEntity;
import com.taxidriverhk.hkadbus2.model.entity.result.SearchRecordResult;
import com.taxidriverhk.hkadbus2.provider.SearchPhotoProvider;
import com.taxidriverhk.hkadbus2.repository.PhotoRepository;
import com.taxidriverhk.hkadbus2.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class PhotoServiceImpl implements PhotoService {

    private static final int PAGE_LIMIT = 100;

    private final PhotoRepository photoRepository;
    private final SearchPhotoProvider searchPhotoProvider;

    @Override
    public Photo getPhoto(final Long photoId, final String language) {
        log.info("Getting photo by ID {} and language {}", photoId, language);
        final PhotoEntity photoEntity = photoRepository.getPhotoByShortId(photoId)
                .orElseThrow(() -> new ItemNotFoundException(photoId.toString()));
        return EntityMapper.INSTANCE.photoEntityToPhoto(photoEntity, language);
    }

    @Override
    public SearchPhotoResult searchPhotos(
            final String query,
            final String orderBy,
            final String sort,
            final SearchPhotoFilter filter,
            final String nextSortKey
    ) {
        final SearchRecordResult searchRecordResult = searchPhotoProvider.searchPhotos(
                query,
                orderBy,
                sort,
                filter,
                nextSortKey,
                PAGE_LIMIT);
        final Optional<String> lastSortKey = getLastSortKey(searchRecordResult.getSearchRecordEntities(), orderBy);
        return SearchPhotoResult.builder()
                .total(searchRecordResult.getTotal())
                .results(EntityMapper.INSTANCE
                        .searchRecordEntitiesToSearchRecords(searchRecordResult.getSearchRecordEntities()))
                .lastSortKey(lastSortKey.orElse(null))
                .build();
    }

    private Optional<String> getLastSortKey(final List<SearchRecordEntity> searchRecordEntities, final String orderBy) {
        if (searchRecordEntities.size() < 1) {
            return Optional.empty();
        }

        try {
            final SearchRecordEntity lastSearchRecord = searchRecordEntities.get(searchRecordEntities.size() - 1);
            final Object value = new PropertyDescriptor(orderBy, SearchRecordEntity.class).getReadMethod().invoke(lastSearchRecord);
            return Optional.of(value.toString());
        } catch (final IntrospectionException | IllegalAccessException | InvocationTargetException ex) {
            throw new BadRequestException(String.format("Invalid order by field %s", orderBy));
        }
    }
}
